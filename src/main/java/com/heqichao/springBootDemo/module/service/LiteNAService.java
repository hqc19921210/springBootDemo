package com.heqichao.springBootDemo.module.service;

import java.util.List;
import java.util.Map;

import com.heqichao.springBootDemo.module.entity.LiteLog;

public interface LiteNAService {

	Object getDataChange() throws Exception;


	void chg();

	String liangPost(Map vmap) throws Exception;

	List<LiteLog> queryAll();

	void deleteAll();
}
