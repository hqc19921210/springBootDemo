package com.heqichao.springBootDemo.base.exception;

/**
 * Created by heqichao on 2018-2-20.
 */
public class ResponeException extends RuntimeException {

    public ResponeException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //异常编码
    private String code;
    //异常信息
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
