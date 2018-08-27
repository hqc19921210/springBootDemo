package com.heqichao.springBootDemo.module.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.heqichao.springBootDemo.base.entity.BaseEntity;
import com.heqichao.springBootDemo.base.util.JsonUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Created by heqichao on 2018-8-21.
 */
@Component("lite_log")
public class LiteLog extends BaseEntity {
    private String message;
    private String currenState;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date eventTime;
    
    public LiteLog() {
    	
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrenState() {
        return currenState;
    }

    public void setCurrenState(String currenState) {
        this.currenState = currenState;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public LiteLog(String message, String currenState, Date eventTime) {
        this.message = message;
        this.currenState = currenState;
        this.eventTime = eventTime;
        this.createTime =new Date();
    }
}
