package web.biz.vanityFair.service.article;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import web.biz.vanityFair.domain.article.ArticleDetail;
import web.biz.vanityFair.repository.article.ArticleDetailRepository;
import web.common.core.component.SisExtends;
import web.common.core.exception.SisRuntimeException;

@Service
public class ArticleDetailService extends SisExtends
{
    @Autowired
    private ArticleDetailRepository articleDetailRepository;
    
    public Page<ArticleDetail> articleDetailList(Pageable pageable)
    {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5, new Sort(Direction.DESC, "seqNo"));
        
        return articleDetailRepository.findAll(pageable);
    }
    
    // 최초 작성 시 동작하는 메소드
    public void articleDetailInit(ArticleDetail articleDetail)
    {
        articleDetailRepository.save(articleDetail);
    }
    
    public ArticleDetail articleDetailSearch(long seqNo)
    {
        
        try
        {
            articleDetailRepository.updateViewCnt(seqNo);
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("조회수 업데이트에 실패하였습니다. 사유 : " + e.getMessage());
        }
        
        Optional<ArticleDetail> articleDetail = articleDetailRepository.findById(seqNo);
        
        return articleDetail.get();
    }
}
