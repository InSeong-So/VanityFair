package web.biz.vanityFair.facade;

import web.biz.vanityFair.domain.user.User;

public interface UserInterface
{
    // 로그인
    public User userLogin(String userId, String pwd);
    
    // 회원가입
    public boolean userRegsistration(User user);
    
    // 개인정보 조회
    public User userProfileInquiry(String userId);
    
    // 개인정보 수정
    public void userProfileUpdate(User user);
    
    // 비밀번호변경
    public boolean userPwdChange(User user, String pwd);
    
}
