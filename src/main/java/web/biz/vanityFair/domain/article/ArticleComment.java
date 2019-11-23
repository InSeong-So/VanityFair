package web.biz.vanityFair.domain.article;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class ArticleComment
{
    // 채번
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seqNo;
    
    // 게시글 코드
    @OneToOne
    @JoinColumn(name = "article_cd")
    private Article article;
    
    // 댓글 내용
    @Column(nullable = false, columnDefinition = "varchar(4000)")
    private String comment;
    
    // 댓글 번호
    @Column(nullable = false)
    private long commentNo;
    
    // 댓글 등록 유저 아이디
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String userId;
    
    // 댓글 등록 IP
    @Column(nullable = false, columnDefinition = "varchar(40)")
    @ColumnDefault("'127.0.0.1'")
    private String regIp;
    
    // 댓글 등록일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
