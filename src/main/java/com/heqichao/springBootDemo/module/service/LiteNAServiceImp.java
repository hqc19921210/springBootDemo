package com.heqichao.springBootDemo.module.service;

import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.heqichao.springBootDemo.module.entity.LiteLog;
import com.heqichao.springBootDemo.module.mapper.LiteLogMapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.util.StringUtil;
import com.heqichao.springBootDemo.module.liteNA.Constant;
import com.heqichao.springBootDemo.module.liteNA.HttpsUtil;
import com.heqichao.springBootDemo.module.liteNA.JsonUtil;
import com.heqichao.springBootDemo.module.liteNA.StreamClosedHttpResponse;
import com.iotplatform.client.NorthApiClient;
import com.iotplatform.client.NorthApiException;
import com.iotplatform.client.dto.*;
import com.iotplatform.client.invokeapi.Authentication;
import com.iotplatform.client.invokeapi.SignalDelivery;
import com.iotplatform.client.invokeapi.SubscriptionManagement;


@Service
public class LiteNAServiceImp implements LiteNAService {
	private static String map;  

	@Autowired
	private LiteLogMapper liteLogMapper;
	@Override
	public Object getDataChange() throws Exception {
//		NorthApiClient northApiClient = initApiClient();
//    	SubscriptionManagement subscriptionManagement = new SubscriptionManagement(northApiClient);
//        
//        /**---------------------get accessToken at first------------------------*/
//        Authentication authentication = new Authentication(northApiClient);        
//        AuthOutDTO authOutDTO = authentication.getAuthToken();        
//        String accessToken = authOutDTO.getAccessToken();
//        
//        /**---------------------sub deviceAdded notification------------------------*/
//        //note: 10.X.X.X is a LAN ip, not a public ip, so subscription callbackUrl's ip cannot be 10.X.X.X
//        String callbackUrl = "http://120.78.181.134:8888/service/liteNaCallback2";//this is a test callbackUrl        
//        SubscriptionDTO subDTO = subDeviceData(subscriptionManagement, "deviceDataChanged", callbackUrl, accessToken);
//        
//        /**---------------------sub device upgrade result notification------------------------*/
//        subDeviceManagementData(subscriptionManagement, "swUpgradeResultNotify", callbackUrl, accessToken);
//        System.out.println("11111111111111111111111");
//        if (subDTO != null) {
//        	System.out.println("222222222222222222222222");
//        	System.out.println(subDTO.toString());
//        	/**---------------------query single subscription------------------------*/
//        	SubscriptionDTO subDTO2 = subscriptionManagement.querySingleSubscription(subDTO.getSubscriptionId(), null, accessToken);
//        	System.out.println(subDTO2.toString());
//        	
//        	/**---------------------delete single subscription------------------------*/
////        	subscriptionManagement.deleteSingleSubscription(subDTO.getSubscriptionId(), null, accessToken);
//        	return subDTO2.toString();
//		}
        return map;
	}
	@Override
	public String liangPost(Map vmap) throws Exception {
//		/**---------------------initialize northApiClient------------------------*/
//        NorthApiClient northApiClient = initApiClient();
//        SignalDelivery signalDelivery = new SignalDelivery(northApiClient);
//        
//        /**---------------------get accessToken at first------------------------*/
//        Authentication authentication = new Authentication(northApiClient);        
//        AuthOutDTO authOutDTO = authentication.getAuthToken();        
//        String accessToken = authOutDTO.getAccessToken();
//        
//        /**---------------------post NB-IoT device command------------------------*/
//        //this is a test NB-IoT device
//        String deviceId = "f08b77ca-d98e-43bd-8e54-ad773c5c3768";
        Integer i = StringUtil.objectToInteger(StringUtil.getStringByMap(vmap,"remark"));
//        PostDeviceCommandOutDTO2 pdcOutDTO = postCommand(signalDelivery, deviceId, accessToken,i);
//        if (pdcOutDTO != null) {
//        	String commandId = pdcOutDTO.getCommandId();
//        	/**---------------------update device command------------------------*/
//            UpdateDeviceCommandInDTO udcInDTO = new UpdateDeviceCommandInDTO();
//            udcInDTO.setStatus("CANCELED");
//            UpdateDeviceCommandOutDTO udcOutDTO = signalDelivery.updateDeviceCommand(udcInDTO, commandId, null, accessToken);
//            System.out.println(udcOutDTO.toString());
//            return udcOutDTO.toString();
//		}
		HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication，get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlPostAsynCmd = Constant.POST_ASYN_CMD;
        String appId = Constant.APPID;

        //please replace the deviceId, when you use the demo.
        String deviceId = "f08b77ca-d98e-43bd-8e54-ad773c5c3768";
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

        //please replace the following parameter values, when you use the demo.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        String serviceId = "In_Current";
        String method = "R";
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"Time_state\":\""+i+"\"}");
      
        Map<String, Object> paramCommand = new HashMap<>();
        paramCommand.put("serviceId", serviceId);
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);      
        
        Map<String, Object> paramPostAsynCmd = new HashMap<>();
        paramPostAsynCmd.put("deviceId", deviceId);
        paramPostAsynCmd.put("command", paramCommand);
        paramPostAsynCmd.put("callbackUrl", callbackUrl);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);

        System.out.println("PostAsynCommand, response content:");
        System.out.print(responsePostAsynCmd.getStatusLine());
        System.out.println(responseBody);
        System.out.println();
        if(responseBody != null) {
        	return responseBody;
        }
        return "失败";
        
	}

	@Override
	public List<LiteLog> queryAll() {
		return liteLogMapper.queryAll();
	}

	@Override
	public void deleteAll() {
		liteLogMapper.deleteAll();
	}

	private static NorthApiClient initApiClient() {
	    	NorthApiClient northApiClient = new NorthApiClient();
	        
	        ClientInfo clientInfo = new ClientInfo();
	        
	        clientInfo.setAppId(Constant.APPID);
	        clientInfo.setPlatformIp(Constant.PLATFORM_IP);
	        clientInfo.setPlatformPort(Constant.PLATFORM_PORT);
	        clientInfo.setSecret(Constant.SECRET);
	        
	        try {
				northApiClient.setClientInfo(clientInfo);
				northApiClient.initSSLConfig();
			} catch (NorthApiException e) {
				System.out.println(e.toString());
			}        
	        
	        return northApiClient;
	    }
	 private static SubscriptionDTO subDeviceData(SubscriptionManagement subscriptionManagement, 
	    		String notifyType, String callbackUrl, String accessToken) {
	    	SubDeviceDataInDTO sddInDTO = new SubDeviceDataInDTO();
	    	sddInDTO.setNotifyType(notifyType);
	    	sddInDTO.setCallbackUrl(callbackUrl);
	    	try {
				return subscriptionManagement.subDeviceData(sddInDTO, null, accessToken);
			} catch (NorthApiException e) {
				System.out.println(e.toString());
			}
	    	return null;
	    }
	 private static void subDeviceManagementData(SubscriptionManagement subscriptionManagement, 
	    		String notifyType, String callbackUrl, String accessToken) {
	    	SubDeviceManagementDataInDTO sddInDTO = new SubDeviceManagementDataInDTO();
	    	sddInDTO.setNotifyType(notifyType);
	    	sddInDTO.setCallbackurl(callbackUrl);
	    	try {
				subscriptionManagement.subDeviceData(sddInDTO, accessToken);
			} catch (NorthApiException e) {
				System.out.println(e.toString());
			}
	    	return;
	    }
	 @SuppressWarnings("unchecked")
		private static PostDeviceCommandOutDTO2 postCommand(SignalDelivery signalDelivery, String deviceId, String accessToken,Integer i) {
	    	PostDeviceCommandInDTO2 pdcInDTO = new PostDeviceCommandInDTO2();
	        pdcInDTO.setDeviceId(deviceId);
	        
	        CommandDTOV4 cmd = new CommandDTOV4();
	        cmd.setServiceId("In_Current");
	        cmd.setMethod("R"); //"PUT" is the command name defined in the profile
	        Map<String, Object> cmdParam = new HashedMap();
	        cmdParam.put("Time_state", i);//"brightness" is the command parameter name defined in the profile
	        
	        cmd.setParas(cmdParam);
	        pdcInDTO.setCommand(cmd);
	        
	        try {
				return signalDelivery.postDeviceCommand(pdcInDTO, null, accessToken);
			} catch (NorthApiException e) {
				System.out.println(e.toString());
			}
	        return null;
	    }
	 public void chg(Map map) {
		 this.map=map.toString();
	 }
	 @SuppressWarnings("unchecked")
	    public static String login(HttpsUtil httpsUtil) throws Exception {

	        String appId = Constant.APPID;
	        String secret = Constant.SECRET;
	        String urlLogin = Constant.APP_AUTH;

	        Map<String, String> paramLogin = new HashMap<>();
	        paramLogin.put("appId", appId);
	        paramLogin.put("secret", secret);

	        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

	        System.out.println("app auth success,return accessToken:");
	        System.out.print(responseLogin.getStatusLine());
	        System.out.println(responseLogin.getContent());
	        System.out.println();

	        Map<String, String> data = new HashMap<>();
	        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
	        return data.get("accessToken");
	    }
	@Override
	public void chg() {
		String mes =RequestContext.getContext().getRequest().toString();
		this.map=mes;

		JSONObject jsonObject =changeMse(mes);
		JSONObject service =jsonObject.getJSONObject("service");
		JSONObject data=service.getJSONObject("data");
		String currenState= (String) data.get("Curren_state");
		String eventTime= (String) service.get("eventTime");
		//20180821T022524Z
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		Date date=null;
		try {
			date=simpleDateFormat.parse(eventTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LiteLog log =new LiteLog(mes,currenState,date);
		liteLogMapper.saveLog(log);

	}

	private  JSONObject changeMse(String mes){
		mes=mes.replaceAll("=",":");
		mes=mes.replaceAll("\"","");
		mes=mes.replaceAll(" ","");
		String newMes ="";
		char[] mesList =mes.toCharArray();
		for(char c :mesList){
			if('{' == c ){
				newMes = newMes+c+"\"";
			}else  if('}' == c){
				newMes = newMes+"\""+c;
			}else if(':' == c || ',' == c){
				newMes = newMes+"\""+c+"\"";
			}else{
				newMes=newMes+c;
			}
		}
		newMes= newMes.replace("\"{\"","{\"");
		newMes= newMes.replace("\"}\"","\"}");
		JSONObject jsonObject = JSONObject.parseObject(newMes);
		return jsonObject;
	}

}
