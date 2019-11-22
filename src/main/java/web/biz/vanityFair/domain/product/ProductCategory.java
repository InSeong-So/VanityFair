package web.biz.vanityFair.domain.product;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class ProductCategory
{
    @Id
    @Column(name = "product_category_cd", columnDefinition = "varchar(20)")
    private String productCategoryCd;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cd", columnDefinition = "varchar(20)")
    private Product product;
    
    @Column(name = "seq_no")
    private long seqNo;
    
    @Column(name = "product_type", nullable = false, columnDefinition = "varchar(20)")
    private String productType;
    
    @Column(name = "top_product_type", nullable = false, columnDefinition = "varchar(20)")
    private String topProductType;
    
    @Column(name = "top_product_category_cd", nullable = false, columnDefinition = "varchar(20)")
    private String topProductCategoryCd;
    
    @Column(name = "etc1", columnDefinition = "varchar(1000)")
    private String etc1;
    
    @Column(name = "etc2", columnDefinition = "varchar(1000)")
    private String etc2;
    
    @Column(name = "etc3", columnDefinition = "varchar(1000)")
    private String etc3;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
