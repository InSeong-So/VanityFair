package web.biz.vanityFair.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import web.biz.vanityFair.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long>
{
    // 유저 아이디 확인(로그인시, 회원가입시)
    Optional<User> findByUserId(String userId);
    
    // seq_no 채번
    @Query(value="SELECT CASE WHEN MAX(u.seqNo) IS NULL THEN 0 ELSE u.seqNo END AS seqNo FROM User u")
    long getSeqNo();
    
    // 비밀번호 변경
    @Modifying
    // Native Query = false 'default', JPQL
    @Query(value="UPDATE User u SET u.pwd = :pwd WHERE u.userCd = :user_cd")
    int setModifiedPwd(@Param("pwd") String pwd, @Param("user_cd") String user_cd);
}
