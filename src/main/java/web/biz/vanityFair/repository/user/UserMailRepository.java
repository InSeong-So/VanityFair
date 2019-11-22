package web.biz.vanityFair.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.UserMail;

public interface UserMailRepository extends JpaRepository<UserMail, String>, CrudRepository<UserMail, String>
{
    // seq_no 채번
    @Query("SELECT CASE WHEN MAX(um.seqNo) IS NULL THEN 0 ELSE um.seqNo END AS seqNo FROM UserMail um")
    long getSeqNo();
    
    @Query("SELECT um.mailCd FROM UserMail um WHERE um.user = :user")
    String getMailCd(@Param("user") User user);
}
