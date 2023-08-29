package com.brad.community.repository;

import com.brad.community.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleRepository {
    public void writeArticle(@Param("boardId")Long boardId, @Param("memberId")Long memberId, @Param("title")String title, @Param("body")String body);
    public Article findById(@Param("id") Long id);
    public void modifyArticle(Long id, String title, String body);
    public List<Article> getArticles();
    public void deleteArticle(Long id);
    Long getLastInsertId();

    // 작성자명을 출력해야 하므로, Member의 작성자명을 가져온다.
    List<Article> findArticlesWithWriterName(@Param("boardId") Long boardId, @Param("startPage") Integer startPage, @Param("offset") Integer offset);

    Article findArticleWithWriterName(@Param("id") Long articleId);

    Integer getArticlesCount(@Param("boardId") Long boardId);

    Integer getArticlesCount(Long boardId, String searchKeywordType, String searchKeyword);

    List<Article> findArticlesWithWriterName(Long boardId, Integer startPage, Integer offset, String searchKeywordType, String searchKeyword);
}
