package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author: Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-13
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Department extends BaseEntity {
    /**
     * department id
     */
    @Id
    private String id;

    /**
     * department's name
     */
    @NotBlank(message = "department's name can not be blank")
    private String name;

    /**
     * the order of salary to create workNumber
     */
    private Integer orderNum;
}
