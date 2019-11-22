package web.biz.vanityFair.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long>
{
    Optional<UserDetail> findByUser(User user);
    
    @Modifying
    @Transactional
    @Query("UPDATE UserDetail ud "
            + "SET "
            + "ud.userNm = :user_nm, "
//            + "ud.userGender = :user_gender, "
//            + "ud.userMail = :user_mail, "
            + "ud.userZipCd = :user_zip_cd, "
            + "ud.userAddr = :user_addr, "
            + "ud.userDtlAddr = :user_dtl_addr, "
            + "ud.userPhoneNumber = :user_phone_number "
//            + "u.userRecent = :user_recent, "
//            + "u.userRegA = :user_reg_a, "
//            + "u.userRegB = :user_reg_b, "
//            + "u.userRegC = :user_reg_c "
            + "WHERE "
            + "ud.user = :user")
    void userDetailUpdate(@Param("user_nm") String user_nm, @Param("user_zip_cd") String user_zip_cd, @Param("user_addr") String user_addr, @Param("user_dtl_addr") String user_dtl_addr, @Param("user_phone_number") String user_phone_number, @Param("user") User user);
    
    @Modifying
    @Query("UPDATE UserDetail ud "
           + "SET "
           +"ud.userMail = :user_mail "
           + "WHERE "
           + "ud.user = :user")
    void userMailUpdate(@Param("user_mail") String user_mail, @Param("user") User user);
}
