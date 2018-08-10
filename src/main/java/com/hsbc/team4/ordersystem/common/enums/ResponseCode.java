package com.hsbc.team4.ordersystem.common.enums;

/**
 * @author:Kevin
 * @version:
 * @Project: permission_manage
 * @Package: com.hsbc.dev.teamo4.sms.common.fields
 * @Description:
 * @Date date: 2018/7/26
 */
public enum  ResponseCode {
    /**
     * SUCCESS
     */
    NOTLOGIN(1000,"NOTLOGIN"),
    NOTPERMISE(1001," NOPERMISSION"),
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR");

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
