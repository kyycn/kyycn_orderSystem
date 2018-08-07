package com.hsbc.team4.ordersystem.emailmessage;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.emailmessage
 * @Description :
 * @Date : 2018/8/5
 */
@Repository
public interface IMailSenderRepository extends IBaseRepository<MailSender,String>{
}
