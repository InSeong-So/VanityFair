package web.biz.vanityFair.service.user;

import lombok.extern.slf4j.Slf4j;
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
import web.biz.vanityFair.facade.UserInterface;
import web.biz.vanityFair.repository.user.UserRepository;
import web.common.core.component.SisExtends;
import web.common.core.exception.SisRuntimeException;
import web.common.core.util.SisEncUtil;
import web.common.core.util.SisStringUtil;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserService extends SisExtends implements UserInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private Map<String, String> certificatedMap = new HashMap<String, String>();

//    @PostConstruct
//    public void init()
//    {
//        userRepository.save(User.builder().adminYn("Y").pwd(SisEncUtil.SHA256("1234")).userCd(codeCreator(true)).userId("admin").userNm("관리자").build());
//        
//    }

    /**
     * 유저 로그인
     */
    @Override
    public User userLogin(String userId, String pwd) {
        Optional<User> checkUser = userRepository.findByUserId(userId);

        if (!checkUser.isPresent()) {
            throw new IllegalStateException();
        }

        User user = checkUser.get();

        if (!user.matchPwd(SisEncUtil.SHA256(pwd))) {
            throw new IllegalStateException("비밀번호가 옳지 않습니다.");
        } else {
            return user;
        }
    }

    /**
     * 회원가입
     */
    @Override
    @Transactional
    public boolean userRegsistration(User user) {
        Optional<User> asCheckUser = userRepository.findByUserId(user.getUserId());

        if (asCheckUser.isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        try {
            User regUSer = User.builder().seqNo(userRepository.getSeqNo() + 1).userId(user.getUserId()).userCd(codeCreator(true)).pwd(SisEncUtil.SHA256(user.getPwd())).userLastLogin(SisStringUtil.getYmd()).build();

            userRepository.save(regUSer);

            return true;
        } catch (Exception e) {
            log.error("userRegsistration ERROR : " + e.getMessage());
            throw new SisRuntimeException("asdf");
        }
    }

    @Override
    public User userProfileInquiry(String userId) {
        Optional<User> userProfile = userRepository.findByUserId(userId);
        return userProfile.get();
    }

    /**
     * 프로필 업데이트
     */
    @Override
    public void userProfileUpdate(User user) {
        userRepository.userProfileUpdate(user.getUserNm(), user.getUserZipCd(), user.getUserAddr(), user.getUserDtlAddr(), user.getUserPhoneNumber(), user.getUserCd());
    }

    /**
     * 비밀번호 변경
     */
    @Override
    public boolean userPwdChange(User user, String chaPwd) {
        String changePwd = SisEncUtil.SHA256(chaPwd);

        userRepository.setModifiedPwd(changePwd, user.getUserCd());

        return true;
    }

    @Override
    public void userSendCertMail(String key, MailVO mailVO, boolean yn) {
        // 키 설정
        mailVO.setKey(SisStringUtil.getKey(false, 8));

//        certificatedMap.put();

//        userService.userMailCertSend((User) mailVO.getModel().get("user"), mailVO.getKey());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
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

        } catch (Exception e) {
            throw new SisRuntimeException("메일 전송 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }

        mailSender.send(message);

    }

    @Override
    public void userSentMailDelete(User user) {

    }

    @Override
    public void userCertMailSuccess() {

    }

}
