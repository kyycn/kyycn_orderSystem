package com.hsbc.team4.ordersystem.common.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.validatecode
 * @Description :
 * @Date : 2018/8/9
 */
public interface ValidateCodeProcessor {
    /**
     * create validate
     * @param request
     * @return String
     * @throws Exception
     */
    String createCode(ServletWebRequest request) throws Exception;

    /**
     * check validate
     * @param servletWebRequest
     * @return
     */
    String checkValidate(ServletWebRequest servletWebRequest);
}
