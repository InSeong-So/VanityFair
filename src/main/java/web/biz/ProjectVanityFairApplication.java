package web.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import core.conf.VanityFairConfigure;

@SpringBootApplication
//@ComponentScan
@EnableJpaAuditing
@EnableConfigurationProperties(VanityFairConfigure.class)
public class ProjectVanityFairApplication
{
    
    //    public static final String APPLICATION_LOCATIONS = "spring.config.location=" + "classpath:application.yml," + "/app/config/springboot-application/opr-application.yml";
    
    //    public static void main(String[] args)
    //    {
    //        new SpringApplicationBuilder(ProjectVanityFairApplication.class).properties(APPLICATION_LOCATIONS).run(args);
    //    }
    
    public static void main(String[] args)
    {
                SpringApplication.run(ProjectVanityFairApplication.class, args);
//        try (ConfigurableApplicationContext ctx = SpringApplication.run(ProjectVanityFairApplication.class, args))
//        {
//        }
//        
    }
    
}
