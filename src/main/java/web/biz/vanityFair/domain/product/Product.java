package web.biz.vanityFair.domain.product;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.biz.vanityFair.domain.depot.Depot;
import web.biz.vanityFair.domain.manufacture.Manufacture;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Product
{
    @Id
    @Column(name = "product_cd", columnDefinition = "varchar(20)")
    private String productCd;
    
    @Column(name = "seq_no")
    private long seqNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depot_cd", columnDefinition = "varchar(20)")
    private Depot depot;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_cd", columnDefinition = "varchar(40)")
    private Manufacture manufacture;
    
    @Column(name = "product_qty", nullable = false, columnDefinition = "int(100)")
    @ColumnDefault("0")
    private long productQty;
    
    @Column(name = "product_price", nullable = false, columnDefinition = "int(255)")
    @ColumnDefault("0")
    private long productPrice;
    
    @Column(name = "product_discount", nullable = false, columnDefinition = "int(4)")
    @ColumnDefault("0")
    private int productDiscount;
    
    @Column(name = "open_yn", nullable = false, columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String openYn;
    
    @Column(name = "start_ymd", nullable = false, columnDefinition = "varchar(8)")
    @ColumnDefault("19010101")
    private String startYmd;
    
    @Column(name = "end_ymd", nullable = false, columnDefinition = "varchar(8)")
    @ColumnDefault("99991231")
    private String endYmd;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
    
    // 유저 상세 데이터 변경일자
    @Column(name = "mod_date", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;
}
