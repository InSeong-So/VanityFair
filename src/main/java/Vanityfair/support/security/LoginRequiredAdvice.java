package Vanityfair.support.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import Vanityfair.support.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class LoginRequiredAdvice
{
    
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void unAuthorized()
    {
        log.debug("UnAuthorizedException is happened!");
    }
}