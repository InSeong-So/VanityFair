package web.biz.vanityFair.domain.vo;

import lombok.Data;

@Data
public class ArticleList
{
    private final long seqNo;
    
    private final String title;
    
    private final String content;
    
    private final String userId;
    
    private final String regDate;
    
    private final long viewCnt;
    
    public static class Builder
    {
        private long seqNo = 0;
        
        private String title = "";
        
        private String content = "";
        
        private String userId = "";
        
        private String regDate = "";
        
        private long viewCnt = 0;
        
        public Builder seqNo(long val)
        {
            seqNo = val;
            return this;
        }
        
        public Builder title(String val)
        {
            title = val;
            return this;
        }
        
        public Builder content(String val)
        {
            content = val;
            return this;
        }
        
        public Builder userId(String val)
        {
            userId = val;
            return this;
        }
        
        public Builder regDate(String val)
        {
            regDate = val;
            return this;
        }
        
        public Builder viewCnt(long val)
        {
            viewCnt = val;
            return this;
        }
        
        public ArticleList build()
        {
            return new ArticleList(this);
        }
    }
    
    public ArticleList(Builder builder)
    {
        seqNo = builder.seqNo;
        title = builder.title;
        content = builder.content;
        userId = builder.userId;
        regDate = builder.regDate;
        viewCnt = builder.viewCnt;
    }
    
}
