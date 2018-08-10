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
public interface ValidateCodeGenerator {
    /**
     *  generate
     * @param request
     * @return ValidateCode
     */
    ValidateCode generate(ServletWebRequest request);
}
