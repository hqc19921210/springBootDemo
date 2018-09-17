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
@Component("lite_application")
public class LiteApplication extends BaseEntity  {


	private static final long serialVersionUID = 1L;
	private String appId;
	private String appName;
	private String secret;
	private String platformIp;
	private String appAuth;
	private String postAsynCmd;
	private String subscribeNotifycation;
	private String callbackUrl;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    private String remark;
    private Integer ownId;
    private String status;
    private Integer updateUid;
    
    public LiteApplication() {
    	
    }
    public LiteApplication(Map map) {
    	super.id=StringUtil.objectToInteger(StringUtil.getStringByMap(map,"id"));
    	this.appId = StringUtil.getStringByMap(map,"appId");
    	this.appName = StringUtil.getStringByMap(map,"appName");
    	this.secret = StringUtil.getStringByMap(map,"secret");
    	this.platformIp = StringUtil.getStringByMap(map,"platformIp");
    	this.appAuth = StringUtil.getStringByMap(map,"appAuth");
    	this.postAsynCmd = StringUtil.getStringByMap(map,"postAsynCmd");
    	this.subscribeNotifycation = StringUtil.getStringByMap(map,"subscribeNotifycation");
    	this.callbackUrl = StringUtil.getStringByMap(map,"callbackUrl");
    	this.remark = StringUtil.getStringByMap(map,"remark");
    	this.ownId = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"ownId"));
    }
    
    
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getPlatformIp() {
		return platformIp;
	}
	public void setPlatformIp(String platformIp) {
		this.platformIp = platformIp;
	}
	public String getAppAuth() {
		return appAuth;
	}
	public void setAppAuth(String appAuth) {
		this.appAuth = appAuth;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getPostAsynCmd() {
		return postAsynCmd;
	}
	public void setPostAsynCmd(String postAsynCmd) {
		this.postAsynCmd = postAsynCmd;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getSubscribeNotifycation() {
		return subscribeNotifycation;
	}
	public void setSubscribeNotifycation(String subscribeNotifycation) {
		this.subscribeNotifycation = subscribeNotifycation;
	}
	

}
