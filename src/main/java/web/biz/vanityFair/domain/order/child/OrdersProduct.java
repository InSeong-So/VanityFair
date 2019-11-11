package web.biz.vanityFair.domain.order.child;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.biz.vanityFair.domain.order.Orders;
import web.biz.vanityFair.domain.product.Product;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqNo;
    
    @OneToOne
    @JoinColumn(name = "orders_cd", columnDefinition = "varchar(20)")
    private Orders orders;
    
    @ManyToOne
    @JoinColumn(name = "product_cd", columnDefinition = "varchar(20)")
    private Product product;
    
    @Column(name = "order_cnt", columnDefinition = "int(100)")
    private int orderCnt;
    
    @Column(name = "order_price", columnDefinition = "int(100)")
    private int orderPrice;
}
