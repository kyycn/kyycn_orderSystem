package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
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
    @NotBlank(message = " the roleName can not be Blank ")
    private String id;
    @NotBlank(message = " the roleName can not be Empty ")
    @Min(value =2)
    private String roleName;

}
