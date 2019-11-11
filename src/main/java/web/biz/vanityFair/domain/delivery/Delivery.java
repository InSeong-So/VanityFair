package web.biz.vanityFair.domain.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class Delivery
{
    @Id
    @Column(name = "delivery_cd", columnDefinition = "varchar(20)")
    private String deliveryCd;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_num", columnDefinition = "varchar(20)")
    private Courier courier;
    
    @Column(name = "category_cd1", columnDefinition = "varchar(20)")
    private String categoryCd1;
    
    @Column(name = "category_cd2", columnDefinition = "varchar(20)")
    private String categoryCd2;
    
    @Column(name = "delivery_mon", columnDefinition = "int(255)")
    private int deliveryMon;
    
    @Column(name = "delivery_ymd", columnDefinition = "varchar(8)")
    private String deliveryYmd;
}
