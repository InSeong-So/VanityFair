package web.biz.vanityFair.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.ArticleComment;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>, CrudRepository<ArticleComment, Long> {
    // comment_no 채번
    @Query("SELECT (CASE WHEN MAX(ac.commentNo) IS NULL THEN 0 ELSE MAX(ac.commentNo) END) AS commentNo FROM ArticleComment ac WHERE ac.article = :article")
    long getCommentNo(@Param("article") Article article);

    List<ArticleComment> findByArticle(Article article);

    @Modifying
    @Query("DELETE FROM ArticleComment ac WHERE ac.article = :article AND ac.commentNo = :commentNo AND ac.userId = :userId")
    void setDeleteComment(@Param("article") Article article, @Param("userId") String userId, @Param("commentNo") long commentNo);
}
