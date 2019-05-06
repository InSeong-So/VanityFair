package Vanityfair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Vanityfair.web.service.PostsService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController
{
    
    @GetMapping("/")
    public String main()
    {
        return "home";
    }
    
}
