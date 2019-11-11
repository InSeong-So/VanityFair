package web.biz.vanityFair.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.util.VanityFairSessionUtil;
import web.biz.vanityFair.ajax.bean.AjaxResponseBody;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.vo.MailVO;
import web.biz.vanityFair.service.mail.MailService;

@RestController
@RequestMapping("/mail")
public class MailController
{
    @Autowired
    private MailService mailService;
    
    private AjaxResponseBody result = new AjaxResponseBody();
    
    /**
     * 이메일 인증
     * 
     * @param params
     * @param errors
     * @param session
     * @param request
     * @return
     */
    @PostMapping("/mailCert")
    public ResponseEntity<?> mailCert(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        User user = (User) session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY);
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        
        String userId = user.getUserId();
        String toMailAddr = (String) params.get("userMail");
        String subject = "[VanityFair] : " + userId + " 님의 인증메일입니다.";
        
        MailVO mailVO = MailVO.builder().subject(subject).from("VanityFairAdmin").to(toMailAddr).userId(userId).model(model).build();
        
        mailService.sendMail("mail", mailVO, false);
        
        result.setMsg("발송성공!!!!");
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 제한 시간 내 이메일 인증 실패 시
     * 
     * @param params
     * @param errors
     * @param session
     * @return
     */
    @PostMapping("/mailDelete")
    public ResponseEntity<?> maildelete(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        User user = (User) session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY);
        
        mailService.mailDelete(user);
        
        result.setMsg("메일 삭제!");
        
        return ResponseEntity.ok(result);
    }
    
    // TODO : 대량 이메일 발송
    //    @PostMapping("/bigMail")
    //    public ResponseEntity<?> bigMail(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    //    {
    //        SimpleMailMessage msg = new SimpleMailMessage();
    //        
    //        User user = (User) session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY);
    //        String emailAddr = (String) params.get("userMail");
    //        
    //        AjaxResponseBody result = new AjaxResponseBody();
    //        
    //        MimeMessagePreparator[] preparators = new MimeMessagePreparator[params.size()];
    //        int i = 0;
    //        
    //        while (params.entrySet().iterator().hasNext())
    //        {
    //            preparators[i++] = new MimeMessagePreparator() {
    //                
    //                @Override
    //                public void prepare(MimeMessage mimeMessage) throws Exception
    //                {
    //                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    //                    helper.setFrom("");
    //                    helper.setTo("");
    //                    helper.setSubject("");
    //                    helper.setText("", true);
    //                }
    //            };
    //        }
    //        mailSender.send(preparators);
    //        
    //        result.setMsg("성공!!!!");
    //        
    //        return ResponseEntity.ok(result);
    //    }
    
    // TODO : 파일첨부 이메일
    //    @PostMapping("/sendMailAttach")
    //    public ResponseEntity<?> sendMailAttach(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    //    {
    //        SimpleMailMessage msg = new SimpleMailMessage();
    //        
    //        User user = (User) session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY);
    //        String emailAddr = (String) params.get("userMail");
    //        
    //        AjaxResponseBody result = new AjaxResponseBody();
    //        
    //        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
    //            
    //            @Override
    //            public void prepare(MimeMessage mimeMessage) throws Exception
    //            {
    //                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    //                helper.setFrom("");
    //                helper.setTo("");
    //                helper.setSubject("");
    //                helper.setText("", true);
    //                FileSystemResource file = new FileSystemResource(new File("E:/test.hwp"));
    //                helper.addAttachment("test.hwp", file);
    //            }
    //        };
    //        
    //        mailSender.send(preparator);
    //        
    //        result.setMsg("성공!!!!");
    //        
    //        return ResponseEntity.ok(result);
    //    }
    
    // TODO : 메일 내에 이미지 첨부
    //    @PostMapping("/sendMailInline")
    //    public String sendMailInline(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    //    {
    //        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
    //            
    //            @Override
    //            public void prepare(MimeMessage mimeMessage) throws Exception
    //            {
    //                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    //                helper.setFrom("");
    //                helper.setTo("");
    //                helper.setSubject("");
    //                String contents = "" + "<img src=\"cid:DUKE.gif\">";
    //                helper.setText(contents, true);
    //                FileSystemResource file = new FileSystemResource(new File("E:/DUKE.gif"));
    //                helper.addInline("DUKE.gif", file);
    //            }
    //        };
    //        
    //        mailSender.send(preparator);
    //        
    //        return "result";
    //    }
}
