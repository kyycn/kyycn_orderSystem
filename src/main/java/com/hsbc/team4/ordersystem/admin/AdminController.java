package com.hsbc.team4.ordersystem.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.admin
 * @Description :
 * @Date : 2018/8/13
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/home")
    public ModelAndView goToHome(ModelAndView modelAndView){
        modelAndView.setViewName("admin/home.html");
        return modelAndView;
    }
}
