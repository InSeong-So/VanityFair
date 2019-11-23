package web.common.core.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import web.biz.vanityFair.domain.user.User;

public class SisSessionUtil
{
    public static final String REQUEST_SESSION_KEY = "requestInfo";
    
    public static final String USER_SESSION_KEY = "loginedUser";
    
    public static final String DEFAULT_ENCODING = "UTF-8";
    
    public static boolean isLoginUser(NativeWebRequest webRequest)
    {
        Object loginedUser = webRequest.getAttribute(USER_SESSION_KEY, RequestAttributes.SCOPE_SESSION);
        return loginedUser != null;
    }
    
    public static User getUserFromSession(NativeWebRequest webRequest)
    {
        if (!isLoginUser(webRequest))
        {
            return User.GUEST_USER;
        }
        return (User) webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
    }
    
    public static boolean isLoginUser(HttpSession session)
    {
        Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
        if (sessionedUser == null)
        {
            return false;
        }
        return true;
    }
    
    public static User getUserFromSession(HttpSession session)
    {
        if (!isLoginUser(session))
        {
            return null;
        }
        
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
    
    public static ModelAndView returnValue(String viewName, String key, String value)
    {
        ModelAndView view = new ModelAndView();
        
        view.setViewName(viewName);
        view.addObject(key, value);
        
        return view;
    }
}
