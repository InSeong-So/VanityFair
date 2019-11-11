package web.biz.vanityFair.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import core.util.VanityFairSessionUtil;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.service.user.UserService;
import web.common.core.component.VanityFairExtends;

@Controller
//@SessionAttributes("vanityFairSession")
public class LoginController extends VanityFairExtends
{
    @Autowired
    private UserService userService;
    
    // 페이지 이동
    @GetMapping("/login")
    public String login()
    {
        return "app_user/login";
    }
    
    // 로그인 함수
    @PostMapping("/login")
    public ModelAndView doLogin(String userId, String pwd, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        
        try
        {
            User user = userService.userLogin(userId, pwd);
            session.setAttribute(VanityFairSessionUtil.USER_SESSION_KEY, user);
            
            view.setViewName(vfConf.getHome());
            view.addObject("login_result", "Y");
            
            return view;
        }
        catch (IllegalStateException e)
        {
            view.setViewName("app_user/login");
            view.addObject("login_result", "N");
            
            return view;
        }
    }
    
    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute(VanityFairSessionUtil.USER_SESSION_KEY);
        session.invalidate();
        return vfConf.getHome();
    }
    
}
