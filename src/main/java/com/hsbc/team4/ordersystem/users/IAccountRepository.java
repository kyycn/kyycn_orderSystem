package com.hsbc.team4.ordersystem.users;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
@Repository
public interface IAccountRepository extends IBaseRepository<Account,String> {
}
