package web.biz.vanityFair.service.article.child;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.child.ArticleComment;
import web.biz.vanityFair.domain.article.child.ArticleDetail;
import web.biz.vanityFair.repository.article.child.ArticleCommentRepository;

@Service
public class ArticleCommentService
{
    @Autowired
    private ArticleCommentRepository articleCommentRepository;
    
    public boolean articleCommentResistration(ArticleDetail articleDetail, String userId, String comment)
    {
        try
        {
            ArticleComment articleComment = ArticleComment.builder().article(articleDetail.getArticle()).comment(comment).userId(userId).commentNo(articleCommentRepository.getCommentNo(articleDetail.getArticle()) + 1).build();
            
            articleCommentRepository.save(articleComment);
            
            return true;
            
        }
        catch (Exception e)
        {
            // TODO: handle exception
            return false;
        }
    }
    
    public List<ArticleComment> articleCommentSearch(Article article)
    {
        List<ArticleComment> articleComment = articleCommentRepository.findByArticle(article);
        
        return articleComment;
    }
}
