package web.biz.vanityFair.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.biz.vanityFair.domain.product.Product;

/**
 * 1. 상품을 등록한다.
 * 2. 상품을 장바구니에 담는다.
 * 3. 상품을 좋아요 누른다.
 * 4. 상품을 수정한다.
 * 5-0. 상품의 정보를 삭제한다.
 * 5-1. 상품을 판매하지 않는다.
 * 6-0. 상품을 리스트를 조회한다.
 * 6-1. 상품 상세 정보를 조회한다.
 * 7. 상품이 판매되어 재고가 소진된다.
 * 8. 상품의 재고를 충전한다.
 * 9. 상품을 결제한다.
 */
public interface ProductInterface {
    void productRegistration();

    void productToCart();

    void productIsGood();

    void productUpdate();

    void productAbsDelete();

    void productHide();

    Page<Product> getProductList(Pageable pageable);

    Product productDetail();

    void productSoldUpdate();

    void productCharge();

    void productPayment();
}
