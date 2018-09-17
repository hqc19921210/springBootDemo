package com.heqichao.springBootDemo.module.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;
import com.heqichao.springBootDemo.base.entity.BaseEntity;
import com.heqichao.springBootDemo.base.util.StringUtil;

/**
 * @author Muzzy Xu.
 * 
 */
@Component("lite_equipment")
public class LiteEquipment extends BaseEntity  {


	private static final long serialVersionUID = 1L;
	private String deviceId;
	private String name;
	private String verification;
	private String type;
	private String supportId;
	private String supportName;
	private String agreement;
    private String location;
    private Integer appId;
	private String appName;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date onlineTime;
    
    private String remark;
    private Integer ownId;
    private String status;
    private Integer updateUid;
    
    public LiteEquipment() {
    	
    }
    public LiteEquipment(Map map) {
    	super.id=StringUtil.objectToInteger(StringUtil.getStringByMap(map,"id"));
    	this.deviceId = StringUtil.getStringByMap(map,"deviceId");
    	this.name = StringUtil.getStringByMap(map,"name");
    	this.verification = StringUtil.getStringByMap(map,"verification");
    	this.type = StringUtil.getStringByMap(map,"type");
    	this.supportId = StringUtil.getStringByMap(map,"supportId");
    	this.supportName = StringUtil.getStringByMap(map,"supportName");
    	this.agreement = StringUtil.getStringByMap(map,"agreement");
    	this.location = StringUtil.getStringByMap(map,"location");
    	this.remark = StringUtil.getStringByMap(map,"remark");
    	this.ownId = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"ownId"));
    	this.appId = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"appId"));
    }
    
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSupportId() {
		return supportId;
	}
	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}
	public String getSupportName() {
		return supportName;
	}
	public void setSupportName(String supportName) {
		this.supportName = supportName;
	}
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Integer getOwnId() {
		return ownId;
	}
	public void setOwnId(Integer ownId) {
		this.ownId = ownId;
	}
	public Integer getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
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
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	

}
