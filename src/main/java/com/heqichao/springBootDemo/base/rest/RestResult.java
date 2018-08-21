package com.heqichao.springBootDemo.base.rest;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created by heqichao on 2018-8-18.
 */
public class RestResult {
    private Integer code;

    private String message;

    private T data;

    public RestResult() {
    }

    public RestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
