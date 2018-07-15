package com.heqichao.springBootDemo.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.glassfish.gmbal.NameValue;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by heqichao on 2018-2-12.
 */
@Component("user_info")
public class UserInfo extends BaseEntity  {


    private String userNo;
    private String name;
    @JsonIgnore//json返回时忽略该字段
    private String password;

/*    @JsonInclude(JsonInclude.Include.NON_NULL) //为null时不返回
    private String mail;
    @JsonInclude(JsonInclude.Include.NON_NULL) //为null时不返回
    private String mobile;*/


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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
