package web.biz.vanityFair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.facade.ProductInterface;
import web.common.core.component.SisExtends;

@Slf4j
@Controller
@RequestMapping("/products")
public class ProductController extends SisExtends
{
    
    private String menuName = "app_products/products";
    
    @Autowired
    private ProductInterface productInterface;
    
    @GetMapping("/{productsNumber}")
    public ModelAndView products_first(@PathVariable("productsNumber") String productsNumber)
    {
        ModelAndView view = new ModelAndView();
        
        log.info("Best&Sale 메뉴");
        view.setViewName(menuName);
        view.addObject("leftMenu", productsNumber);
        return view;
    }
    
    @GetMapping("/{productsNumber}/{productsSubNumber}")
    public ModelAndView products_left(@PathVariable("productsNumber") String productsNumber, @PathVariable("productsSubNumber") String productsSubNumber)
    {
        ModelAndView view = new ModelAndView();
        
        switch (productsNumber)
        {
            case "02":
                log.info("TOP 카테고리");
                break;
            case "03":
                log.info("BOTTOM 카테고리");
                break;
            case "04":
                log.info("OUTER 카테고리");
                break;
            case "05":
                log.info("DRESS 카테고리");
                break;
            case "06":
                log.info("ACC 카테고리");
                break;
            default:
                break;
        }
        
        view.setViewName(menuName);
        view.addObject("leftMenu", productsNumber);
        view.addObject("subMenu", productsSubNumber);
        
        return view;
    }
}
