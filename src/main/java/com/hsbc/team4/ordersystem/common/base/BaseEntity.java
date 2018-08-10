package com.hsbc.team4.ordersystem.common.base;

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
    private String createUsername;
    /**
     * create time
     */
    private long createTime=System.currentTimeMillis();
    /**
     * update ID
     */
    private String updateUsername;
    /**
     * update time
     */
    private long updateTime=System.currentTimeMillis();
    /**
     * status（0：可用，1：软删除，2：永久删除)
     */
    @Column(name="status", columnDefinition = "INT default 0")
    private int status = 0;

}
