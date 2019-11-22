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
    @Id
    @Column(name = "seq_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqNo;
    
    @OneToOne
    @JoinColumn(name = "article_cd")
    private Article article;
    
    @Column(name = "comment", nullable = false, columnDefinition = "varchar(4000)")
    private String comment;
    
    @Column(name = "comment_no", nullable = false)
    private long commentNo;
    
    @Column(name = "user_id", nullable = false, columnDefinition = "varchar(20)")
    private String userId;
    
    @Column(name = "reg_ip", nullable = false, columnDefinition = "varchar(40)")
    @ColumnDefault("'127.0.0.1'")
    private String regIp;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
