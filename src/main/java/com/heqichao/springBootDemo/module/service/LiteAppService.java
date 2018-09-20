package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.ResponeResult;

public interface LiteAppService {
	// 正常
	static final String NORMAL = "N"; 
	// 删除
	static final String DELETE = "D";
	
	PageInfo queryLiteApps();
	ResponeResult addLiteApp();
	ResponeResult deleteAppByID();
	ResponeResult updLiteApp();
	ResponeResult resetSecret();
	ResponeResult subLiteDataChg() throws Exception;

}
