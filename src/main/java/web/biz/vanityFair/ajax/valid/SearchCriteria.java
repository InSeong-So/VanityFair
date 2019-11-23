package web.biz.vanityFair.ajax.valid;

import javax.validation.constraints.NotBlank;

//TODO: 차후 수정
public class SearchCriteria
{
    @NotBlank(message = "elapsedTime can't empty!")
    String elapsedTime;
    
    public String getElapsedTime()
    {
        return elapsedTime;
    }
    
    public void setElapsedTime(String elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }
    
}
