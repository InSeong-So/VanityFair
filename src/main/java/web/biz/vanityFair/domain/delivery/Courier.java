package web.biz.vanityFair.domain.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Courier
{
    @Id
    @Column(name = "courier_num", columnDefinition = "varchar(20)")
    private String courierNum;
    
    @Column(name = "courier_nm", columnDefinition = "varchar(150)")
    private String courierNm;
    
    @Column(name = "courier_numb", columnDefinition = "varchar(150)")
    private String courierNumb;
    
    @Column(name = "courier_zip_no", columnDefinition = "varchar(150)")
    private String courierZipNo;
    
    @Column(name = "courier_addr", columnDefinition = "varchar(150)")
    private String courierAddr;
    
    @Column(name = "bank_cd", columnDefinition = "varchar(100)")
    private String bankCd;
    
    @Column(name = "acc_No", columnDefinition = "varchar(100)")
    private String accNo;
    
    @Column(name = "depo_nm", columnDefinition = "varchar(20)")
    private String depoNm;
    
}
