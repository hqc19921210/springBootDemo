package com.heqichao.springBootDemo.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by heqichao on 2018-2-12.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userNo;
    private String name;
    private int id;
    @JsonIgnore//json返回时忽略该字段
    private String password;

/*    @JsonInclude(JsonInclude.Include.NON_NULL) //为null时不返回
    private String mail;
    @JsonInclude(JsonInclude.Include.NON_NULL) //为null时不返回
    private String mobile;*/


   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
   @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
