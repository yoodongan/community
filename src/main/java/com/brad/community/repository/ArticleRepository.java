package com.brad.community.repository;

import com.brad.community.vo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleRepository {
    public void writeArticle( @Param("memberId")Long memberId, @Param("title")String title, @Param("body")String body);
    public Article getArticle(@Param("id") Long id);
    public void modifyArticle(Long id, String title, String body);
    public List<Article> getArticles();
    public void deleteArticle(Long id);
    Long getLastInsertId();

    // 작성자명을 출력해야 하므로, Member의 작성자명을 가져온다.
    List<Article> findArticlesWithWriterName();

    Article findArticleWithWriterName(@Param("id") Long articleId);
}
