package web.biz.vanityFair.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * yml 변수등록 클래스
 *
 */
@Data
@Component
@PropertySource("classpath:vanityfair.yml")
@ConfigurationProperties
public class VanityFairConfigure implements WebMvcConfigurer {

    private String xmlPath;

    private String home;

    private String error;

    private String seed;

    private String spare;

    private String grade;

    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/images/**").addResourceLocations(uploadPath);
    }

}
