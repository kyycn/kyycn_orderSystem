package com.hsbc.team4.ordersystem.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.base
 * @Description :
 * @Date : 2018/8/1
 */
@Data
@MappedSuperclass
public class BaseEntity {
    /**
     * create ID
     */
    private String createId;
    /**
     * create time
     */
    private long createTime;
    /**
     * update ID
     */
    private String updateId;
    /**
     * update time
     */
    private long updateTime;
    /**
     * 是否可用（0：可用，1：软删除，2：永久删除)
     */
    @Column(name="status", columnDefinition = "INT default 0")
    private int status = 0;

}
