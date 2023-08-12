package com.brad.community.controller;

import com.brad.community.service.ArticleService;
import com.brad.community.util.Ut;
import com.brad.community.vo.Article;
import com.brad.community.vo.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/usr/article")
@RequiredArgsConstructor
public class UsrArticleController {
    private final ArticleService articleService;

    @RequestMapping("/doAdd")
    @ResponseBody
    public DataResponse<Article> doAdd(String title, String body) {
        if (Ut.isEmpty(title)) return DataResponse.of("F-1", "게시물 제목을 입력해주세요.");
        if (Ut.isEmpty(body)) return DataResponse.of("F-1", "게시물 내용을 입력해주세요.");

        Long articleId = articleService.writeArticle(title, body);
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

    @RequestMapping("/doDelete")
    @ResponseBody
    public DataResponse doDelete(Long id) {
        Article article = articleService.getArticle(id);
        if(article == null) {
            return DataResponse.of("F-1", Ut.f("%d번 게시물이 존재하지 않습니다!", id));
        }
        articleService.deleteArticle(id);
        return DataResponse.of("S-1", Ut.f("%d번 게시물이 삭제되었습니다.", id));
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public DataResponse doModify(Long id, String title, String body) {
        Article article = articleService.getArticle(id);
        if(article == null) {
            return DataResponse.of("F-1", Ut.f("%d번 게시물이 존재하지 않습니다!", id));
        }
        articleService.modifyArticle(id, title, body);
        return DataResponse.of("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id));
    }
}
