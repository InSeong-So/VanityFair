package web.biz.vanityFair.domain.user;

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
import web.biz.vanityFair.domain.product.Product;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class UserInterest
{
    @Id
    @Column(name = "user_interest_cd")
    private String userInterestCd;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd")
    private User user;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cd", columnDefinition = "varchar(20)")
    private Product product;
    
    @Column(name = "product_nm", nullable = false, columnDefinition = "varchar(100)")
    private String productNm;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
