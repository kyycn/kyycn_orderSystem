
package com.hsbc.team4.ordersystem.jwt;

import com.hsbc.team4.ordersystem.exception.UserNotLoginException;
import com.hsbc.team4.ordersystem.properties.JwtProperties;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@NoArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private  JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private  JwtProperties jwtProperties;

//    @Autowired
//    public JwtAuthenticationTokenFilter(UserDetailsService userDetailsService, JwtTokenGenerator jwtTokenGenerator, JwtProperties jwtProperties) {
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenGenerator = jwtTokenGenerator;
//        this.jwtProperties = jwtProperties;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("chain :",chain.toString());
        log.info("start Authentication:",request.getRequestURI());
        //get tokenHeader
        log.info(jwtProperties.getHeader());
        final String authHeader = request.getHeader(jwtProperties.getHeader());
        String username=null;
        String authToken;
        if (authHeader != null && authHeader.startsWith(jwtProperties.getTokenHead())) {
            //get token after the  Bearer
             authToken = authHeader.substring(jwtProperties.getTokenHead().length());
            //get username by token
            try {
                 username = jwtTokenGenerator.getUsernameFromToken(authToken);
                 log.info("username",username);
            } catch (IllegalArgumentException e) {
                log.error("get username by token errors:",e);
            } catch (ExpiredJwtException e) {
                log.error("the token is expiration:",e);
            }

            log.info("start check the user's permissions");
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //load account message
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //verify token
                try {
                    if (jwtTokenGenerator.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                        log.info("the account is pass the permission:",username);
                        log.info("set security context :",authentication);
                        logger.info(" user " + username + ", set security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }else {
            log.warn("not find the tokenHead :",jwtProperties.getTokenHead());
            //throw new UserNotLoginException("you are not sign in ");
        }
        chain.doFilter(request, response);
    }
}