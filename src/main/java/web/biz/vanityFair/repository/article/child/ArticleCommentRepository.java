package web.biz.vanityFair.repository.article.child;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.child.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>, CrudRepository<ArticleComment, Long>
{
    // comment_no 채번
    @Query("SELECT CASE WHEN MAX(ac.commentNo) IS NULL THEN 0 ELSE ac.commentNo END AS commentNo FROM ArticleComment ac WHERE ac.article = :article")
    long getCommentNo(@Param("article") Article article);
    
    List<ArticleComment> findByArticle(Article article);
    
}
