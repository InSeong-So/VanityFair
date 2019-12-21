package web.biz.vanityFair.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User {
    public static final GuestUser GUEST_USER = new GuestUser();
    // 채번
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long seqNo;
    // 유저 코드
    @Id
    @Column(columnDefinition = "varchar(40)")
    private String userCd;
    // 유저 아이디
    @Column(columnDefinition = "varchar(20)")
    private String userId;
    // 유저 비밀번호
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String pwd;
    // 유저 이름
    @Column(columnDefinition = "varchar(100)")
    private String userNm;

    // 유저 메일 인증 코드
    //    @Column
    //    private String mailCd;

    // 유저 성별
    @Column(columnDefinition = "varchar(2)")
    private String userGender;
    // 유저 이메일
    @Column(columnDefinition = "varchar(200)")
    private String userMail;
    // 유저 인증 상태
    @Column(nullable = false, columnDefinition = "int(2)")
    @ColumnDefault("0")
    private int authStat;
    // 인증일자
    @Column(columnDefinition = "varchar(9)")
    private String certDate;
    // 인증 유효일자
    @Column(columnDefinition = "varchar(9)")
    private String valiDate;
    // 유저 등급
    @Column(columnDefinition = "varchar(1)")
    @ColumnDefault("'B'")
    private String userGrade;
    // 유저 우편번호
    @Column(columnDefinition = "varchar(20)")
    private String userZipCd;
    // 유저 주소
    @Column(columnDefinition = "varchar(400)")
    private String userAddr;
    // 유저 상세주소
    @Column(columnDefinition = "varchar(400)")
    private String userDtlAddr;
    // 유저 연락처
    @Column(columnDefinition = "varchar(13)")
    private String userPhoneNumber;
    // 유저 최근배송지
    @Column(columnDefinition = "varchar(1000)")
    private String userRecent;
    // 유저 등록배송지 1
    @Column(columnDefinition = "varchar(1000)")
    private String userRegA;
    // 유저 등록배송지 2
    @Column(columnDefinition = "varchar(1000)")
    private String userRegB;
    // 유저 등록배송지 3
    @Column(columnDefinition = "varchar(1000)")
    private String userRegC;
    // 최종 로그인일자
    @Column(columnDefinition = "varchar(30)")
    private String userLastLogin;
    // 가입일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime userRegDate;
    // 최근 비밀번호
    @Column(columnDefinition = "varchar(300)")
    private String userRecentPwd;
    // 로그인 실패 횟수
    @Column(columnDefinition = "int(1)")
    @ColumnDefault("0")
    private int userErrCnt;
    // 비밀번호 변경일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime pwdModDate;
    // 탈퇴여부
    @Column(nullable = false, columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String retireYn;
    // 유저 상태 코드
    @Column(columnDefinition = "varchar(20)")
    @ColumnDefault("'10'")
    private String userStatCd;
    // 유저 상태 이름
    @Column(columnDefinition = "varchar(20)")
    @ColumnDefault("'활동계정'")
    private String userStatNm;
    // 소셜 가입 여부
    @Column(columnDefinition = "varchar(20)")
    @ColumnDefault("'n'")
    private String userSocialYn;
    // 소셜 가입 방법
    @Column(columnDefinition = "varchar(20)")
    @ColumnDefault("'none'")
    private String userSocialWay;
    // 관리자 여부
    @Column(columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String adminYn;
    // 변경일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;

    public boolean matchPwd(String pwd) {
        return this.pwd.equals(pwd);
    }

    public void update(User target) {
        if (!matchPwd(pwd)) {
            return;
        }

        this.userId = target.userId;
    }

    public boolean isGuestUser() {
        return false;
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}
