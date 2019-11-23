package web.biz.vanityFair.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.user.User;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, CrudRepository<Article, Long> {

    Optional<Article> findByUserArtiNo(String userId);

    Optional<Article> findBySeqNo(long seqNo);

    Optional<Article> getArticleByArticleCd(String articleCd);

    // seq_no 채번
    @Query(value = "SELECT CASE WHEN MAX(a.seqNo) IS NULL THEN 0 ELSE MAX(a.seqNo) END AS seqNo FROM Article a")
    long getSeqNo();

    // user_arti_no 채번
    @Query(value = "SELECT CASE WHEN MAX(a.userArtiNo) IS NULL THEN 0 ELSE MAX(a.userArtiNo) END AS userArtiNo FROM Article a WHERE a.user = :user")
    long getUserArtiNo(@Param("user") User user);

    @Modifying
    @Query("UPDATE Article a SET a.viewCnt = a.viewCnt + 1 WHERE a.seqNo = :seqNo")
    void updateViewCnt(@Param("seqNo") long seqNo);
}
