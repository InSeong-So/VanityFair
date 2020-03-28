package web.biz.vanityFair.ajax.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.ajax.bean.AjaxResponseBody;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.vo.MailVO;
import web.biz.vanityFair.facade.ArticleInterface;
import web.common.core.exception.SisRuntimeException;
import web.common.core.util.SisSessionUtil;

@Slf4j
@RestController
@RequestMapping("/api")
public class AjaxController
{
    
    @Autowired
    ArticleInterface articleInterface;
    
    @PostMapping("/articleDelete")
    public ResponseEntity<?> articleDelete(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        AjaxResponseBody result = new AjaxResponseBody();
        
        if (errors.hasErrors())
        {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        
        try
        {
            articleInterface.articleDelete(articleInterface.getArticleByArticleCd(String.valueOf(params.get("articleCd"))));
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("게시글 삭제 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }
        
        result.setMsg("변경 완료!");
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/commentDelete")
    public ResponseEntity<?> commentDelete(@RequestBody Map<String, Object> params, Errors errors)
    {
        AjaxResponseBody result = new AjaxResponseBody();
        
        if (errors.hasErrors())
        {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        
        try
        {
            Article article = articleInterface.getArticleByArticleCd(String.valueOf(params.get("articleCd")));
            String userId = String.valueOf(params.get("userId"));
            long commentNo = Long.parseLong(String.valueOf(params.get("commentNo")));
            
            articleInterface.articleCommentDelete(article, userId, commentNo);
            
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("댓글 삭제 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }
        
        result.setMsg("변경 완료!");
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 이메일 인증
     *
     * @param params
     * @param errors
     * @param session
     * @return
     */
    @PostMapping("/mailCert")
    public ResponseEntity<?> mailCert(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        AjaxResponseBody result = new AjaxResponseBody();
        
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        
        String userId = user.getUserId();
        String toMailAddr = (String) params.get("userMail");
        String subject = "[VanityFair] : " + userId + " 님의 인증메일입니다.";
        
        MailVO mailVO = MailVO.builder().subject(subject).from("VanityFairAdmin").to(toMailAddr).userId(userId).model(model).build();
        
        //        mailService.sendMail("mail", mailVO, false);
        
        result.setMsg("발송성공!!!!");
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/productRegist")
    public ResponseEntity<?> productRegist(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        AjaxResponseBody result = new AjaxResponseBody();
        /*
         *  productNm=test
         *  providerPrice=asd
         *  optionType_1=Y
         *  mainImage=
         *  shortDesc=asdf
         *  longDesc=
         *  productStatus_1=productStatus_1_N
         *  productStatus_2=productStatus_2_N
         */

        Iterator<String> keys = params.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            log.info( String.format("키 : %s, 값 : %s", key, params.get(key)) );
        }
        
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        
        String userId = user.getUserId();
        
        return ResponseEntity.ok(result);
    }
}
