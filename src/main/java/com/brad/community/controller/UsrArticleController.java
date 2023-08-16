package com.brad.community.controller;

import com.brad.community.service.ArticleService;
import com.brad.community.util.Ut;
import com.brad.community.vo.Article;
import com.brad.community.vo.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/usr/article")
@RequiredArgsConstructor
public class UsrArticleController {
    private final ArticleService articleService;

    @RequestMapping("/doAdd")
    @ResponseBody
    public DataResponse<Article> doAdd(HttpSession session, String title, String body) {
        if(session.getAttribute("loginMemberId") == null) {
            return DataResponse.of("F-Authentication", "로그인 후 이용해주세요.");
        }
        Long memberId = (Long) session.getAttribute("loginMemberId");

        if (Ut.isEmpty(title)) return DataResponse.of("F-1", "게시물 제목을 입력해주세요.");
        if (Ut.isEmpty(body)) return DataResponse.of("F-1", "게시물 내용을 입력해주세요.");

        Long articleId = articleService.writeArticle(memberId, title, body);
        Article article = articleService.getArticle(articleId);
        return DataResponse.of("S-1", Ut.f("%d번 게시물이 생성되었습니다.", articleId), article);
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
        Article article = articleService.getArticle(id);
        if(article == null) {
            return DataResponse.of("F-1", Ut.f("%d번 게시물이 존재하지 않습니다", id));
        }
        return DataResponse.of("S-1", Ut.f("%d번 게시물입니다.", id), article);
    }

    @RequestMapping("/list")
    public String showList(HttpSession session, Model model) {
        List<Article> articles = articleService.findArticlesWithWriterName();
        model.addAttribute("articles", articles);
        return "article/list";
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public DataResponse doDelete(HttpSession session, Long id) {

        if(session.getAttribute("loginMemberId") == null) {
            return DataResponse.of("F-Authentication", "로그인 후 이용해주세요.");
        }
        Long memberId = (Long) session.getAttribute("loginMemberId");

        Article article = articleService.getArticle(id);
        if(article == null) {
            return DataResponse.of("F-1", Ut.f("%d번 게시물이 존재하지 않습니다!", id));
        }

        boolean canDelete = articleService.canDelete(memberId, article);
        if(!canDelete) return DataResponse.of("F-Authorization", "삭제 권한이 없습니다!");

        articleService.deleteArticle(id);
        return DataResponse.of("S-1", Ut.f("%d번 게시물이 삭제되었습니다.", id));
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public DataResponse doModify(HttpSession session, Long id, String title, String body) {
        if(session.getAttribute("loginMemberId") == null) {
            return DataResponse.of("F-Authentication", "로그인 후 이용해주세요.");
        }
        Long memberId = (Long) session.getAttribute("loginMemberId");

        Article article = articleService.getArticle(id);
        if(article == null) {
            return DataResponse.of("F-1", Ut.f("%d번 게시물이 존재하지 않습니다!", id));
        }

        boolean canModify = articleService.canModify(memberId, article);
        if(!canModify) return DataResponse.of("F-Authorization", "수정 권한이 없습니다!");

        articleService.modifyArticle(id, title, body);
        return DataResponse.of("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id));
    }

    @RequestMapping("/detail")
    public String showDetail(HttpSession session, Model model, Long id) {
        Long loginMemberId = 0L;  // 0으로 찾으면, 게시물 조회가 안된다.(아래 메서드에서 예외 처리)
        if(session.getAttribute("loginMemberId") != null) {
            loginMemberId = (Long) session.getAttribute("loginMemberId");
        }

        Article article = articleService.findArticleWithWriterName(loginMemberId, id);
        model.addAttribute("article", article);
        return "article/detail";
    }
}
