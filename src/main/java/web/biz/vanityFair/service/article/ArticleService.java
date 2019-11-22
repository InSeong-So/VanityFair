package web.biz.vanityFair.service.article;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.ArticleAttribute;
import web.biz.vanityFair.domain.article.ArticleComment;
import web.biz.vanityFair.domain.article.ArticleDetail;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.repository.article.ArticleRepository;
import web.common.core.component.SisExtends;
import web.common.core.exception.SisRuntimeException;
import web.common.core.util.SisEncUtil;
import web.common.core.util.SisStringUtil;

@Slf4j
@Service
@Transactional
public class ArticleService extends SisExtends
{
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleDetailService ad;
    
    @Autowired
    private ArticleAttributeService aa;
    
    @Autowired
    private ArticleCommentService ac;
    
    public Page<ArticleDetail> getArticleList(Pageable pageable)
    {
        return ad.articleDetailList(pageable);
    }
    
    @Transactional
    public boolean articleResistration(User user, ArticleDetail articleDetail, ArticleAttribute articleAttribute)
    {
        // 세션값이 존재하지 않으면 튕겨냄
        if (SisStringUtil.isEmpty(user))
            throw new IllegalStateException("로그인이 필요한 서비스입니다.");
        
        try
        {
            Article article = Article.builder().articleCd(codeCreator()).seqNo(articleRepository.getSeqNo() + 1).user(user).userArtiNo(articleRepository.getUserArtiNo(user) + 1).build();
            
            articleRepository.save(article);
            
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> arti0000 등록 완료!");
            
            articleDetail.setArticle(article);
            articleDetail.setUserId(user.getUserId());
            articleDetail.setRegDateStr(SisStringUtil.getYmd(new SimpleDateFormat("yyyy-MM-dd")));
            
            ad.articleDetailInit(articleDetail);
            
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> arti0010 등록 완료!");
            
            articleAttribute.setArticle(article);
            articleAttribute.setArticlePwd(SisEncUtil.SHA256(articleAttribute.getArticlePwd()));
            
            aa.articleAttributeInit(articleAttribute);
            
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> arti0020 등록 완료!");
            
            return true;
            
        }
        catch (Exception e)
        {
            log.error("articleResistration ERROR : " + e.getMessage());
            throw new SisRuntimeException("게시글 등록에 실패하였습니다.");
        }
        
    }
    
    public ArticleDetail getArticleDetail(long seqNo)
    {
        return ad.articleDetailSearch(seqNo);
    }
    
    public boolean articleCommentResistration(long seqNo, User user, String comment)
    {
        log.info("댓글 등록 중, 등록자 : " + user.getUserId());
        
        try
        {
            ArticleDetail articleDetail = ad.articleDetailSearch(seqNo);
            
            ArticleAttribute articleAttribute = ArticleAttribute.builder().article(articleDetail.getArticle()).articleCategory("자유게시판_댓글").articleLayer(1).articlePwd("").build();
            
            aa.articleAttributeInit(articleAttribute);
            
            ac.articleCommentResistration(articleDetail, user.getUserId(), comment);
            
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        
        log.info("댓글 등록 완료");
        
        return true;
    }
    
    public List<ArticleComment> getArticleComment(Article article)
    {
        return ac.articleCommentSearch(article);
    }
    
}
