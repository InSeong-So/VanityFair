package web.biz.vanityFair.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import core.util.VanityFairSessionUtil;
import lombok.extern.slf4j.Slf4j;
import web.common.core.component.VanityFairExtends;

@Slf4j
@Controller
public class MainController extends VanityFairExtends
{
    
    @GetMapping("/")
    public String home(HttpServletRequest request, HttpSession session)
    {
        //        if (!session.isNew())
        //        {
        //            session.invalidate();
        //        }
        //        log.info(codeCreator(request));
        log.info(">>>>>>>>>>>>>>>>>>>>>>>> : "+vfConf.getUploadImages());
        session.setAttribute(VanityFairSessionUtil.REQUEST_SESSION_KEY, request);
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
