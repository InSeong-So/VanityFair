package Vanityfair.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Vanityfair.support.exception.UnAuthorizationException;
import Vanityfair.support.security.HttpSessionUtils;
import Vanityfair.web.domain.user.User;
import Vanityfair.web.service.UserService;

@Controller
public class LoginController
{
    @Resource(name = "userService")
    private UserService userService;
    
    @GetMapping("/login")
    public String form()
    {
        return "/user/login";
    }
    
    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session)
    {
        try
        {
            User user = userService.login(userId, password);
            session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
            return "redirect:/";
        }
        catch (UnAuthorizationException e)
        {
            return "/user/login_failed";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }
}
