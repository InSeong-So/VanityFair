package web.biz.vanityFair.domain.system;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

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
public class SystemCodeDetail
{
    @ManyToOne
    @JoinColumn(name = "idx_cd")
    private SystemCode systemCode;
    
    @Id
    @Column(name = "cd", columnDefinition = "varchar(20)")
    private String cd;
    
    @Column(name = "dp_order", columnDefinition = "int(100)")
    private int dpOrder;
    
    @Column(name = "smr_cd_nm", columnDefinition = "varchar(500)")
    private String smrCdNm;
    
    @Column(name = "eng_nm", columnDefinition = "varchar(4000)")
    private String engNm;
    
    @Column(name = "cnvt_cd", columnDefinition = "varchar(20)")
    private String cnvtCd;
    
    @Column(name = "start_ymd", columnDefinition = "varchar(8)")
    private String startYmd;
    
    @Column(name = "end_ymd", columnDefinition = "varchar(8)")
    private String endYmd;
    
    @Column(name = "use_yn", columnDefinition = "varchar(2)")
    private String useYn;
    
    @Column(name = "cond_cd1", columnDefinition = "varchar(20)")
    private String condCd1;
    
    @Column(name = "cond_cd2", columnDefinition = "varchar(20)")
    private String condCd2;
    
    @Column(name = "cond_cd3", columnDefinition = "varchar(20)")
    private String condCd3;
    
    @Column(name = "cond_cd4", columnDefinition = "varchar(20)")
    private String condCd4;
    
    @Column(name = "cond_cd5", columnDefinition = "varchar(20)")
    private String condCd5;
    
    @Column(name = "cond_start_cd", columnDefinition = "varchar(20)")
    private String condStartCd;
    
    @Column(name = "cond_end_cd", columnDefinition = "varchar(20)")
    private String condEndCd;
    
    @Column(name = "default_yn", columnDefinition = "varchar(2)")
    private String defaultYn;
    
    @Column(name = "num_val1", columnDefinition = "int(100)")
    private int numVal1;
    
    @Column(name = "num_val2", columnDefinition = "int(100)")
    private int numVal2;
    
    @Column(name = "num_val3", columnDefinition = "int(100)")
    private int numVal3;
    
    @Column(name = "image_path", columnDefinition = "varchar(20)")
    private String imagePath;
    
    @Column(name = "note", columnDefinition = "varchar(1000)")
    private String note;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
    
    @Column(name = "mod_date", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;
}
