package com.hsbc.team4.ordersystem.smsmessage;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.smsmessage
 * @Description :
 * @Date : 2018/8/5
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Sender extends BaseEntity implements Serializable{
    @Id
    private String id;
    /**
     * baseUrl
     */
    @NotBlank(message = " the baseUrl can not be Empty ")
    private String baseUrl;

    /**
     * accountId
     */
    @NotBlank(message = " the accountId can not be Empty ")
    private String accountId;

    /**
     *
     */
    @NotBlank(message = " the authToken can not be Empty ")
    private String authToken;

    /**
     * dateType json or xml
     */
    @NotBlank(message = " the dateType can not be Empty ")
    private    String dateType;
    /**
     * phone
     */
    @NotBlank(message = " the phone can not be Empty ")
    private String phone;
    /**
     * SMS templateId
     */
    @NotBlank(message = " the templateId can not be Empty ")
    private String templateId;
    /**
     * templateType
     */
    @NotBlank(message = " the templateType can not be Empty ")
    private String templateType;
}
