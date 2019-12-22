package web.biz.vanityFair.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import web.biz.vanityFair.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, CrudRepository<Product, Long> {

}
