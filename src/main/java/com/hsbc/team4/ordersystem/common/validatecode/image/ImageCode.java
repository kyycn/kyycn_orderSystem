package com.hsbc.team4.ordersystem.common.validatecode.image;

import com.hsbc.team4.ordersystem.common.validatecode.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.validatecode.image
 * @Description :
 * @Date : 2018/8/9
 */
@Data
public class ImageCode extends ValidateCode{

    private BufferedImage image;

    /**
     *  ImageCode
     * @param image
     * @param code
     * @param expireTime
     */
    public ImageCode(BufferedImage image,String code, long expireTime) {
        super(code, expireTime);
        this.image=image;
    }

}
