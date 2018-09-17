package com.heqichao.springBootDemo.module.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.module.entity.LiteLog;

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
