package com.hsbc.team4.ordersystem.smsmessage;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.smsmessage
 * @Description :
 * @Date : 2018/8/5
 */
@Repository
public interface ISenderRepository extends IBaseRepository<Sender,String>{
    /**
     * findByTemplateTypeAndStatus
     * @param templateType
     * @param status
     * @return Sender
     */
    Sender findByTemplateTypeAndStatus(String templateType,int status);
}
