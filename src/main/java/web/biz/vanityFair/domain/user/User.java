package web.biz.vanityFair.domain.user;

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
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User
{
    @Column(name = "seq_no")
    private long seqNo;
    
    @Column(name = "user_id", columnDefinition = "varchar(20)")
    private String userId;
    
    @Id
    //    @EqualsAndHashCode.Include
    @Column(name = "user_cd", columnDefinition = "varchar(20)")
    private String userCd;
    
    @Column(name = "pwd", nullable = false, columnDefinition = "varchar(100)")
    private String pwd;
    
    @Column(name = "reg_date", nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;
    
    @Column(name = "mod_date", nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;
    
    public boolean matchPwd(String pwd)
    {
        return this.pwd.equals(pwd);
    }
    
    public void update(User target)
    {
        if (!matchPwd(pwd))
        {
            return;
        }
        
        this.userId = target.userId;
    }
    
    public static final GuestUser GUEST_USER = new GuestUser();
    
    public boolean isGuestUser()
    {
        return false;
    }
    
    private static class GuestUser extends User
    {
        @Override
        public boolean isGuestUser()
        {
            return true;
        }
    }
}
