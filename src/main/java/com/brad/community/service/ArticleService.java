package com.brad.community.service;

import com.brad.community.repository.ArticleRepository;
import com.brad.community.vo.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long writeArticle(String title, String body) {
        articleRepository.writeArticle(title, body);
        return articleRepository.getLastInsertId();
    }
    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public Article getArticle(Long id) {
        return articleRepository.getArticle(id);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteArticle(id);
    }

    public void modifyArticle(Long id, String title, String body) {
        articleRepository.modifyArticle(id, title, body);
    }
}
