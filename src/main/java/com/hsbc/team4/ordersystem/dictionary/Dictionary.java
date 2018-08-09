package com.hsbc.team4.ordersystem.dictionary;

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
 * @Package: com.hsbc.team4.ordersystem.dictionary
 * @Description:
 * @Date date: 2018-08-09
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Dictionary extends BaseEntity {
    /**
     * dictionary
     */
    @Id
    private String id;
    /**
     * type
     */
    @NotBlank(message = "dictionary type can not be null")
    private String  type;
    /**
     * content
     */
    @NotBlank(message = "dictionary content can not be null")
    private String content;
    /**
     * description
     */
    private String description;
}
