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
    /**
     * manager id
     */
    @Id
    private String id;

    /**
     * manager name connect to
     */
    @NotBlank(message = "The username can not be empty")
    private String name;

    /**
     * manager workNumber
     */
    @NotBlank(message = "The workNumber can not be empty")
    private String workNumber;

    /**
     * manager department
     */
    @NotBlank(message = "The department can not be empty")
    private String department;

}
