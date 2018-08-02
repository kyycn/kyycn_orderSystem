package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Manager extends BaseEntity {
    @Id
    private String id;
    @NotBlank(message = "The username can not be empty")
    private String name;
    @NotBlank(message = "The username can not be empty")
    private String password;
    @NotBlank(message = "The username can not be empty")
    private String workNumber;
    @NotBlank(message = "The username can not be empty")
    private String department;

}
