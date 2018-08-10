package com.hsbc.team4.ordersystem.users.service;

import com.hsbc.team4.ordersystem.common.base.IBaseService;
import com.hsbc.team4.ordersystem.products.Product;
import com.hsbc.team4.ordersystem.users.domain.Favourite;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author:yang
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.users.service
 * @Description:
 * @Date date: 2018/8/9
 */
public interface IFavouriteService extends IBaseService<Favourite> {
    List<Product> findByUserId(String userId);
    Favourite findByUserIdAndProId(String userId,String proId);
    Favourite save(Favourite favourite);
}
