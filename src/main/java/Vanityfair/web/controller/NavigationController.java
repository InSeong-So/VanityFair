package Vanityfair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Vanityfair.web.service.PostsService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/navigation")
public class NavigationController
{
    private PostsService postsService;
    
    @GetMapping("/introduce")
    public String introduce()
    {
        return "/introduce";
    }
    
    @GetMapping("/cost")
    public String cost()
    {
        return "/cost";
    }
    
    @GetMapping("/posts")
    public String posts(Model model)
    {
        model.addAttribute("posts", postsService.findAllDesc());
        return "/posts";
    }
    
    @GetMapping("/qna")
    public String qna()
    {
        return "/qna";
    }
    
    @GetMapping("/reservation")
    public String reservation()
    {
        return "/reservation";
    }
    
    @GetMapping("/login")
    public String login()
    {
        return "/customers/login";
    }
}
