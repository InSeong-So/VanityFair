package web.biz.vanityFair.domain.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
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
public class ArticleAttribute
{
    @Id
    @Column(name = "seq_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqNo;
    
    @OneToOne
    @JoinColumn(name = "article_cd")
    private Article article;
    
    @Column(name = "article_category", nullable = false, columnDefinition = "varchar(100)")
    private String articleCategory;
    
    @Column(name = "article_layer", nullable = false, columnDefinition = "int(5)")
    @ColumnDefault("0")
    private int articleLayer;
    
    @Column(name = "article_pwd", columnDefinition = "varchar(200)")
    private String articlePwd;
    
    @Column(name = "article_del_yn", nullable = false, columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String articleDelYn;
}
