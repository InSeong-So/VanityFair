package web.biz.vanityFair.facade;

import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.vo.MailVO;

public interface UserInterface {
    // 로그인
    User userLogin(String userId, String pwd);

    // 회원가입
    boolean userRegsistration(User user);

    // 개인정보 조회
    User userProfileInquiry(String userId);

    // 개인정보 수정
    void userProfileUpdate(User user);

    // 비밀번호변경
    boolean userPwdChange(User user, String pwd);

    void userSendCertMail(String key, MailVO mailVO, boolean yn);

    void userSentMailDelete(User user);

    void userCertMailSuccess();

}
