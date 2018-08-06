package com.hsbc.team4.ordersystem.emailmessage;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.emailmessage
 * @Description :
 * @Date : 2018/8/5
 */
@Entity
@Data
public class MailMsg {
    @Id
    private String id;
    /**
     * sender
     */
    private String sender;
    /**
     * receiver
     */
    private String receiver;
    /**
     * email title
     */
    private String subject;
    /**
     * text
     */
    private String content;
    /**
     * attachment
     */
    private String[] attachFileNames;
    /**
     * status
     */
    private int status;


}
