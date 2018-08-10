package com.hsbc.team4.ordersystem.properties;

import lombok.Data;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.properties
 * @Description :
 * @Date : 2018/8/9
 */
@Data
public class ValidateCodeProperties {

    private final ImageCodeProperties image= new ImageCodeProperties();

    private final SmsCodeProperties code= new SmsCodeProperties();


}
