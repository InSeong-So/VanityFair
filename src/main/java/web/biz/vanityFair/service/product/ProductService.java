package web.biz.vanityFair.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import web.biz.vanityFair.domain.product.Product;
import web.biz.vanityFair.facade.ProductInterface;
import web.biz.vanityFair.repository.product.ProductRepository;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void productRegistration() {

    }

    @Override
    public void productToCart() {

    }

    @Override
    public void productIsGood() {

    }

    @Override
    public void productUpdate() {

    }

    @Override
    public void productAbsDelete() {

    }

    @Override
    public void productHide() {

    }

    @Override
    public Page<Product> getProductList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5, new Sort(Direction.DESC, "seqNo"));

        return productRepository.findAll(pageable);
    }

    @Override
    public Product productDetail() {
        return null;
    }

    @Override
    public void productSoldUpdate() {

    }

    @Override
    public void productCharge() {

    }

    @Override
    public void productPayment() {

    }
}
