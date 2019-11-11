package web.biz.vanityFair.repository.article.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import web.biz.vanityFair.domain.article.child.ArticleAttribute;

public interface ArticleAttributeRepository extends JpaRepository<ArticleAttribute, String>, CrudRepository<ArticleAttribute, String>
{
    
}
