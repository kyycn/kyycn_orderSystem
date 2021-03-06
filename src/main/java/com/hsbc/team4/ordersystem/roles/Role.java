package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.roles
 * @Description :
 * @Date : 2018/8/2
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Role extends BaseEntity{
    @Id
    private String id;
    @NotBlank(message = " the roleName can not be Empty ")
    private String roleName;

}
