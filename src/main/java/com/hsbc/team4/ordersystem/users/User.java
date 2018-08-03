package com.hsbc.team4.ordersystem.users;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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
public class User{
    @Id
    private String id;
    @NotBlank(message = "The username can not be empty")
    private String username;
    /**
     * the user realName
     */
    private String realName;
    /**
     * the user phone
     */
    private String phone;
    /**
     * the user email
     */
    private String email;
    /**
     * headURL
     */
    private String  headURL;



}
