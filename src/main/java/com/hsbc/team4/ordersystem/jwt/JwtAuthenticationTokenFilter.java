package com.hsbc.team4.ordersystem.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * token  filter
 * @author Kevin
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private  UserDetailsService userDetailsService;
    private  JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public JwtAuthenticationTokenFilter(){

    }
    @Autowired
    public JwtAuthenticationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("start Authentication:",request.getRequestURI());
        //get tokenHeader
        final String authHeader = request.getHeader(this.tokenHeader);
        String username=null;
        String authToken=null;
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            //get token after the  Bearer
             authToken = authHeader.substring(tokenHead.length());
            //get username by token
            try {
                 username = jwtTokenUtil.getUsernameFromToken(authToken);
                 log.info("username",username);
            } catch (IllegalArgumentException e) {
                log.error("get username by token errors:",e);
            } catch (ExpiredJwtException e) {
                log.error("the token is expiration:",e);
            }
        }else {
            log.warn("not find the tokenHead :",tokenHead);
            logger.warn("没有发现tokenHead:"+tokenHead);
        }
        log.info("start check the user's permissions");
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //load account message
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //verify token
            try {
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                        log.info("the account is pass the permission:",username);
                        log.info("set security context :",authentication);
                        logger.info("为通过权限认证的 user " + username + ", 设置 security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }
}