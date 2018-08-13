package com.hsbc.team4.ordersystem.users.service.impl;

import com.hsbc.team4.ordersystem.products.Product;
import com.hsbc.team4.ordersystem.users.domain.Favourite;
import com.hsbc.team4.ordersystem.users.repository.IFavouriteRepository;
import com.hsbc.team4.ordersystem.users.service.IFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:yang
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.users.service.impl
 * @Description:
 * @Date date: 2018/8/9
 */
@Service
public class FavouriteServiceImpl implements IFavouriteService {
    @Autowired
    IFavouriteRepository favouriteRepository;

    @Override
    public List<Product> findByUserId(String userId) {
        return favouriteRepository.findByUserId(userId);
    }

    @Override
    public Favourite findByUserIdAndProId(String userId,String proId) {
        return favouriteRepository.findByUserIdAndProId(userId,proId);
    }

    @Override
    public Favourite save(Favourite favourite) {
        return favouriteRepository.save(favourite);
    }

    @Override
    public Page findByStatus(int current, int pageSize, int status) {
        return null;
    }

    @Override
    public Favourite addEntity(Favourite fav) {
        return null;
    }

    @Override
    public int updateStatusById(String id, int status) {
        return 0;
    }

    @Override
    public Favourite updateEntity(Favourite favourite) {
        return null;
    }

    @Override
    public Favourite findById(String id) {
        return null;
    }


}
