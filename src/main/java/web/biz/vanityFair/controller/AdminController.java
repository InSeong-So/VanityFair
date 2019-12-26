package web.biz.vanityFair.controller;

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

        view.setViewName(menuName);
        view.addObject("leftMenu", adminNumber);

        return view;
    }
}
