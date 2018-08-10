package com.hsbc.team4.ordersystem.common.validatecode.image;

import com.hsbc.team4.ordersystem.common.validatecode.AbstractValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.validatecode.image
 * @Description :
 * @Date : 2018/8/9
 */
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    public String createCode(ServletWebRequest request) throws Exception {
        return null;
    }

    @Override
    public String checkValidate(ServletWebRequest servletWebRequest) {
        return null;
    }
}
