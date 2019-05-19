package Vanityfair.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Vanityfair.support.security.LoginUser;
import Vanityfair.web.domain.user.User;
import Vanityfair.web.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/form")
    public String form()
    {
        return "/user/form";
    }
    
    @PostMapping("")
    public String create(User user)
    {
        log.debug("create user : {}", user);
        userRepository.save(user);
        return "redirect:/users";
    }
    
    @GetMapping("")
    public String list(Model model)
    {
        List<User> users = userRepository.findAll();
        log.debug("user size : {}", users.size());
        model.addAttribute("users", users);
        return "/user/list";
    }
    
    @GetMapping("/{id}/form")
    public String updateForm(@LoginUser User loginUser, @PathVariable long id, Model model)
    {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent())
        {
            model.addAttribute("user", user.get());
            return "/user/updateForm";
        }
        else
        {
            return null;
        }
        
    }
    
    @PutMapping("/{id}")
    public String update(@LoginUser User loginUser, @PathVariable long id, User target)
    {
        Optional<User> original = this.userRepository.findById(id);
        if (original.isPresent())
        {
            original.get().update(loginUser, target);
            userRepository.save(original.get());
            return "redirect:/users";
        }
        else
        {
            return null;
        }
    }
}
