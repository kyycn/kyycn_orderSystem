package com.hsbc.team4.ordersystem.emailmessage;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Properties;

/**
 * Created by Kevin on 2018/7/23
 *
 * @author Kevin
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MailSender extends BaseEntity{
    @Id
    private String id;
    /**
     * mail host
     */
    private String mailServerHost;
    /**
     * mail port
     */
    private String mailServerPort;
    /**
     * mail protocol
     */
    private String protocol;
    /**
     * sander address
     */
    private String fromAddress;
    private String toAddress;
    /**
     * mail username
     */
    private String username;
    /**
     * mail password
     */
    private String password;
    /**
     * Permission validation
     */
    private boolean validate = true;

    /**
     * get email session
     */
    public Properties getProperties(){
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }
}
