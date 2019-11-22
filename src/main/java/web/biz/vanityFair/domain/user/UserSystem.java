package web.biz.vanityFair.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class UserSystem
{
    @Id
    @Column(name = "seq_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqNo;
    
    @Column(name = "admin_yn", columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String adminYn;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd")
    private User user;
    
    // 최종 로그인일자
    @Column(name = "user_last_login", nullable = false, columnDefinition = "varchar(30)")
    private String userLastLogin;
    
    // 가입일자
    @Column(name = "user_reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime userRegDate;
    
    // 최근 비밀번호
    @Column(name = "user_recent_pwd", columnDefinition = "varchar(300)")
    private String userRecentPwd;
    
    // 로그인 실패 횟수
    @Column(name = "user_err_cnt", columnDefinition = "int(1)")
    @ColumnDefault("0")
    private int userErrCnt;
    
    // 비밀번호 변경일자
    @Column(name = "pwd_mod_date", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime pwdModDate;
    
    // 탈퇴여부
    @Column(name = "retire_yn", nullable = false, columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String retireYn;
    
    // 유저 상태 코드
    @Column(name = "user_stat_cd", columnDefinition = "varchar(20)")
    @ColumnDefault("'10'")
    private String userStatCd;
    
    // 유저 상태 이름
    @Column(name = "user_stat_nm", columnDefinition = "varchar(20)")
    @ColumnDefault("'활동계정'")
    private String userStatNm;
    
    // 관리자 여부
    @Column(name = "user_admin_yn", columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String userAdminYn;
    
    // 소셜 가입 여부
    @Column(name = "user_social_yn", columnDefinition = "varchar(20)")
    @ColumnDefault("'n'")
    private String userSocialYn;
    
    // 소셜 가입 방법
    @Column(name = "user_social_way", columnDefinition = "varchar(20)")
    @ColumnDefault("'none'")
    private String userSocialWay;
}
