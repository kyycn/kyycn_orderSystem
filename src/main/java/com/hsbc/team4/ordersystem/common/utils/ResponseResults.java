package com.hsbc.team4.ordersystem.common.utils;

import java.io.Serializable;

/**
 * @author:Kevin
 * @version:
 * @Project: permission_manage
 * @Package: com.hsbc.dev.teamo4.sms.common.fields
 * @Description:
 * @Date date: 2018/7/26
 */
public class ResponseResults<T> implements Serializable {
    /**
     * status
     */
    private int status;
    /**
     * massage
     */
    private String msg;
    /**
     * results
     */
    private T results;

    /**
     * constructor
     * @param status
     */
    private ResponseResults(int status){
        this.status=status;
    }

    private ResponseResults(int status, T results){
        this.status=status;
        this.results=results;
    }
    private ResponseResults(int status, String msg){
        this.status=status;
        this.msg=msg;
    }
    private ResponseResults(int status, String msg, T results){
        this.status=status;
        this.results=results;
        this.msg=msg;
    }

    public int getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }
    public T getResults() {
        return results;
    }

    /**
     * 使之不在json序列化结果当中
     * @return
     */
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 请求成功，只返回状态
     * @param <T>
     * @return ResponseResults
     */
    public static <T> ResponseResults<T> responseBySuccess(){
        return new ResponseResults<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 返回成功的信息和状态
     * @param msg
     * @param <T>
     * @return ResponseResults
     */
    public static <T> ResponseResults<T> responseBySuccessMessage(String msg){
        return new ResponseResults<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    /**
     * 返回状态和结果集
     * @param result
     * @param <T>
     * @return ResponseResults
     */
    public static <T> ResponseResults<T> responseBySuccess(T result){
        return new ResponseResults<T>(ResponseCode.SUCCESS.getCode(),result);
    }

    /**
     * 返回成功的状态、信息和结果集
     * @param msg
     * @param data
     * @param <T>
     * @return ResponseResults
     */
    public static <T> ResponseResults<T> responseBySuccess(String msg, T data){
        return new ResponseResults<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回失败
     * @param <T>
     * @return ResponseResults
     */
    public static <T> ResponseResults<T> responseByError(){
        return new ResponseResults<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    /**
     * 返回失败的状态和信息
     * @param errorMessage
     * @param <T>
     * @return ResponseResults
     */
    public static <T> ResponseResults<T> responseByErrorMessage(String errorMessage){
        return new ResponseResults<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    /**
     * 返回失败的状态和错误信息
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return ResponseResults
     */
    public static <T> ResponseResults<T> responseByErrorCodeMessage(int errorCode, String errorMessage){
        return new ResponseResults<T>(errorCode,errorMessage);
    }

}
