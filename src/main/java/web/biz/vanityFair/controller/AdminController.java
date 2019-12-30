package web.biz.vanityFair.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.common.core.component.SisExtends;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController extends SisExtends {

    private String menuName = "app_admin/admin";

    @GetMapping
    public ModelAndView admin_total() {
        ModelAndView view = new ModelAndView();

        view.setViewName(menuName);
        view.addObject("leftMenu", "status");

        return view;
    }

    @GetMapping("/{adminNumber}/{adminSubNumber}")
    public ModelAndView admin_left(@PathVariable("adminNumber") String adminNumber, @PathVariable("adminSubNumber") String adminSubNumber) {
        ModelAndView view = new ModelAndView();

        switch (adminNumber) {
            case "01":
                log.info("상품분류관리 메뉴");
                break;
            case "02":
                log.info("상품옵션관리 메뉴");
                break;
            case "03":
                log.info("상품등록 메뉴");
                break;
            case "04":
                log.info("상품관리 메뉴");
                break;
            case "05":
                log.info("상품표시관리 메뉴");
                break;
            case "06":
                log.info("판매분류관리 메뉴");
                break;
            case "07":
                log.info("재고관리 메뉴");
                break;
            default:
                break;
        }

        view.setViewName(menuName);
        view.addObject("leftMenu", adminNumber);
        view.addObject("subMenu", adminSubNumber);

        return view;
    }
}
