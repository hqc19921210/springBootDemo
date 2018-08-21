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
	//设备只有在线和离线线状态，所以把故障状态也改为下线状态B
	String NORMAL = "N";
	String BREAKDOWN = "B";

	PageInfo queryEquipmentList();

	List<String> getUserEquipmentIdList(Integer uid);

	List<String> getEquipmentIdListAll();

	ResponeResult insertEqu(Map map);

	ResponeResult deleteEquByID(Map map);


	void setEquStatus(String eid, String status);

	List<String> getEquipmentByStatus(String status);

	/**
	 * 更新设备的量程
	 * @param eid
	 * @param status
	 * @return
	 */
	int updateRange(String eid, Integer range);

	/**
	 * 查询设备的量程
	 * @param eid
	 * @return
	 */
	Integer queryRange(String eid);


}
