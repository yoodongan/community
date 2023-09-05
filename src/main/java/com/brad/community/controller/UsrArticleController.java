package com.brad.community.controller;

import com.brad.community.service.ArticleService;
import com.brad.community.service.BoardService;
import com.brad.community.util.Ut;
import com.brad.community.vo.Article;
import com.brad.community.vo.Board;
import com.brad.community.vo.DataResponse;
import com.brad.community.vo.Req;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/usr/article")
@RequiredArgsConstructor
public class UsrArticleController {
    private final ArticleService articleService;
    private final BoardService boardService;
    private final Req req;

    @RequestMapping("/write")
    public String showWriteForm() {
        return "article/write";
    }

    @RequestMapping("/doWrite")
    @ResponseBody
    public String doWrite(Long boardId, String title, String body) {
        if (Ut.isEmpty(title)) return Ut.historyBack("게시물 제목을 입력해주세요.");
        if (Ut.isEmpty(body)) return Ut.historyBack("게시물 내용을 입력해주세요.");
        Long articleId = articleService.writeArticle(boardId, req.getLoginMemberId(), title, body);
        return Ut.replace(Ut.f("%d번 게시물이 생성되었습니다.", articleId), "../article/list");
    }

    @RequestMapping("/getArticles")
    @ResponseBody
    public DataResponse<List> getArticles() {
        List<Article> articles = articleService.getArticles();
        return DataResponse.of("S-1", "게시물 전체 조회", articles);
    }

    @RequestMapping("/getArticle")
    @ResponseBody
    public DataResponse<Article> getArticle(Long id) {
        Article article = articleService.findById(id);
        if(article == null) {
            return DataResponse.of("F-1", Ut.f("%d번 게시물이 존재하지 않습니다", id));
        }
        return DataResponse.of("S-1", Ut.f("%d번 게시물입니다.", id), article);
    }

    /* 공지사항, 자유게시판에 따라 다르게 보여줘야 한다. */
    /* 검색타입, 검색어에 따라 총 게시물 수를 계산할 수 있어야 한다. */
    @RequestMapping("/list")
    public String showList(Model model, @RequestParam(defaultValue = "1") Long boardId, @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "title,body") String searchKeywordType, @RequestParam(defaultValue = "") String searchKeyword) {
        Board board = boardService.findById(boardId);
        if(board == null) {
            return Ut.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다!", boardId));
        }
        model.addAttribute("board", board);
        model.addAttribute("boardId", boardId);

        Integer articlesCount = articleService.getArticlesCount(boardId, searchKeywordType, searchKeyword);
        model.addAttribute("articlesCount", articlesCount);

        int totalPage = (int) Math.ceil((double)articlesCount / 10);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);

        List<Article> articles = articleService.findArticlesWithWriterName(boardId, page, searchKeywordType, searchKeyword);
        model.addAttribute("articles", articles);
        return "/article/list";
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(HttpServletRequest request, Long id) {
        Article article = articleService.findById(id);
        if(article == null) {
            return Ut.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다!", id));
        }

        boolean canDelete = articleService.canDelete(req.getLoginMemberId(), article);
        if(!canDelete) return Ut.historyBack("삭제 권한이 없습니다!");

        articleService.deleteArticle(id);
        return Ut.replace(Ut.f("%d번 게시물이 삭제되었습니다.", id), "../article/list");
    }

    @RequestMapping("/modify")
    public String showModifyForm(HttpServletRequest request, Model model, Long id) {
        Article article = articleService.findById(id);
        if(article == null) Ut.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다!", id));
        model.addAttribute("article", article);

        boolean canModify = articleService.canModify(req.getLoginMemberId(), article);
        if(!canModify) Ut.historyBack("수정 권한이 없습니다!");

        return "/article/modify";
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public String doModify(HttpServletRequest request, Long id, String title, String body) {
        Article article = articleService.findById(id);
        if(article == null) {
            return Ut.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다!", id));
        }

        boolean canModify = articleService.canModify(req.getLoginMemberId(), article);
        if(!canModify) return Ut.historyBack("수정 권한이 없습니다!");

        if(Ut.isEmpty(title)) return Ut.historyBack("게시물 제목을 입력해주세요.");

        articleService.modifyArticle(id, title, body);
        return Ut.replace(Ut.f("%d번 게시물이 수정되었습니다.", id), "../article/list");
    }

    @RequestMapping("/detail")
    public String showDetail(HttpServletRequest request, Model model, Long id) {
        Article article = articleService.findArticleWithWriterName(req.getLoginMemberId(), id);
        model.addAttribute("article", article);
        return "article/detail";
    }

    @RequestMapping("/doIncreaseHitCount")
    @ResponseBody
    public DataResponse doIncreaseHitcount(Long id) {
        boolean canIncreaseHitCount = articleService.increaseHitcount(id);
        if(!canIncreaseHitCount) return DataResponse.of("F-1", "조회 수 증가할 게시물이 존재하지 않습니다!");

        Integer hitCount = articleService.getHitCount(id);
        return DataResponse.of("S-1", "조회 수 증가 성공", hitCount);
    }
}
