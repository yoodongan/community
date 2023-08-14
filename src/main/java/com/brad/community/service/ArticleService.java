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

    public Long writeArticle(Long memberId, String title, String body) {
        articleRepository.writeArticle(memberId, title, body);
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

    /* 회원이 게시물 삭제가 가능한지 판별하는 메서드 */
    public boolean canDelete(Long memberId, Article article) {
        if (!article.getMemberId().equals(memberId)) {
            return false;  // 회원 아이디가 다르면, 해당 게시물 삭제할 권한이 없다.
        }
        return true;
    }

    public boolean canModify(Long memberId, Article article) {
        if (!article.getMemberId().equals(memberId)) {
            return false;  // 회원 아이디가 다르면, 해당 게시물 삭제할 권한이 없다.
        }
        return true;
    }
}
