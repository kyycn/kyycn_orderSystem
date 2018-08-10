package com.hsbc.team4.ordersystem.common.validatecode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.validatecode
 * @Description :
 * @Date : 2018/8/9
 */
@Data
@AllArgsConstructor
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    /**
     *  ValidateCode
     * @param code the validate code
     * @param expireTime
     */
    public ValidateCode(String code, long expireTime){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

}
