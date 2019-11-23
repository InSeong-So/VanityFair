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
public class UserCart
{
    // 유저 장바구니 코드 
    @Id
    @Column(columnDefinition = "varchar(40)")
    private String userCartCd;
    
    // 유저 장바구니 채번
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long seqNo;
    
    // 유저 코드
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd")
    private User user;
    
    // 상품 코드
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cd", columnDefinition = "varchar(20)")
    private Product product;
    
    // 상품 이름
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String productNm;
    
    // 카트 총 수량
    @Column(nullable = false, columnDefinition = "int(10)")
    @ColumnDefault("0")
    private int cartQty;
    
    // 카트 총 가격
    @Column(nullable = false, columnDefinition = "int(100)")
    @ColumnDefault("0")
    private long cartPrice;
    
    // 등록일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
