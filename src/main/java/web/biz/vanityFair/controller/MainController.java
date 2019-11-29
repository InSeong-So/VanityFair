package web.biz.vanityFair.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import web.common.core.component.SisExtends;
import web.common.core.util.SisSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MainController extends SisExtends {

    @GetMapping("/")
    public String home(HttpServletRequest request, HttpSession session) {
        session.setAttribute(SisSessionUtil.REQUEST_SESSION_KEY, request);
        return "home";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/deals")
    public String deals() {
        return "deals";
    }

    @GetMapping("/stores")
    public String stores() {
        return "stores";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
