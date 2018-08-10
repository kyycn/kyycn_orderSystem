package com.hsbc.team4.ordersystem.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.properties
 * @Description :
 * @Date : 2018/8/10
 */
@Component
@Data
public class RedisProperties {
    @Value("${spring.redis.database}")
    private String database;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("spring.redis.jedis.pool.max-idle")
    private String maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private String minIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${redis.timeout}")
    private String timeout;

}
