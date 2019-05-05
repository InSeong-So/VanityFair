package Vanityfair.web.domain;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseTimeEntity
{
    
    private LocalDateTime createdTime;
    
    private LocalDateTime modifiedTime;
}
