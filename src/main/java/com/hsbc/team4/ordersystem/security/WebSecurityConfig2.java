//package com.hsbc.team4.ordersystem.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author:yang
// * @version:
// * @Project: ordersystem
// * @Package: com.hsbc.team4.ordersystem.security
// * @Description:
// * @Date date: 2018/8/6
// */
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();
//        http
//                .formLogin().loginPage("/login")
//                .failureUrl("/login?str=error").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/","/css/**","/js/**","/image/**","/iconfont/**","/images/**","/vendors/**","/check","/register").permitAll()
////                .antMatchers("/product/query?id=2018").hasRole("user")
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//              .inMemoryAuthentication()
//              .passwordEncoder(new MyPasswordEncoder())
//            .withUser("user").password("123").roles("USER");
//
//    }
//
//}