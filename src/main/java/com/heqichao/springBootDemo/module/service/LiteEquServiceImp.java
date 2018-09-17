package com.heqichao.springBootDemo.module.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.heqichao.springBootDemo.module.mapper.LiteEquMapper;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.util.AesUtil;
import com.heqichao.springBootDemo.base.util.PageUtil;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import com.heqichao.springBootDemo.module.entity.LiteCommand;
import com.heqichao.springBootDemo.module.entity.LiteEquipment;
import com.heqichao.springBootDemo.module.liteNA.Constant;
import com.heqichao.springBootDemo.module.liteNA.HttpsUtil;
import com.heqichao.springBootDemo.module.liteNA.JsonUtil;
import com.heqichao.springBootDemo.module.liteNA.StreamClosedHttpResponse;


@Service
@Transactional
public class LiteEquServiceImp implements LiteEquService {
	

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LiteEquMapper liteEquMapper;
	

	@Override
	public PageInfo queryLiteEqus() {
		Map map = RequestContext.getContext().getParamMap();
    	String deviceId = StringUtil.getStringByMap(map,"deviceId");
    	String name = StringUtil.getStringByMap(map,"name");
    	String agreement = StringUtil.getStringByMap(map,"agreement");
    	PageUtil.setPage();
        PageInfo pageInfo = new PageInfo(liteEquMapper.queryLiteEqus(
        		ServletUtil.getSessionUser().getId(),
        		ServletUtil.getSessionUser().getParentId(),
        		ServletUtil.getSessionUser().getCompetence(),
        		deviceId,name,agreement
        		));
		return pageInfo;
	}
	
	@Override
    public ResponeResult getAppSelectList() {
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	Integer uid = ServletUtil.getSessionUser().getId();
    	Integer pid = ServletUtil.getSessionUser().getParentId();
    	if(uid == null || cmp == null) {
    		return  new ResponeResult(false,"");
    	}
		Map<String, Integer> res =  liteEquMapper.getAppSelectList(uid,pid,cmp).stream().collect(
						Collectors.toMap(LiteEquipment::getAppName,LiteEquipment::getAppId, (k1,k2)->k1)
					);
		return new ResponeResult(res);
    }
	
	@Override
    public ResponeResult addLiteEqu() {
		Map map = RequestContext.getContext().getParamMap();
		LiteEquipment equ = new LiteEquipment(map);
    	Integer uid = ServletUtil.getSessionUser().getId();
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	Integer oid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"seleCompany"));
    	if(equ.getDeviceId() == null || uid == null || cmp == 4) {
    		return new ResponeResult(true,"Add Equipment Input Error!","errorMsg");
    	}
    	if(liteEquMapper.duplicatedEid(equ.getDeviceId())) {
    		return new ResponeResult(true,"设备Id重复","errorMsg");
    	}
    	if(cmp == 2 && oid == null) {
    		return new ResponeResult(true,"Add Equipment Input Error!","errorMsg");
		}else {
    		if(cmp == 2) {
    			equ.setOwnId(oid);
    		}else {
    			equ.setOwnId(uid);
    		}
    		if(liteEquMapper.checkAppOwnId(equ.getOwnId())) {
    			return new ResponeResult(true,"应用与设备拥有者不一致","errorMsg");
    		}
    		equ.setUpdateUid(uid);
    		equ.setStatus("N");
    		if(liteEquMapper.insertLiteEquipment(equ)>0) {
					return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Add Equipment fail","errorMsg");
    }
	
	@Override
	public ResponeResult updLiteEqu() {
		Map map = RequestContext.getContext().getParamMap();
		LiteEquipment equ = new LiteEquipment(map);
		Integer uid = ServletUtil.getSessionUser().getId();
		Integer cmp = ServletUtil.getSessionUser().getCompetence();
		if(equ.getDeviceId() == null || uid == null || cmp == 4) {
			return new ResponeResult(true,"Update Equipment Input Error!","errorMsg");
		}
		if(liteEquMapper.duplicatedEidByUpd(equ.getDeviceId(),equ.getId())) {
			return new ResponeResult(true,"设备Id重复","errorMsg");
		}
		if(liteEquMapper.checkAppOwnId(equ.getOwnId())) {
			return new ResponeResult(true,"应用与设备拥有者不一致","errorMsg");
		}
		equ.setUpdateUid(uid);
		if(liteEquMapper.updateLiteEquipment(equ)>0) {
			return new ResponeResult();
		}
		return  new ResponeResult(true,"Update Equipment fail","errorMsg");
	}
	
	@Override
	public ResponeResult getEquForCmd() {
		Map map = RequestContext.getContext().getParamMap();
		Integer eid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"eid"));
		return  new ResponeResult(liteEquMapper.queryLiteEquForCmd(eid));
	}
	
	@Override
	public ResponeResult cmdLiteEqu() throws Exception {
		Map map = RequestContext.getContext().getParamMap();
		LiteCommand cmdPost = new LiteCommand(map);
		Integer uid = ServletUtil.getSessionUser().getId();
		Integer cmp = ServletUtil.getSessionUser().getCompetence();
		HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        String appId=cmdPost.getAppIdDetial();
        String secret=AesUtil.aesDecrypt(cmdPost.getSecret());
        String urlLogin=cmdPost.getAppAuth();
        String accessToken = login(httpsUtil,appId,secret,urlLogin);
        ObjectNode paras = JsonUtil.convertObject2ObjectNode(cmdPost.getParas());
        
		Map<String, Object> paramCommand = new HashMap<>();
        paramCommand.put("serviceId", cmdPost.getServiceId());
        paramCommand.put("method", cmdPost.getMethod());
        paramCommand.put("paras", paras);      
        
        Map<String, Object> paramPostAsynCmd = new HashMap<>();
        paramPostAsynCmd.put("deviceId", cmdPost.getDeviceId());
        paramPostAsynCmd.put("command", paramCommand);
        paramPostAsynCmd.put("callbackUrl", cmdPost.getCallbackUrl());
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(cmdPost.getPostAsynCmd(), header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
        
        logger.info("PostAsynCommand, response content:");
        logger.info(responsePostAsynCmd.getStatusLine().toString());
        logger.info(responseBody);
        if(responseBody != null) {
        	liteEquMapper.updateLiteEquipmentForCmd(cmdPost);
        	return new ResponeResult(responseBody);
        }
		return  new ResponeResult(true,"失败","errorMsg");
	}

	@Override
    public ResponeResult deleteEquByID() {
		Map map = RequestContext.getContext().getParamMap();
    	Integer eid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"eid"));
    	Integer udid = ServletUtil.getSessionUser().getId();
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	if(  eid == null || udid == null || cmp == 4) {
    		return new ResponeResult(true,"Delete fail!","errorMsg");
    	}else {
    		if(liteEquMapper.deleteById(eid,udid)>0) {
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Delete Equipment fail","errorMsg");
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
