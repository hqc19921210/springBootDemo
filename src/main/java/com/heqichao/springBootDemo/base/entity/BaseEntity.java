package com.heqichao.springBootDemo.base.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by heqichao on 2018-7-1.
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
