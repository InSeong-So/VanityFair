package web.biz.vanityFair.domain.manufacture;

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
public class Manufacture
{
    @Id
    @Column(name = "manufacture_cd", columnDefinition = "varchar(40)")
    private String manufactureCd;
    
    @Column(name = "manufacture_nm", columnDefinition = "varchar(100)")
    private String manufactureNm;
    
    @Column(name = "manufacture_numb", columnDefinition = "varchar(20)")
    private String manufactureNumb;
    
    @Column(name = "manufacture_zip_no", columnDefinition = "varchar(20)")
    private String manufactureZipNo;
    
    @Column(name = "manufacture_addr", columnDefinition = "varchar(150)")
    private String manufactureAddr;
    
    @Column(name = "bank_cd", columnDefinition = "varchar(40)")
    private String bankCd;
    
    @Column(name = "acc_no", columnDefinition = "varchar(40)")
    private String accNo;
    
    @Column(name = "depo_nm", columnDefinition = "varchar(40)")
    private String depoNm;
}
