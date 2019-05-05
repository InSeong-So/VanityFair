package Vanityfair.web.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Vanityfair.support.exception.UnAuthorizationException;
import Vanityfair.web.domain.user.User;
import Vanityfair.web.domain.user.UserRepository;

@Service("userService")
public class UserService
{
    
    @Resource(name = "userRepository")
    private UserRepository userRepository;
    
    public User login(String userId, String password) throws UnAuthorizationException
    {
        Optional<User> exUser = userRepository.findByUserId(userId);
        if (!exUser.isPresent())
        {
            throw new UnAuthorizationException();
        }
        
        User user = exUser.get();
        if (!user.matchPassword(password))
        {
            throw new UnAuthorizationException();
        }
        
        return user;
    }
}
