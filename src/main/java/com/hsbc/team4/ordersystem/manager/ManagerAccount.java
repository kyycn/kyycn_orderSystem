package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-03
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ManagerAccount extends BaseEntity{
    /**
     * account id
     */
    @Id
    private String id;

    /**
     * account name, the same as the manager's workNumber
     */
    @NotBlank(message = "The account can not be empty")
    private String name;

    /**
     * account password
     */
    @NotBlank(message = "The password can not be empty")
    private String password;

}
