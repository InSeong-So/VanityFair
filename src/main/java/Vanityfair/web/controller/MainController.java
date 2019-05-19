package Vanityfair.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Vanityfair.web.domain.qna.QuestionRepository;
import Vanityfair.web.service.PostsService;
import Vanityfair.web.service.QnaService;
import lombok.AllArgsConstructor;

@Controller
public class MainController
{
    @Resource(name = "qnaService")
    private QnaService qnaService;
    
    @GetMapping("/")
    public String main(Model model)
    {
        model.addAttribute("questions", qnaService.findAll());
        return "home";
    }
    
}
