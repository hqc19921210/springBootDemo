package com.heqichao.springBootDemo.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 * @author Muzzy Xu.
 * 
 * 
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int uId;
    private int parentId;
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
    private String competence;
    
    public User() {
    	
    }
    public User(String account,String password) {
    	this.account = account;
    	this.password = password;
    }
    
    public void reset() {
    	this.uId = 0;
    	this.parentId = 0;
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
        
    }
    
    
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
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
	public String getCompetence() {
		return competence;
	}
	public void setCompetence(String competence) {
		this.competence = competence;
	}




}
