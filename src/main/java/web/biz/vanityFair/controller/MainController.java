package web.biz.vanityFair.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import web.common.core.component.SisExtends;
import web.common.core.util.SisSessionUtil;

@Controller
public class MainController extends SisExtends
{
    
    @GetMapping("/")
    public String home(HttpServletRequest request, HttpSession session)
    {
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>> : "+vfConf.getUploadImages());
        session.setAttribute(SisSessionUtil.REQUEST_SESSION_KEY, request);
        return "home";
    }
    
    @GetMapping("/products")
    public String products()
    {
        return "products";
    }
    
    @GetMapping("/deals")
    public String deals()
    {
        return "deals";
    }
    
    @GetMapping("/stores")
    public String stores()
    {
        return "stores";
    }
    
    @GetMapping("/contact")
    public String contact()
    {
        return "contact";
    }
    
}
