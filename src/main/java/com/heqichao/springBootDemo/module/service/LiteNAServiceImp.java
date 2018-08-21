package com.heqichao.springBootDemo.module.service;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.util.StringUtil;
import com.heqichao.springBootDemo.module.liteNA.Constant;
import com.iotplatform.client.NorthApiClient;
import com.iotplatform.client.NorthApiException;
import com.iotplatform.client.dto.*;
import com.iotplatform.client.invokeapi.Authentication;
import com.iotplatform.client.invokeapi.SignalDelivery;
import com.iotplatform.client.invokeapi.SubscriptionManagement;


@Service
public class LiteNAServiceImp implements LiteNAService {
	private static String map;  

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
		/**---------------------initialize northApiClient------------------------*/
        NorthApiClient northApiClient = initApiClient();
        SignalDelivery signalDelivery = new SignalDelivery(northApiClient);
        
        /**---------------------get accessToken at first------------------------*/
        Authentication authentication = new Authentication(northApiClient);        
        AuthOutDTO authOutDTO = authentication.getAuthToken();        
        String accessToken = authOutDTO.getAccessToken();
        
        /**---------------------post NB-IoT device command------------------------*/
        //this is a test NB-IoT device
        String deviceId = "f08b77ca-d98e-43bd-8e54-ad773c5c3768";
        Integer i = StringUtil.objectToInteger(StringUtil.getStringByMap(vmap,"remark"));
        PostDeviceCommandOutDTO2 pdcOutDTO = postCommand(signalDelivery, deviceId, accessToken,i);
        if (pdcOutDTO != null) {
        	String commandId = pdcOutDTO.getCommandId();
        	/**---------------------update device command------------------------*/
            UpdateDeviceCommandInDTO udcInDTO = new UpdateDeviceCommandInDTO();
            udcInDTO.setStatus("CANCELED");
            UpdateDeviceCommandOutDTO udcOutDTO = signalDelivery.updateDeviceCommand(udcInDTO, commandId, null, accessToken);
            System.out.println(udcOutDTO.toString());
            return udcOutDTO.toString();
		}
        return "失败";
        
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
	@Override
	public void chg() {
		System.out.println(RequestContext.getContext().getRequest());
		this.map=RequestContext.getContext().getParamMap().toString();
		
	}
}
