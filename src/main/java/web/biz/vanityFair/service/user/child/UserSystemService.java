package web.biz.vanityFair.service.user.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import core.util.VanityFairStringUtil;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.child.UserSystem;
import web.biz.vanityFair.repository.user.child.UserSystemRepository;

@Service
@Component
public class UserSystemService
{
    @Autowired
    private UserSystemRepository userSystemRepository;
    
    public void userSystemInit(User user)
    {
        UserSystem us = UserSystem
                .builder()
                .user(user)
                .userLastLogin(VanityFairStringUtil.getYmd())
                .build();
        
        userSystemRepository.save(us);
    }
}
