package web.biz.vanityFair.service.user.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.child.UserDetail;
import web.biz.vanityFair.repository.user.child.UserDetailRepository;
import web.common.core.component.VanityFairExtends;

@Service
@Component
public class UserDetailService extends VanityFairExtends
{
    @Autowired
    private UserDetailRepository userDetailRepository;
    
    public void userDetailInit(User user)
    {
        UserDetail ud = UserDetail.builder().user(user).build();
        
        userDetailRepository.save(ud);
    }
    
    public UserDetail userDetailSelect(User user)
    {
        UserDetail ud = userDetailRepository.findByUser(user).get();
        return ud;
    }
    
    public void userProfileUpdate(UserDetail user)
    {
        userDetailRepository.userDetailUpdate(user.getUserNm(), user.getUserZipCd(), user.getUserAddr(), user.getUserDtlAddr(), user.getUserPhoneNumber(), user.getUser());
    }
    
    public void userMailUpdate(String userMail, User user)
    {
        userDetailRepository.userMailUpdate(userMail, user);
    }
    
}
