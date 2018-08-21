package com.heqichao.springBootDemo.module.service;

import java.util.Map;

import com.iotplatform.client.dto.NotifyDeviceDataChangedDTO;

public interface LiteNAService {

	Object getDataChange() throws Exception;


	void chg();

	String liangPost(Map vmap) throws Exception;

}
