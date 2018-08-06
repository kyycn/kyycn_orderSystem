package com.hsbc.team4.ordersystem.common.utils;

/**
 * @author:Kevin
 * @version:
 * @Project: permission_manage
 * @Package: com.hsbc.dev.teamo4.sms.common.fields
 * @Description:
 * @Date date: 2018/7/26
 */
public enum  ResponseCode {
    SUCCESS(0,"SUCCESS"), //代表请求成功
    ERROR(1,"ERROR");     //代表请求失败

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
