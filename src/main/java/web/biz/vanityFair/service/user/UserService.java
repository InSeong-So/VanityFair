package web.biz.vanityFair.service.user;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.util.VanityFairEncUtil;
import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.child.UserDetail;
import web.biz.vanityFair.repository.user.UserRepository;
import web.biz.vanityFair.service.user.child.UserDetailService;
import web.biz.vanityFair.service.user.child.UserMailService;
import web.biz.vanityFair.service.user.child.UserSystemService;
import web.common.core.component.VanityFairExtends;
import web.common.core.exception.VanityFairRException;

@Slf4j
@Service
public class UserService extends VanityFairExtends
{
    //    User user = new User();
    //    
    //    UserDetail userDetail = new UserDetail();
    //    
    //    UserSystem userSystem = new UserSystem();
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserSystemService us;
    
    @Autowired
    private UserDetailService ud;
    
    @Autowired
    private UserMailService um;
    
    /**
     * 유저 로그인
     * 
     * @param userId
     * @param pwd
     * @return
     */
    public User userLogin(String userId, String pwd)
    {
        Optional<User> checkUser = userRepository.findByUserId(userId);
        
        if (!checkUser.isPresent())
        {
            throw new IllegalStateException();
        }
        
        User user = checkUser.get();
        
        if (!user.matchPwd(VanityFairEncUtil.SHA256(pwd)))
        {
            throw new IllegalStateException("비밀번호가 옳지 않습니다.");
        }
        else
        {
            return user;
        }
        
    }
    
    /**
     * 회원가입
     * 
     * @param user
     * @return
     */
    @Transactional
    public boolean userRegsistration(User user)
    {
        Optional<User> asCheckUser = userRepository.findByUserId(user.getUserId());
        
        if (asCheckUser.isPresent())
        {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        
        try
        {
            User regUSer = User.builder().seqNo(userRepository.getSeqNo() + 1).userId(user.getUserId()).userCd(codeCreator()).pwd(VanityFairEncUtil.SHA256(user.getPwd())).build();
            
            userRepository.save(regUSer);
            
            us.userSystemInit(regUSer);
            
            ud.userDetailInit(regUSer);
            
            return true;
        }
        catch (Exception e)
        {
            log.error("userRegsistration ERROR : " + e.getMessage());
            throw new VanityFairRException("asdf");
        }
        
    }
    
    /**
     * 유저 상세 정보 조회
     * 
     * @param user
     * @return
     */
    public UserDetail returnUserDetailSelect(User user)
    {
        return ud.userDetailSelect(user);
    }
    
    /**
     * 기본 프로필 변경
     * 
     * @param user
     */
    public void userProfileUpdate(UserDetail user)
    {
        ud.userProfileUpdate(user);
    }
    
    /**
     * 이메일 인증코드 전송
     * 
     * @param user
     */
    public void userMailCertSend(User user, String certKey)
    {
        um.userMailCertSend(user, certKey);
    }
    
    public void userMailDelete(User user)
    {
        um.userMailDelete(user);
    }
    
    /**
     * 이메일 인증 후처리
     * 
     * @param user
     */
    @Transactional
    public boolean userMailCertified(User user, Map<String, Object> params)
    {
        if (um.userMailCertified(user, params))
        {
            try
            {
                ud.userMailUpdate((String) params.get("userMail"), user);
                return true;
            }
            catch (Exception e)
            {
                throw new VanityFairRException("유저 이메일 등록 중 오류가 발생했습니다. 사유 : " + e.getMessage());
            }
        }
        
        return false;
    }
    
    /**
     * 비밀번호 변경
     * 
     * @param user
     */
    @Transactional
    public boolean userPwdChange(User user, Map<String, Object> params)
    {
        String changePwd = VanityFairEncUtil.SHA256((String)params.get("chaPwd"));
        
        userRepository.setModifiedPwd(changePwd, user.getUserCd());
        
        return true;
    }
}
