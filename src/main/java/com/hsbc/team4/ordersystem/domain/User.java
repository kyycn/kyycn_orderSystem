package com.hsbc.team4.ordersystem.domain;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.domain
 * @Description :
 * @Date : 2018/8/1
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class User  extends BaseEntity{
    @Id
    private String id;
    @NotBlank(message = "The username can not be empty")
    private String username;
    @NotBlank(message = "The password can not be empty")
    private String password;

}
