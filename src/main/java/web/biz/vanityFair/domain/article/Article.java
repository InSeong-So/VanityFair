package web.biz.vanityFair.domain.article;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web.biz.vanityFair.domain.user.User;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Article
{
    // 게시글 코드
    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "varchar(40)")
    private String articleCd;
    
    // 채번
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long seqNo;
    
    // 유저 코드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd")
    private User user;
    
    // 등록 유저 아이디
    @Column(columnDefinition = "varchar(20)")
    private String userId;
    
    // 유저 게시글 번호
    @Column(columnDefinition = "bigint")
    @ColumnDefault("0")
    private long userArtiNo;
    
    // 게시글 분류
    @Column(columnDefinition = "varchar(100)")
    private String articleCategory;
    
    // 게시글 계층
    @Column(columnDefinition = "int(5)")
    @ColumnDefault("0")
    private int articleLayer;
    
    // 게시글 비밀번호
    @Column(columnDefinition = "varchar(200)")
    private String articlePwd;
    
    // 게시글 삭제 여부
    @Column(columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String articleDelYn;
    
    // 게시글 제목
    @Column(columnDefinition = "varchar(100)")
    private String title;
    
    // 게시글 내용
    @Column(columnDefinition = "varchar(4000)")
    private String content;
    
    // 게시글 조회수
    @Column(columnDefinition = "bigint")
    @ColumnDefault("0")
    private long viewCnt;
    
    // 게시글 등록 IP
    @Column(columnDefinition = "varchar(40)")
    private String regIp;
    
    // 게시글 파일 등록코드
    @Column(columnDefinition = "varchar(1000)")
    private String fileCd;
    
    // 게시글 등록일자(String)
    @Column(nullable = false, columnDefinition = "varchar(12)")
    private String regDateStr;
    
    // 게시글 등록일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
    
    // 게시글 변경일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;
}
