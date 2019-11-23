package web.common.core.util;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import web.common.core.crypto.Crypto;

@Slf4j
@Component
public class SisEncUtil
{
    protected static SisEncUtil instance = new SisEncUtil();
    
    public static SisEncUtil getInstance()
    {
        return instance;
    }
    
    // TODO : 현재 암호화 시 null 값이 나옴 ㅠ
    /**
     * 비밀번호 단방향 암호화
     * 
     * @param input_data
     * @return
     */
    public static String HMAC(String input_data)
    {
        if (input_data == null || input_data == "")
        {
            return input_data;
        }
        String result = null;
        try
        {
            result = Crypto.getInstance().getHash("HMAC").hash(input_data);
            
        }
        catch (Exception e)
        {
            log.error("SpinApi HMAC Encrypt Err!! - < Detail : ", e.getMessage() + " />");
            result = input_data;
        }
        return result;
    }
    
    /**
     * 비밀번호 단방향 암호화
     * 
     * @param input_data
     * @return
     */
    public static String SHA1(String input_data)
    {
        if (input_data == null || input_data == "")
        {
            return input_data;
        }
        String result = null;
        try
        {
            result = Crypto.getInstance().getHash("SHA1").hash(input_data);
            
        }
        catch (Exception e)
        {
            log.error("SpinApi SHA1 Encrypt Err!! - < Detail : ", e.getMessage() + " />");
            result = input_data;
        }
        return result;
    }
    
    /**
     * 비밀번호 단방향 암호화
     * 
     * @param input_data
     * @return
     */
    public static String SHA256(String input_data)
    {
        if (input_data == null || input_data == "")
        {
            return input_data;
        }
        String result = null;
        try
        {
            result = Crypto.getInstance().getHash("SHA256").hash(input_data);
            
        }
        catch (Exception e)
        {
            log.error("SpinApi SHA256 Encrypt Err!! - < Detail : ", e.getMessage() + " />");
            result = input_data;
        }
        return result;
    }
}
