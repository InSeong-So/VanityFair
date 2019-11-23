package web.biz.vanityFair.domain.system;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class SystemCode
{
    @Id
    @Column(name = "idx_cd", columnDefinition = "varchar(20)")
    private String idxCd;
    
    @Column(name = "idx_nm", columnDefinition = "varchar(100)")
    private String idxNm;
    
    @Column(name = "dp_order", columnDefinition = "int(100)")
    private int dpOrder;
    
    @Column(name = "cd_table_nm", columnDefinition = "varchar(100)")
    private String cdTableNm;
    
    @Column(name = "cd_column_nm", columnDefinition = "varchar(100)")
    private String cdColumnNm;
    
    @Column(name = "idx_column_nm", columnDefinition = "varchar(100)")
    private String idxColumnNm;
    
    @Column(name = "hold_yn", columnDefinition = "varchar(2)")
    private String holdYn;
    
    @Column(name = "use_yn", columnDefinition = "varchar(2)")
    private String useYn;
    
    @Column(name = "cond_seq_no1", columnDefinition = "varchar(20)")
    private String conSeqNo1;
    
    @Column(name = "cond_seq_no2", columnDefinition = "varchar(20)")
    private String conSeqNo2;
    
    @Column(name = "cond_seq_no3", columnDefinition = "varchar(20)")
    private String conSeqNo3;
    
    @Column(name = "cond_seq_no4", columnDefinition = "varchar(20)")
    private String conSeqNo4;
    
    @Column(name = "cond_seq_no5", columnDefinition = "varchar(20)")
    private String conSeqNo5;
    
    @Column(name = "note", columnDefinition = "varchar(1000)")
    private String note;
    
    @Column(name = "default_yn", columnDefinition = "varchar(2)")
    private String defaultYn;
    
    @Column(name = "use_edit_yn", columnDefinition = "varchar(2)")
    private String useEdityn;
    
    @Column(name = "con_update_yn", columnDefinition = "varchar(2)")
    private String conUpdateYn;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
    
    @Column(name = "mod_date", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;
}
