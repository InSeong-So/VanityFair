package web.common.core.config;
//package web.biz.vanityFair.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
//import org.thymeleaf.spring5.ISpringTemplateEngine;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.templateresolver.ITemplateResolver;

// package web.biz.vanityFair.service.mail;

//@Configuration
//public class ThymeleafConfig
//{
    //    @Bean
    //    public SpringTemplateEngine springTemplateEngine() {
    //        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    //        templateEngine.addTemplateResolver(htmlTemplateResolver());
    //        return templateEngine;
    //    }
    //
    //    @Bean
    //    public SpringResourceTemplateResolver htmlTemplateResolver(){
    //        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
    //        emailTemplateResolver.setPrefix("classpath:/templates/");
    //        emailTemplateResolver.setSuffix(".html");
    //        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
    //        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    //        return emailTemplateResolver;
    //    }
//    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver)
//    {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new Java8TimeDialect());
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }
//}
