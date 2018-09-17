package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.entity.HomeEntity;

/**
 * @author Muzzy Xu.
 * 
 */


public interface HomeService {
	//设备只有在线和离线线状态，所以把故障状态也改为下线状态B
	String ADMIN = "ADM";
	String USER = "USER";
	Integer ADMINCMP = 2;
	HomeEntity queryPieData();



}
