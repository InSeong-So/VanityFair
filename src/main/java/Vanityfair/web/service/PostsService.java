package Vanityfair.web.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import Vanityfair.web.domain.posts.PostsRepository;
import Vanityfair.web.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService
{
    private PostsRepository postsRepository;
    
    @Transactional
    public Long save(PostsSaveRequestDto dto)
    {
        return postsRepository.save(dto.toEntity()).getId();
    }
}
