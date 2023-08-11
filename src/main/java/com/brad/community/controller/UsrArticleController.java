package com.brad.community.controller;

import com.brad.community.service.ArticleService;
import com.brad.community.vo.Article;
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
    public Long doAdd(String title, String body) {
        return articleService.writeArticle(title, body);
    }

    @RequestMapping("/getArticles")
    @ResponseBody
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @RequestMapping("/getArticle")
    @ResponseBody
    public Object getArticle(Long id) {
        Article article = articleService.getArticle(id);
        if(article == null) {
            return id + "번 게시물이 존재하지 않습니다!";
        }
        return article;
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Long id) {
        Article article = articleService.getArticle(id);
        if(article == null) {
            return id +"번 게시물이 존재하지 않습니다.";
        }
        articleService.deleteArticle(id);
        return id + "번 게시물이 삭제되었습니다!";
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public String doModify(Long id, String title, String body) {
        Article article = articleService.getArticle(id);
        if(article == null) {
            return id +"번 게시물이 존재하지 않습니다.";
        }
        articleService.modifyArticle(id, title, body);
        return id + "번 게시물이 수정되었습니다!";
    }
}
