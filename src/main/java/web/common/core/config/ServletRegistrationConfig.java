package web.common.core.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.config.VanityFairServletConfig;

@Slf4j
@Configuration
public class ServletRegistrationConfig implements ServletContextInitializer
{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        registerServlet(servletContext);
        
    }
    
    @Bean
    public ServletRegistrationBean<VanityFairServletConfig> getServletRegistrationBean()
    {
        ServletRegistrationBean<VanityFairServletConfig> registrationBean = new ServletRegistrationBean<VanityFairServletConfig>(new VanityFairServletConfig());
        registrationBean.addUrlMappings("/vanity");
        return registrationBean;
    }
    
    private void registerServlet(ServletContext servletContext)
    {
        log.info(">>>>>>>>>> register Servlet <<<<<<<<<<");
        ServletRegistration.Dynamic serviceServlet = servletContext.addServlet("dispatcher", new VanityFairServletConfig());
        
        //        serviceServlet.addMapping("/*");
        serviceServlet.setAsyncSupported(true);
        serviceServlet.setLoadOnStartup(1);
    }
    
}
