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
public class UserDetail
{
    // 유저 상세정보 채번(상세정보 코드)
    @Id
    @Column(name = "seq_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqNo;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd")
    private User user;
    
    // 유저 이름
    @Column(name = "user_nm", columnDefinition = "varchar(100)")
    private String userNm;
    
    // 유저 성별
    @Column(name = "user_gender", columnDefinition = "varchar(2)")
    private String userGender;
    
    // 유저 이메일
    @Column(name = "user_mail", columnDefinition = "varchar(200)")
    private String userMail;
    
    // 유저 등급
    @Column(name = "user_grade", columnDefinition = "varchar(1)")
    @ColumnDefault("'B'")
    private String userGrade;
    
    // 유저 우편번호
    @Column(name = "user_zip_cd", columnDefinition = "varchar(20)")
    private String userZipCd;
    
    // 유저 주소
    @Column(name = "user_addr", columnDefinition = "varchar(400)")
    private String userAddr;
    
    // 유저 상세주소
    @Column(name = "user_dtl_addr", columnDefinition = "varchar(400)")
    private String userDtlAddr;
    
    // 유저 연락처
    @Column(name = "user_phone_number", columnDefinition = "varchar(13)")
    private String userPhoneNumber;
    
    // 유저 최근배송지
    @Column(name = "user_recent", columnDefinition = "varchar(1000)")
    private String userRecent;
    
    // 유저 등록배송지 1
    @Column(name = "user_reg_a", columnDefinition = "varchar(1000)")
    private String userRegA;
    
    // 유저 등록배송지 2
    @Column(name = "user_reg_b", columnDefinition = "varchar(1000)")
    private String userRegB;
    
    // 유저 등록배송지 3
    @Column(name = "user_reg_c", columnDefinition = "varchar(1000)")
    private String userRegC;
    
    // 유저 상세 데이터 변경일자
    @Column(name = "mod_date", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;
    
}
