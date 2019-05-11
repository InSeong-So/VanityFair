package Vanityfair.web.domain.posts;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import Vanityfair.web.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "VP0000")
public class Posts extends BaseTimeEntity
{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VP0000")
    @SequenceGenerator(sequenceName = "SEQ_VP0000", allocationSize = 1, name = "SEQ_VP0000")
    private Long id;
    
    @Column(length = 500, nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @Column
    private String author;
    
    @Builder
    public Posts(String title, String content, String author)
    {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
}
