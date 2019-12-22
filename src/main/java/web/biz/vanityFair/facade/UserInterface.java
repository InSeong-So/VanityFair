package web.biz.vanityFair.facade;

import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.vo.MailVO;


/**
 * 1. 로그인을 한다.
 * 2. 회원가입을 한다.
 * 3. 프로필을 조회한다.
 * 4. 프로필을 수정한다.
 * 5. 비밀번호를 수정한다.
 * 6. 유저에게 메일을 보낸다.
 * 7. 유저에게 보낸 메일을 삭제한다?
 * 8. 유저에게 보낸 메일의 성공여부?
 */
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
