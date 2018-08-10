package com.hsbc.team4.ordersystem.exception;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *  global
 * @author Kevin
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final ResponseResults responseResults;

    @Autowired
    public GlobalExceptionHandler(ResponseResults responseResults) {
        this.responseResults = responseResults;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResults defaultExceptionHandler(Exception e){
        log.info("errors",e);
        return responseResults.responseByErrorMessage("The service busy");
    }

    /**
     *  usernameNotFoundHandle
     * @param e
     * @param request
     * @return String
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseResults usernameNotFoundHandle(Exception e,HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        map.put("code",1001);
        map.put("msg","the user not found");
        request.setAttribute("ext",map);
        return responseResults.responseBySuccess(map);
    }

    /**
     * 500 - Bad Request
     * @ExceptionHandler
     */
    @ExceptionHandler({HttpMessageNotReadableException.class,
                       HttpRequestMethodNotSupportedException.class,
                       HttpMediaTypeNotSupportedException.class,
                       SQLException.class})
    public ResponseResults handleHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response, Exception e){
        return responseResults.responseByErrorMessage("The service busy");
    }
    /**
     * 404.
     * @param request
     * @param response
     * @param ex
     * @return
     * @throws Exception
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResults notFound(HttpServletRequest request, HttpServletResponse response, Exception ex, Model model) {
        return responseResults.responseByErrorMessage("please check you request url");
    }


}
