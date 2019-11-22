package web.biz.vanityFair.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class UserMail
{
    @Id
    private String userId;
    
    @Column(name = "seq_no")
    private long seqNo;
    
    @Column(name = "mail_cd")
    private String mailCd;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cd", columnDefinition = "varchar(20)")
    private User user;
    
    @Column(name = "auth_stat", nullable = false, columnDefinition = "int(2)")
    @ColumnDefault("0")
    private int authStat;
    
    @Column(name = "cert_date", nullable = false, columnDefinition = "varchar(9)")
    private String certDate;
    
    @Column(name = "vali_date", nullable = false, columnDefinition = "varchar(9)")
    private String valiDate;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
}
