package com.hsbc.team4.ordersystem.users.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.domain
 * @Description :
 * @Date : 2018/8/1
 */
@Entity
@Data
public class Account {
    @Id
    private String id;
    @NotBlank(message = "The realName can not be empty")
    private String username;
    @NotBlank(message = "The realName can not be empty")
    private String realName;
    @NotBlank(message = "The tradePassword can not be empty")
    private String tradePassword;
    @NotNull(message = "The usableBalance can not be null")
    private BigDecimal usableBalance;
    @NotNull(message = "The freezeBalance can not be null")
    private BigDecimal freezeBalance;
    @NotNull(message = "The status can not be null")
    private int status;

}
