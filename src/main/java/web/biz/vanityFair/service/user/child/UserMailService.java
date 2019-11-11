package web.biz.vanityFair.service.user.child;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.util.VanityFairStringUtil;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.child.UserMail;
import web.biz.vanityFair.repository.user.child.UserMailRepository;
import web.common.core.exception.VanityFairRException;

@Service
public class UserMailService
{
    @Autowired
    private UserMailRepository userMailRepository;
    
    /**
     * 유저 이메일 인증 코드 전송
     * 
     * @param user
     * @param certKey
     * @return
     */
    public boolean userMailCertSend(User user, String certKey)
    {
        // userMail 빌드
        UserMail userMail = UserMail.builder().userId(user.getUserId()).user(user).seqNo(userMailRepository.getSeqNo() + 1).mailCd(certKey).certDate(VanityFairStringUtil.getYmd()).valiDate(VanityFairStringUtil.getYmd()).build();
        
        try
        {
            Optional<UserMail> mailCheck = userMailRepository.findById(user.getUserCd());
            
            if (mailCheck.isPresent())
                userMailRepository.delete(mailCheck.get());
            
            userMailRepository.save(userMail);
            return true;
        }
        catch (Exception e)
        {
            throw new VanityFairRException("메일 키 저장 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }
    }
    
    /**
     * 유저 메일코드 삭제
     * 
     * @return
     */
    public boolean userMailDelete(User user)
    {
        Optional<UserMail> mailCheck = userMailRepository.findById(user.getUserCd());
        userMailRepository.delete(mailCheck.get());
        
        return true;
    }
    
    /**
     * 유저 이메일 인증 완료 처리
     * 
     * @param user
     * @param params
     * @return
     */
    public boolean userMailCertified(User user, Map<String, Object> params)
    {
        if (userMailRepository.getMailCd(user).equals((String) params.get("mailCd")))
        {
            return true;
        }
        return false;
    }
}
