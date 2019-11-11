package web.biz.vanityFair.domain.article.child;

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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.biz.vanityFair.domain.article.Article;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetail
{
    @Id
    @Column(name = "seq_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqNo;
    
    @OneToOne
    @JoinColumn(name = "article_cd")
    private Article article;
    
    @Column(name = "title", nullable = false, columnDefinition = "varchar(100)")
    private String title;
    
    @Column(name = "content", nullable = false, columnDefinition = "varchar(4000)")
    private String content;
    
    @Column(name = "user_id", nullable = false, columnDefinition = "varchar(20)")
    private String userId;
    
    @Column(name = "view_cnt", nullable = false, columnDefinition = "bigint")
    private long viewCnt;
    
    @Column(name = "reg_ip", nullable = false, columnDefinition = "varchar(40)")
    @ColumnDefault("'127.0.0.1'")
    private String regIp;
    
    @Column(name = "file_cd", nullable = true, columnDefinition = "varchar(1000)")
    private String fileCd;
    
    @Column(name = "reg_date_str", nullable = false, columnDefinition = "varchar(12)")
    private String regDateStr;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
    
    @UpdateTimestamp
    @Column(name = "mod_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime modDate;
    
}
