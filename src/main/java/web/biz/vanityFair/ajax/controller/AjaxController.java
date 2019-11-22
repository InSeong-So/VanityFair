package web.biz.vanityFair.ajax.controller;

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

import web.biz.vanityFair.ajax.bean.AjaxResponseBody;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.domain.user.UserDetail;
import web.biz.vanityFair.service.user.UserService;
import web.common.core.exception.SisRuntimeException;
import web.common.core.util.SisEncUtil;
import web.common.core.util.SisSessionUtil;

// TODO: 차후 수정
@RestController
@RequestMapping("/api")
public class AjaxController
{
    @Autowired
    private UserService userService;
    
    /**
     * 프로필 업데이트
     * 
     * @param params
     * @param errors
     * @param session
     * @return
     */
    @PostMapping("/profileUpdate")
    public ResponseEntity<?> profileUpdate(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        AjaxResponseBody result = new AjaxResponseBody();
        
        //        if (errors.hasErrors())
        //        {
        //            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
        //            return ResponseEntity.badRequest().body(result);
        //        }
        
        try
        {
            userService.userProfileUpdate(UserDetail.builder().user((User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY)).userNm((String) params.get("userNm")).userPhoneNumber((String) params.get("userPhoneNumber")).userZipCd((String) params.get("userZipCd"))
                    .userAddr((String) params.get("userAddr")).userDtlAddr((String) params.get("userDtlAddr")).build());
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("프로필 업데이트 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }
        
        result.setMsg("변경 완료!");
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/mailCert")
    public ResponseEntity<?> mailCert(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        AjaxResponseBody result = new AjaxResponseBody();
        
        if (errors.hasErrors())
        {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            
            return ResponseEntity.badRequest().body(result);
        }
        
        if (!userService.userMailCertified(user, params))
        {
            result.setMsg("인증 실패!");
        }
        else
        {
            result.setMsg("인증 완료!");
        }
        
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/pwdChange")
    public ResponseEntity<?> pwdChange(@RequestBody Map<String, Object> params, Errors errors, HttpSession session)
    {
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        AjaxResponseBody result = new AjaxResponseBody();
        
        if (errors.hasErrors())
        {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            
            return ResponseEntity.badRequest().body(result);
        }
        
        try
        {
            // 현재 비밀번호가 다르면 불가능 체크
            if (!SisEncUtil.SHA256((String) params.get("curPwd")).equals(user.getPwd()))
            {
                return ResponseEntity.badRequest().body("현재 비밀번호가 다릅니다.");
            }
            
            // TODO: 비밀번호 변경 시 로그아웃 처리를 하지 않으면 세션 비밀번호와 비교하므로 에러가 발생.
            userService.userPwdChange(user, params);
        }
        catch (Exception e)
        {
            throw new SisRuntimeException("비밀번호 변경 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }
        
        result.setMsg("변경 완료!");
        
        return ResponseEntity.ok(result);
    }
    
    //    @PostMapping("/error")
    //    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody String time, Errors errors)
    //    {
    //        AjaxResponseBody result = new AjaxResponseBody();
    //        
    //        if (errors.hasErrors())
    //        {
    //            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
    //            
    //            return ResponseEntity.badRequest().body(result);
    //        }
    //        
    //        result.setMsg(VanityFairStringUtil.CreateDataWithCheck(time));
    //        
    //        return ResponseEntity.ok(result);
    //    }
    
}
