package web.biz.vanityFair.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.UserSystem;
import web.biz.vanityFair.repository.user.UserSystemRepository;
import web.common.core.util.SisStringUtil;

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
                .userLastLogin(SisStringUtil.getYmd())
                .build();
        
        userSystemRepository.save(us);
    }
}
