package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.products.Product;
import com.hsbc.team4.ordersystem.users.domain.Favourite;
import com.hsbc.team4.ordersystem.users.service.IFavouriteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:yang
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.users.controller
 * @Description:
 * @Date date: 2018/8/9
 */
@RestController
public class FavouriteController {
    private final IFavouriteService favouriteService;
    private final ResponseResults responseResults;

    @Autowired
    public FavouriteController(IFavouriteService favouriteService,ResponseResults responseResults){
        this.favouriteService=favouriteService;
        this.responseResults=responseResults;
    }

    /**
     * @Author:yang
     * @Description:query all products in user's favourite
     * @Param: userId
     * @return:list of product type
     * @Date: 2018/8/9
     */

    @ApiOperation(value="query user's favourite",notes = "query favourite",httpMethod = "GET")
    @ApiImplicitParam(name = "userId",value = "userId",dataType="String")
    @RequestMapping(value = "/fav/query/{userId}")
    public ResponseResults findAll(@PathVariable  String userId){
        List<Product> products=favouriteService.findByUserId(userId);
        if(products.isEmpty()){
            return responseResults.responseBySuccess("your favourite is empty");
        }
        else {
            return responseResults.responseBySuccess("ok",products);
        }
    }

    @RequestMapping(value = "/fav/insert")
     public ResponseResults insert(){
        Favourite favourite=new Favourite();
        favourite.setId("2");
        favourite.setProId("20180808");
        favourite.setUserId("1");
        Favourite favourite2=favouriteService.findByUserIdAndProId("1","20180802");
        if(favourite2==null){
            return responseResults.responseBySuccess("ok",favouriteService.save(favourite));
        }
        else
        {
            return responseResults.responseBySuccess("The product is exists in your favourite");
        }
    }

}
