package web.biz.vanityFair.service.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.biz.vanityFair.domain.article.ArticleAttribute;
import web.biz.vanityFair.repository.article.ArticleAttributeRepository;
import web.common.core.component.SisExtends;

@Service
public class ArticleAttributeService extends SisExtends
{
    
    @Autowired
    private ArticleAttributeRepository articleAttributeRepository;
    
    // 최초 작성 시 동작하는 메소드
    public void articleAttributeInit(ArticleAttribute articleAttribute)
    {
        articleAttributeRepository.save(articleAttribute);
    }
}
