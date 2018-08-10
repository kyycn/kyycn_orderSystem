package com.hsbc.team4.ordersystem.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.properties
 * @Description :
 * @Date : 2018/8/9
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String header;
    private String secret;
    private Long expiration;
    private String tokenHead;
    private String exceptUrl;

}
