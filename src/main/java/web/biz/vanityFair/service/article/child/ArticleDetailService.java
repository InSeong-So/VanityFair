package web.biz.vanityFair.service.article.child;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import web.biz.vanityFair.domain.article.child.ArticleDetail;
import web.biz.vanityFair.repository.article.child.ArticleDetailRepository;
import web.common.core.component.VanityFairExtends;
import web.common.core.exception.VanityFairRException;

@Service
public class ArticleDetailService extends VanityFairExtends
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
            throw new VanityFairRException("조회수 업데이트에 실패하였습니다. 사유 : " + e.getMessage());
        }
        
        Optional<ArticleDetail> articleDetail = articleDetailRepository.findById(seqNo);
        
        return articleDetail.get();
    }
}
