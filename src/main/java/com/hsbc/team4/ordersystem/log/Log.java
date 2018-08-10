package com.hsbc.team4.ordersystem.log;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author chenRenXun
 * @date 2018/1/11 0011
 */
@Entity
@Data
public class Log {
    @Id
    private String id;
    private String operateName;
    private String operateType;
    private String operationDescribe;
    private long operateTime;
    private String operateIP;
}

