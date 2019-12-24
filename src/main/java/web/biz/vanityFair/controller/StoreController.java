package web.biz.vanityFair.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stores")
public class StoreController {

    private String menuName = "app_stores/stores";

    @GetMapping("/{storesNumber}")
    public ModelAndView articles_left(@PathVariable("storesNumber") String storesNumber, @PageableDefault Pageable pageable) {
        ModelAndView view = new ModelAndView();

        view.setViewName(menuName);
        view.addObject("leftMenu", storesNumber);

        return view;
    }
}
