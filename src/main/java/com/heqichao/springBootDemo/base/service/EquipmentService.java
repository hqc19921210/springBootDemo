package com.heqichao.springBootDemo.base.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.ResponeResult;

import java.util.List;
import java.util.Map;

/**
 * @author Muzzy Xu.
 * 
 */


public interface EquipmentService {
	String FAULT = "F";
	String NORMAL = "N";
	String BREAKDOWN = "B";

	PageInfo queryEquipmentList();

	List<String> getUserEquipmentIdList(Integer uid);

	List<String> getEquipmentIdListAll();

	ResponeResult insertEqu(Map map);

	ResponeResult deleteEquByID(Map map);


	void setEquStatus(String eid, String status);

	List<String> getEquipmentByStatus(String status);




}
