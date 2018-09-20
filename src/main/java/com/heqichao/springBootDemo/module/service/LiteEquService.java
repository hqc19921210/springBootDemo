package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.ResponeResult;

public interface LiteEquService {
	// 正常
	static final String NORMAL = "N"; 
	// 删除
	static final String DELETE = "D";
	
	PageInfo queryLiteEqus();
	ResponeResult addLiteEqu();
	ResponeResult deleteEquByID();
	ResponeResult updLiteEqu();
	ResponeResult cmdLiteEqu() throws Exception;
	ResponeResult getEquForCmd();
	ResponeResult getAppSelectList(); 

}
