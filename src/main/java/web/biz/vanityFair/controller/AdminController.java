package web.biz.vanityFair.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.common.core.component.SisExtends;

@Controller
@RequestMapping("/admin")
public class AdminController extends SisExtends {

    private String menuName = "app_admin/admin";

    @GetMapping("/{adminNumber}")
    public ModelAndView admin_left(@PathVariable("adminNumber") String adminNumber, @PageableDefault Pageable pageable) {
        ModelAndView view = new ModelAndView();

        view.setViewName(menuName);
        view.addObject("leftMenu", 1);

        return view;
    }
}
