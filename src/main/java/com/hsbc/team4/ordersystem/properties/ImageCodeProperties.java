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
public class ImageCodeProperties extends SmsCodeProperties{

    private int width = 67;
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }
}
