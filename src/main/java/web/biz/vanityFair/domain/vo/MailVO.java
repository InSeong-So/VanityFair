package web.biz.vanityFair.domain.vo;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailVO
{
    private String from;
    
    private String to;
    
    private String subject;
    
    private String text;
    
    private String userId;
    
    private String key;
    
    private Map<String, Object> model;
}
