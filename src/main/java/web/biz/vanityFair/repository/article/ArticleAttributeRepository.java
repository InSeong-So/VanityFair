package web.biz.vanityFair.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import web.biz.vanityFair.domain.article.ArticleAttribute;

public interface ArticleAttributeRepository extends JpaRepository<ArticleAttribute, String>, CrudRepository<ArticleAttribute, String>
{
    
}
