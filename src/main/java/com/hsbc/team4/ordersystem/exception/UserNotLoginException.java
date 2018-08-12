package com.hsbc.team4.ordersystem.exception;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.exception
 * @Description :
 * @Date : 2018/8/11
 */
public class UserNotLoginException  extends RuntimeException{
    private static final long serialVersionUID = -6925278824391495117L;
    /**
     *  UserNotLoginException
     * @param message
     */
    public UserNotLoginException(String message) {
        super(message);
    }

}
