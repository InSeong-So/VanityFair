package Vanityfair.support.security;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import Vanityfair.support.exception.UnAuthorizationException;
import Vanityfair.web.domain.user.User;
import Vanityfair.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicAuthInterceptor extends HandlerInterceptorAdapter
{
    
    public static void main(String args[])
    {
    }
    
    @Autowired
    private UserService userService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String authorization = request.getHeader("Authorization");
        log.debug("Authorization : {}", authorization);
        if (authorization == null || !authorization.startsWith("Basic"))
        {
            return true;
        }
        
        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);
        log.debug("username : {}", values[0]);
        log.debug("password : {}", values[1]);
        
        try
        {
            User user = userService.login(values[0], values[1]);
            log.debug("Login Success : {}", user);
            request.getSession().setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
            return true;
        }
        catch (UnAuthorizationException e)
        {
            return true;
        }
    }
}
