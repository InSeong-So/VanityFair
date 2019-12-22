package web.biz.vanityFair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.biz.vanityFair.domain.product.Product;
import web.biz.vanityFair.facade.ProductInterface;
import web.common.core.component.SisExtends;

@Controller
@RequestMapping("/products")
public class ProductController extends SisExtends {

    private String menuName = "app_products/products";

    @Autowired
    private ProductInterface productInterface;

    @GetMapping("/{productsNumber}")
    public ModelAndView product_left(@PathVariable("productsNumber") String productsNumber, @PageableDefault Pageable pageable) {
        ModelAndView view = new ModelAndView();

        Page<Product> productList = productInterface.getProductList(pageable);

        view.setViewName(menuName);
        view.addObject("leftMenu", productsNumber);
        view.addObject("productList", productList);

        return view;
    }
}
