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

    public Long writeArticle(Long boardId, Long memberId, String title, String body) {
        articleRepository.writeArticle(boardId, memberId, title, body);
        return articleRepository.getLastInsertId();
    }
    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }
    public List<Article> findArticlesWithWriterName(Long boardId, Integer page) {
        Integer startPage = (page-1) * 10;  // 시작 페이지는 동적으로 변한다.
        Integer offset = 10;  // offset 은 10
        return articleRepository.findArticlesWithWriterName(boardId, startPage, offset);
    }

    public Article findArticleWithWriterName(Long memberId, Long articleId) {
        Article article = articleRepository.findArticleWithWriterName(articleId);
        updateCanDelete(memberId, article);
        return article;
    }
    private void updateCanDelete(Long memberId, Article article) {
        if(article == null) return;
        boolean canDelete = canDelete(memberId, article);
        article.setTemp_canDelete(canDelete); // 삭제가 가능하면, Article에 임시로 만들어 둔 boolean 필드가 true로 세팅된다. 지울 수 있다는 뜻.
    }
    public Article findById(Long id) {
        return articleRepository.findById(id);
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

    public Integer getArticlesCount(Long boardId) {
        return articleRepository.getArticlesCount(boardId);
    }
}
