package com.hsbc.team4.ordersystem.dao;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import com.hsbc.team4.ordersystem.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project :ordersystem
 * @Package : com.hsbc.team4.ordersystem.dao
 * @Description :
 * @Date : 2018/8/1
 */
@Repository
public interface IUserRepository extends IBaseRepository<User,String> {
}
