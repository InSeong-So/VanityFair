package web.biz.vanityFair.domain.user.child;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import web.biz.vanityFair.domain.product.Product;
import web.biz.vanityFair.domain.user.User;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class UserCart
{
    @Id
    @Column(name = "user_cart_cd", columnDefinition = "varchar(20)")
    private String userCartCd;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd")
    private User user;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cd", columnDefinition = "varchar(20)")
    private Product product;
    
    @Column(name = "product_nm", nullable = false, columnDefinition = "varchar(100)")
    private String productNm;
    
    @Column(name = "cart_qty", nullable = false, columnDefinition = "int(10)")
    @ColumnDefault("1")
    private int cartQty;
    
    @Column(name = "cart_price", nullable = false, columnDefinition = "int(100)")
    @ColumnDefault("0")
    private long cartPrice;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
