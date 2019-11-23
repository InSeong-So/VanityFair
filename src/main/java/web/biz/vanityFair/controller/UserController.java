package web.biz.vanityFair.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.service.user.UserService;
import web.common.core.util.SisSessionUtil;

@Slf4j
@Controller
@RequestMapping
public class UserController
{
    private String menuName = "app_user/myprofile";
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/registration")
    public String registration()
    {
        return "app_user/registration";
    }
    
    @PostMapping("/registration")
    public String doRegistration(User user)
    {
        log.info("user 가입 정보 : " + user);
        
        if (userService.userRegsistration(user))
        {
            return "redirect:/login";
        }
        
        return "app_user/registration_failed";
    }
    
    @GetMapping("/myprofile/{myprofileNumbers}")
    public ModelAndView myProfile_left(@PathVariable String myprofileNumbers, HttpSession session)
    {
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        ModelAndView view = new ModelAndView();
        
        view.setViewName(menuName);
        view.addObject("userProfile", user);
        view.addObject("leftMenu", myprofileNumbers);
        
        return view;
    }
    
    @PostMapping("/myprofile/{myprofileNumbers}/update")
    public ModelAndView doMyProfileUpdate(@PathVariable String myprofileNumbers, HttpSession session, String mailCd)
    {
        ModelAndView view = new ModelAndView();
        
        switch (myprofileNumbers)
        {
            // 개인정보변경
            case "01":
                break;
            case "02":
                break;
            case "03":
                break;
            case "04":
                break;
            case "05":
                break;
            case "06":
                break;
            case "07":
                break;
            case "08":
                break;
        }
        
        return view;
    }
    
}
