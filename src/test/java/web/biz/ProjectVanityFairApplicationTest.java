//package web.biz;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.mail.MessagingException;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import core.util.VanityFairStringUtil;
//import lombok.extern.slf4j.Slf4j;
//import web.biz.vanityFair.domain.user.User;
//import web.biz.vanityFair.domain.vo.MailVO;
//import web.biz.vanityFair.service.mail.MailService;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ProjectVanityFairApplicationTest
//{
//    @Autowired
//    private MailService emailService;
//    
//    @Test
//    public void test() throws MessagingException, IOException
//    {
//        log.info(">>>>>>>>>>>>>>>>> sending TEST <<<<<<<<<<<<<<<<<");
//        
//        User user = User.builder().userCd("1029VA4810").userId("admin").build();
//        
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("user", user);
//        
//        MailVO mail = new MailVO();
//        mail.setKey(VanityFairStringUtil.getKey(false, 5));
//        
//        mail.setFrom("no-reply@memorynotfound.com");
//        mail.setTo("goflvhxj2547@naver.com");
//        mail.setSubject("[VanityFair] : " + user.getUserId() + " 님의 인증메일입니다.");
//        mail.setUserId("Admin");
//        mail.setModel(model);
//        
//        log.info(">>>>>>>>>>>>>>>>> sending TEST end <<<<<<<<<<<<<<<<<");
//        
//        emailService.sendMail("mail", mail);
//        
//    }
//    
//}
