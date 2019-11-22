package web.biz.vanityFair.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import web.biz.vanityFair.domain.article.ArticleDetail;

public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, Long>, CrudRepository<ArticleDetail, Long>
{
    ArticleDetail findBySeqNo(long seqNo);
    
    @Modifying
    @Query("UPDATE ArticleDetail ad SET ad.viewCnt = ad.viewCnt + 1 WHERE ad.seqNo = :seqNo")
    public void updateViewCnt(@Param("seqNo") long seqNo);
}
