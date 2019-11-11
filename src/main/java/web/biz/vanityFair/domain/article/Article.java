package web.biz.vanityFair.domain.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "article_cd", columnDefinition = "varchar(20)")
    private String articleCd;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_no")
    private long seqNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd")
    private User user;
    
    @Column(name = "user_arti_no", nullable = false, columnDefinition = "bigint")
    @ColumnDefault("0")
    private long userArtiNo;
    
}
