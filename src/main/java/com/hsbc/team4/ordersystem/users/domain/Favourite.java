package com.hsbc.team4.ordersystem.users.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author:yang
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.users.domain
 * @Description:
 * @Date date: 2018/8/9
 */

@Entity
@Data
public class Favourite {
    @Id
    private String id;

    @NotBlank(message = "The userId can not be empty")
    private String userId;

    @NotBlank(message = "The proId can not be empty")
    private String proId;


}
