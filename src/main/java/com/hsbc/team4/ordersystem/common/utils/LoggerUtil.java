package com.hsbc.team4.ordersystem.common.utils;

import com.hsbc.team4.ordersystem.log.Log;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author chenRenXun
 * @date 2018/1/11 0011
 */
public class LoggerUtil {

    public LoggerUtil() {
    }

    /**
     * get Log object
     * @param request
     * @return Log
     */
    public  Log getLog(HttpServletRequest request) {
        /*
         Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user==null){
            return new Log();
        }
        Log log = new Log();
        if (user instanceof UserDetails) {
            log.setOperateName(((UserDetails)user).getUsername());
        } else {
            log.setOperateName(user.toString());
        }
        */
        Log log = new Log();
        log.setOperateType(request.getMethod());
        log.setOperateIP(getClientIp(request));
        log.setOperateTime(System.currentTimeMillis());
        return log;
    }

    private  String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        } else {
            ip = request.getHeader("X-Forwarded-For");
            if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
                int index = ip.indexOf(44);
                return index != -1 ? ip.substring(0, index) : ip;
            } else {
                return request.getRemoteAddr();
            }
        }
    }
}