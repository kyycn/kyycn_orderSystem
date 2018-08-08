package com.hsbc.team4.ordersystem.common.utils;

import java.io.Serializable;

/**
 * @author : Kevin
 * @version :
 * @Project : permission_manage
 * @Package : com.hsbc.dev.teamo4.sms.common.fields
 * @Description :
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
     */
    public ResponseResults(){

    }

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
     * @return boolean
     */
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * @param <T>
     * @return ResponseResults
     */
    public  <T> ResponseResults<T> responseBySuccess(){
        return new ResponseResults<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * return msg and code
     * @param msg
     * @param <T>
     * @return ResponseResults
     */
    public  <T> ResponseResults<T> responseBySuccessMessage(String msg){
        return new ResponseResults<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    /**
     * @param result
     * @param <T>
     * @return ResponseResults
     */
    public  <T> ResponseResults<T> responseBySuccess(T result){
        return new ResponseResults<T>(ResponseCode.SUCCESS.getCode(),result);
    }

    /**
     * @param msg
     * @param data
     * @param <T>
     * @return ResponseResults
     */
    public  <T> ResponseResults<T> responseBySuccess(String msg, T data){
        return new ResponseResults<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public  <T> ResponseResults<T> responseBySuccess(String msg){
        return new ResponseResults<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * @param <T>
     * @return ResponseResults
     */
    public  <T> ResponseResults<T> responseByError(){
        return new ResponseResults<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    /**
     * @param errorMessage
     * @param <T>
     * @return ResponseResults
     */
    public  <T> ResponseResults<T> responseByErrorMessage(String errorMessage){
        return new ResponseResults<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    /**
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return ResponseResults
     */
    public  <T> ResponseResults<T> responseByErrorCodeMessage(int errorCode, String errorMessage){
        return new ResponseResults<T>(errorCode,errorMessage);
    }

}
