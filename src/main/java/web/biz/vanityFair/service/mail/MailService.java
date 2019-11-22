package web.biz.vanityFair.service.mail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.vo.MailVO;
import web.biz.vanityFair.service.user.UserService;
import web.common.core.exception.SisRuntimeException;
import web.common.core.util.SisStringUtil;

@Service
public class MailService
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;
    
    public void sendMail(String key, MailVO mailVO) throws MessagingException, IOException
    {
        sendMail(key, mailVO, false);
    }
    
    /**
     * 인증메일 발송
     * 
     * @param key
     *            thymeleaf에서 받는 파라미터 객체명
     * @param mailVO
     *            메일 객체
     * @param yn
     *            파일첨부 여부
     * @throws MessagingException
     * @throws IOException
     */
    @Transactional
    public void sendMail(String key, MailVO mailVO, boolean yn)
    {
        // 키 설정
        mailVO.setKey(SisStringUtil.getKey(false, 8));
        
        userService.userMailCertSend((User) mailVO.getModel().get("user"), mailVO.getKey());
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try
        {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            
            // TODO: 파일 첨부기능, 하드코딩 된 상태
            if (yn)
                helper.addAttachment("freeLogo.jpeg", new ClassPathResource("app_template/freeLogo.jpeg"));
            
            Context context = new Context();
            context.setVariable(key, mailVO);
            
            String html = templateEngine.process("app_template/vanityfair-mail", context);
            
            helper.setTo(mailVO.getTo());
            helper.setText(html, true);
            helper.setSubject(mailVO.getSubject());
            helper.setFrom(mailVO.getFrom());
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("메일 전송 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }
        
        mailSender.send(message);
    }
    
    public void mailDelete(User user)
    {
        try
        {
            userService.userMailDelete(user);
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("유저 이메일 삭제 중 에러가 발생했습니다. 사유 : " + e.getMessage());
        }
    }
    
    public void mailCertSuccess()
    {
        
    }
}
