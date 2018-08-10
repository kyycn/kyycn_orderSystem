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
public class SmsCodeProperties {

    private int length=6;

    private long expireTime=60;

}
