package com.heqichao.springBootDemo.module.entity;

import java.util.Map;

import com.heqichao.springBootDemo.base.util.StringUtil;

/**
 * @author Muzzy Xu.
 * 
 */
public class LiteCommand extends LiteEquipment  {


	private static final long serialVersionUID = 1L;
	private String appIdDetial;
	private String secret;
	private String callbackUrl;
	private String appAuth;
	//服务名称
	private String serviceId;
	//访问模式
	private String method;
	//命令下发字段
	private String paramField;
	//命令下发值
	private String paramValue;
    private String postAsynCmd;
    
    
    public LiteCommand() {
    	
    }
    public LiteCommand(Map map) {
    	super(map);
    	this.appIdDetial = StringUtil.getStringByMap(map,"appIdDetial");
    	this.secret = StringUtil.getStringByMap(map,"secret");
    	this.callbackUrl = StringUtil.getStringByMap(map,"callbackUrl");
    	this.postAsynCmd = StringUtil.getStringByMap(map,"postAsynCmd");
    	this.appAuth = StringUtil.getStringByMap(map,"appAuth");
    	this.serviceId = StringUtil.getStringByMap(map,"serviceId");
    	this.method = StringUtil.getStringByMap(map,"method");
    	this.paramField = StringUtil.getStringByMap(map,"paramField");
    	this.paramValue = StringUtil.getStringByMap(map,"paramValue");
    	
    }
	public String getAppIdDetial() {
		return appIdDetial;
	}
	public void setAppIdDetial(String appIdDetial) {
		this.appIdDetial = appIdDetial;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParas() {
		return "{\""+this.paramField+"\":\""+this.paramValue+"\"}";
	}
	public String getParamField() {
		return paramField;
	}
	public void setParamField(String paramField) {
		this.paramField = paramField;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getPostAsynCmd() {
		return postAsynCmd;
	}
	public void setPostAsynCmd(String postAsynCmd) {
		this.postAsynCmd = postAsynCmd;
	}
	public String getAppAuth() {
		return appAuth;
	}
	public void setAppAuth(String appAuth) {
		this.appAuth = appAuth;
	}
    
	
	

}
