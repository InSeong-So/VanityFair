package web.biz.vanityFair.domain.depot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
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
public class Depot
{
    @Id
    @Column(name="depot_cd", columnDefinition = "varchar(20)")
    private String depotCd;
    
    @Column(name="depot_addr", nullable = false, columnDefinition = "varchar(150)")
    private String depotAddr;
    
    @Column(name="use_yn", nullable = false, columnDefinition = "varchar(2)")
    @ColumnDefault("'n'")
    private String useYn;
    
    @Column(name="depot_numb", columnDefinition = "varchar(20)")
    private String depotNumb;
    
    @Column(name="depot_rate", nullable = false, columnDefinition = "int(10)")
    @ColumnDefault("0")
    private int depotRate;
}
