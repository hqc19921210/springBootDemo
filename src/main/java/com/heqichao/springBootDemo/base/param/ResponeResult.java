package com.heqichao.springBootDemo.base.param;
/**
 * @author
 * Created by heqichao on 2018-2-14.
 */

public class ResponeResult {

    private boolean isSuccess =true;

    private String message="";

    private Object resultObj=new Object();

    public ResponeResult() {
        this.isSuccess = true;
    }

    public ResponeResult(boolean isSuccess, String message, Object resultObj) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.resultObj = resultObj;
    }


    public ResponeResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ResponeResult( Object resultObj) {
        this.isSuccess =true;
        this.resultObj = resultObj;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

    public void success(Object resultObj){
        this.isSuccess = true;
        this.resultObj = resultObj;
    }

    public void error(Object resultObj){
        this.isSuccess = false;
        this.resultObj = resultObj;
    }

    public void errorMsg(String message){
        this.isSuccess = false;
        this.message = message;
    }

}
