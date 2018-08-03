package com.hsbc.team4.ordersystem.common.factory;

import java.util.UUID;

/**
 * @author:yang
 * @version:
 * @Project: permission_manage
 * @Package: com.hsbc.dev.teamo4.sms.common.utils.factory
 * @Description:
 * @Date date: 2018/7/27
 */
public class UUIDFactory {
    /**
     * getUUID
     * @return uuid
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","")+System.currentTimeMillis();
    }
}
