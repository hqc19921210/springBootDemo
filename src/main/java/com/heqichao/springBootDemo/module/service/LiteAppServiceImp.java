package com.heqichao.springBootDemo.module.service;

import java.util.HashMap;
import java.util.Map;

import com.heqichao.springBootDemo.module.entity.LiteApplication;
import com.heqichao.springBootDemo.module.liteNA.HttpsUtil;
import com.heqichao.springBootDemo.module.liteNA.JsonUtil;
import com.heqichao.springBootDemo.module.liteNA.StreamClosedHttpResponse;
import com.heqichao.springBootDemo.module.mapper.LiteAppMapper;
import com.heqichao.springBootDemo.module.liteNA.Constant;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.util.AesUtil;
import com.heqichao.springBootDemo.base.util.PageUtil;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;

/**
 * 
 * @author Muzzy XU.
 *
 */

@Service
@Transactional
public class LiteAppServiceImp implements LiteAppService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LiteAppMapper liteAppMapper;
	

	@Override
	public PageInfo queryLiteApps() {
		Map map = RequestContext.getContext().getParamMap();
    	String deviceId = StringUtil.getStringByMap(map,"appId");
    	String name = StringUtil.getStringByMap(map,"appName");
    	PageUtil.setPage();
        PageInfo pageInfo = new PageInfo(liteAppMapper.queryLiteApps(
        		ServletUtil.getSessionUser().getId(),
        		ServletUtil.getSessionUser().getParentId(),
        		ServletUtil.getSessionUser().getCompetence(),
        		deviceId,name
        		));
		return pageInfo;
	}
	
	@Override
    public ResponeResult addLiteApp() {
		Map map = RequestContext.getContext().getParamMap();
		LiteApplication app = new LiteApplication(map);
    	Integer uid = ServletUtil.getSessionUser().getId();
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	Integer oid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"seleCompany"));
    	if(app.getAppId() == null || uid == null || cmp == 4) {
    		return new ResponeResult(true,"Add Application Input Error!","errorMsg");
    	}
    	if(liteAppMapper.duplicatedAid(app.getAppId())) {
    		return new ResponeResult(true,"应用Id重复","errorMsg");
    	}
    	if(cmp == 2 && oid == null) {
    		return new ResponeResult(true,"Add Application Input Error!","errorMsg");
		}else {
    		if(cmp == 2) {
    			app.setOwnId(oid);
    		}else {
    			app.setOwnId(uid);
    		}
    		String appAuth = "https://"+app.getPlatformIp()+"/iocm/app/sec/v1.1.0/login";
    		String callbackUrl = "http://120.78.181.134:8888/service/nbiotCallback/"+app.getAppId().replaceAll("-", "");
    		String postAsynCmd = "https://"+app.getPlatformIp()+"/iocm/app/cmd/v1.4.0/deviceCommands";
    		String subscribeNotifycation = "https://"+app.getPlatformIp()+"/iocm/app/sub/v1.1.0/subscribe";
    		app.setAppAuth(appAuth);
    		app.setCallbackUrl(callbackUrl);
    		app.setPostAsynCmd(postAsynCmd);
    		app.setSubscribeNotifycation(subscribeNotifycation);
    		app.setUpdateUid(uid);
    		app.setStatus("N");
    		app.setSecret(AesUtil.aesEncrypt(app.getSecret()));
    		if(liteAppMapper.insertLiteApplication(app)>0) {
					return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Add Application fail","errorMsg");
    }
	
	@Override
	public ResponeResult updLiteApp() {
		Map map = RequestContext.getContext().getParamMap();
		LiteApplication app = new LiteApplication(map);
		Integer uid = ServletUtil.getSessionUser().getId();
		Integer cmp = ServletUtil.getSessionUser().getCompetence();
		if(app.getAppId() == null || uid == null || cmp == 4) {
			return new ResponeResult(true,"Update Application Input Error!","errorMsg");
		}
		if(liteAppMapper.duplicatedEidByUpd(app.getAppId(),app.getId())) {
			return new ResponeResult(true,"应用Id重复","errorMsg");
		}
		String appAuth = "https://"+app.getPlatformIp()+"/iocm/app/sec/v1.1.0/login";
		String callbackUrl = "http://120.78.181.134:8888/service/nbiotCallback/"+app.getAppId().replaceAll("-", "");
		String postAsynCmd = "https://"+app.getPlatformIp()+"/iocm/app/cmd/v1.4.0/deviceCommands";
		String subscribeNotifycation = "https://"+app.getPlatformIp()+"/iocm/app/sub/v1.1.0/subscribe";
		app.setAppAuth(appAuth);
		app.setCallbackUrl(callbackUrl);
		app.setPostAsynCmd(postAsynCmd);
		app.setSubscribeNotifycation(subscribeNotifycation);
		app.setUpdateUid(uid);
		if(liteAppMapper.updateLiteEquipment(app)>0) {
			return new ResponeResult();
		}
		return  new ResponeResult(true,"Update Application fail","errorMsg");
	}
	
	@Override
	public ResponeResult resetSecret() {
		Map map = RequestContext.getContext().getParamMap();
		LiteApplication app = new LiteApplication(map);
		Integer uid = ServletUtil.getSessionUser().getId();
		Integer cmp = ServletUtil.getSessionUser().getCompetence();
		if(app.getId() == null ||app.getSecret() == null || uid == null || cmp == 4) {
			return new ResponeResult(true,"Reset Input Error!","errorMsg");
		}
		app.setSecret(AesUtil.aesEncrypt(app.getSecret()));
		app.setUpdateUid(uid);
		if(liteAppMapper.resetAppSecret(app)>0) {
			return new ResponeResult();
		}
		return  new ResponeResult(true,"Reset fail","errorMsg");
	}

	@Override
    public ResponeResult deleteAppByID() {
		Map map = RequestContext.getContext().getParamMap();
    	Integer eid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"eid"));
    	Integer udid = ServletUtil.getSessionUser().getId();
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	if(  eid == null || udid == null || cmp == 4) {
    		return new ResponeResult(true,"Delete fail!","errorMsg");
    	}else {
    		if(liteAppMapper.deleteById(eid,udid)>0) {
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Delete Application fail","errorMsg");
    }
	@Override
	public ResponeResult subLiteDataChg() throws Exception {
		Map map = RequestContext.getContext().getParamMap();
		LiteApplication app = new LiteApplication(map);
		Integer udid = ServletUtil.getSessionUser().getId();
		if(  app.getAppId() == null || udid == null ) {
			return new ResponeResult(true,"Subscribe fail!","errorMsg");
		}
		
		// Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
		String appId=app.getAppId();
        String secret=AesUtil.aesDecrypt(app.getSecret());
        String urlLogin=app.getAppAuth();
        String accessToken = login(httpsUtil,appId,secret,urlLogin);
        
        /*
         * subscribe deviceDataChanged notification
         */
        Map<String, Object> paramSubscribe_deviceDataChanged = new HashMap<>();
        paramSubscribe_deviceDataChanged.put("notifyType", Constant.DEVICE_DATA_CHANGED);
        paramSubscribe_deviceDataChanged.put("callbackurl", app.getCallbackUrl());

        String jsonRequest_deviceDataChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDataChanged);

        Map<String, String> header_deviceDataChanged = new HashMap<>();
        header_deviceDataChanged.put(Constant.HEADER_APP_KEY, appId);
        header_deviceDataChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse httpResponse_deviceDataChanged = httpsUtil.doPostJson(app.getSubscribeNotifycation(), header_deviceDataChanged, jsonRequest_deviceDataChanged);

        String bodySubscribe_deviceDataChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDataChanged);

        logger.info("SubscribeNotification: " + httpResponse_deviceDataChanged.getStatusLine().toString());
        logger.info(bodySubscribe_deviceDataChanged);
        if("HTTP/1.1 201 Created".equals(httpResponse_deviceDataChanged.getStatusLine().toString())) {
        	return  new ResponeResult();
        }
		return  new ResponeResult(true,"Subscribe fail!","errorMsg");
	}
	
	@SuppressWarnings("unchecked")
	public String login(HttpsUtil httpsUtil,String appId,String secret,String urlLogin) throws Exception {
		
		
		Map<String, String> paramLogin = new HashMap<>();
		paramLogin.put("appId", appId);
		paramLogin.put("secret", secret);
		
		StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);
		
		logger.info("app auth success,return accessToken:");
		logger.info(responseLogin.getStatusLine().toString());
		logger.info(responseLogin.getContent());
		
		Map<String, String> data = new HashMap<>();
		data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
		return data.get("accessToken");
	}

	
	
}
