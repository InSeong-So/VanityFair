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
public class ProductImage
{
    @Id
    @Column(name = "product_image_cd", columnDefinition = "varchar(20)")
    private String productImageCd;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cd", columnDefinition = "varchar(20)")
    private Product product;
    
    @Column(name = "up_user_id", columnDefinition = "varchar(20)")
    private String upUserId;
    
    @Column(name = "org_img_nm", columnDefinition = "varchar(1000)")
    private String orgImgNm;
    
    @Column(name = "srv_img_nm", columnDefinition = "varchar(1000)")
    private String srvImgNm;
    
    @Column(name = "img_size", columnDefinition = "int(100)")
    private int imgSize;
    
    @Column(name = "img_width", columnDefinition = "int(20)")
    private int imgWidth;
    
    @Column(name = "img_height", columnDefinition = "int(20)")
    private int imgHeight;
    
    @Column(name = "img_extension", columnDefinition = "varchar(20)")
    private String imgExtension;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
