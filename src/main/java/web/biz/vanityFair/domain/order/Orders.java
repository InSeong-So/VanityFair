package web.biz.vanityFair.domain.order;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web.biz.vanityFair.domain.delivery.Delivery;
import web.biz.vanityFair.domain.user.User;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Orders
{
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "orders_cd", columnDefinition = "varchar(20)")
    private String ordersCd;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd", columnDefinition = "varchar(20)")
    private User user;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_cd", columnDefinition = "varchar(20)")
    private Delivery delivery;
    
    @Column(name = "order_stat_cd", columnDefinition = "varchar(20)")
    private String orderStatCd;
    
    @Column(name = "order_ymd", columnDefinition = "varchar(8)")
    private String orderYmd;
    
    @Column(name = "order_addr", columnDefinition = "varchar(200)")
    private String orderAddr;
    
    @Column(name = "order_numb", columnDefinition = "varchar(20)")
    private String orderNumb;
    
    @Column(name = "order_way_cd", columnDefinition = "varchar(20)")
    private String orderWayCd;
    
    @Column(name = "pay_way_cd", columnDefinition = "varchar(20)")
    private String payWayCd;
    
    @Column(name = "note", columnDefinition = "varchar(1000)")
    private String note;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
