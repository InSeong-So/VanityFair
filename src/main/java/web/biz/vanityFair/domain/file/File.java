package web.biz.vanityFair.domain.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class File {
    // 채번
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long seqNo;

    // 파일 코드
    @Id
    @Column
    private String fileCd;

    // 파일명
    @Column(columnDefinition = "varchar(4000)")
    private String fileNm;

    // 파일 등록 분류
    @Column(columnDefinition = "varchar(100)")
    private String fileCategory;

    // 파일 경로
    @Column(columnDefinition = "varchar(4000)")
    private String filePath;

    // 등록 유저 아이디
    @Column(columnDefinition = "varchar(20)")
    private String userId;

    // 파일 등록일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime regDate;

    // 파일 변경일자
    @Column(nullable = false, columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime modDate;
}
