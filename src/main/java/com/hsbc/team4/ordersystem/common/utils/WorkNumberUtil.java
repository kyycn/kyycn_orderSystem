package com.hsbc.team4.ordersystem.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author: Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.common.utils
 * @Description:
 * @Date date: 2018-08-13
 */
@Component
public class WorkNumberUtil {
    /**
     * the department id which contain three number,
     * where is the second part of the workNumber
     */
    private String departmentId;

    /**
     * the third part of the workNumber,
     * it is salary's order in his/her department
     */
    private Integer orderNumber;

    /**
     * format orderNumber
     */
    public static final String ORDER_FORMAT = "%04d";

    public WorkNumberUtil(){}

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public WorkNumberUtil(String departmentId, Integer orderNumber) {
        this.departmentId = departmentId;
        this.orderNumber = orderNumber;
    }

    public String getWordNumber(){
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        return year + this.departmentId + this.orderNumber;
    }

    public String getWordNumber(String departmentID, int order){
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        return year + departmentID + String.format(ORDER_FORMAT, order);
    }
}
