package Vanityfair.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Vanityfair.web.dto.posts.PostsSaveRequestDto;
import Vanityfair.web.service.PostsService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PostsRestController
{
    private PostsService postsService;
    
    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto)
    {
        return postsService.save(dto);
    }
}
