package web.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageConfig
{
    /**
     * 로케일 결정 빈 생성
     * 세션, 쿠키, 헤더, 고정값 리졸버가 존재함
     * 
     * @return
     */
    @Bean
    public LocaleResolver localeResolver()
    {
        //쿠키 기반 로케일 설정
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        // 쿠키명 설정
        cookieLocaleResolver.setCookieName("cookie_language");
        return cookieLocaleResolver;
    }
    
    /**
     * url 파라미터로 로케일 변경을 설정
     * https://localhost:8224?locale=kr
     * 
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor()
    {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("cookie_language");
        return lci;
    }
    
}
