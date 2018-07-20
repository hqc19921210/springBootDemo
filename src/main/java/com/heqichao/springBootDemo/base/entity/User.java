package com.heqichao.springBootDemo.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heqichao.springBootDemo.base.util.StringUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Muzzy Xu.
 * 
 * 
 */
@Component("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer parentId;
    private String account;
    
    @JsonIgnore//json返回时忽略该字段
    private String password;
    
    private String company;
    private String contact;
    private String phone;
    private String fax;
    private String email;
    private String site;
    private String remark;
    private String status;
    private Integer competence;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    private Integer upadteUID;
    
    
    
	public User() {
    	
    }
    public User(Map map) {
    	this.id = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"id"));
    	this.parentId = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"parentId"));
    	this.account = StringUtil.getStringByMap(map,"account");
    	this.password = StringUtil.getStringByMap(map,"encordPwd");
    	this.company = StringUtil.getStringByMap(map,"company");
    	this.contact = StringUtil.getStringByMap(map,"contact");
    	this.phone = StringUtil.getStringByMap(map,"phone");
    	this.fax = StringUtil.getStringByMap(map,"fax");
    	this.email = StringUtil.getStringByMap(map,"email");
    	this.site = StringUtil.getStringByMap(map,"site");
    	this.remark = StringUtil.getStringByMap(map,"remark");
    	this.competence = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"competence"));
    }
    
    public void reset() {
    	this.id = null;
    	this.parentId = null;
    	this.account = null;
    	this.password = null;
    	this.company = null;
    	this.contact = null;
    	this.phone = null;
    	this.fax = null;
    	this.email = null;
    	this.site = null;
    	this.remark = null;
    	this.status = null;
    	this.competence = null;
    	this.createTime = null;
    	this.updateTime = null;
    	this.upadteUID = null;
        
    }
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCompetence() {
		return competence;
	}
	public void setCompetence(Integer competence) {
		this.competence = competence;
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
	public Integer getUpadteUID() {
		return upadteUID;
	}
	public void setUpadteUID(Integer upadteUID) {
		this.upadteUID = upadteUID;
	}




}
