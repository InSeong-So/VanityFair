package web.biz.vanityFair.ajax.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AjaxControllerTest
{
    @Before
    public void testBefore()
    {
        ClassPathResource resource = new ClassPathResource("templates/app_template/vanityfair-mail.html");
        try
        {
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);
            content.forEach(System.out::println);
        }
        catch (IOException e)
        {
            log.error("{}", e.getMessage(), e);
        }
    }
    
    @Test
    public void test()
    {
        System.out.println("테스트");
    }
    
    @After
    public void testAfter()
    {
        
    }
    
}
