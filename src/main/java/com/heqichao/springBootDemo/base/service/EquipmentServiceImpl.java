package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.mapper.EquipmentMapper;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.entity.Equipment;
import com.heqichao.springBootDemo.base.util.PageUtil;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import com.heqichao.springBootDemo.module.mqtt.MqttUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Muzzy Xu.
 * 
 */


@Service
@Transactional(rollbackFor = { Exception.class })
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentMapper eMapper ;

    @Override
    public PageInfo queryEquipmentList() {
    	PageUtil.setPage();
        PageInfo pageInfo = new PageInfo(eMapper.getEquipments(ServletUtil.getSessionUser()));
    	return pageInfo;
    }
    @Override
    public List<String> getUserEquipmentIdList(Integer uid) {
    	return eMapper.getUserEquipmentIdList(uid);
    }
    @Override
    public List<String> getEquipmentIdListAll() {
    	return eMapper.getEquipmentIdListAll();
    }
    
    // 根据杆塔ID设置状态
    @Override
    public void setEquStatus(String eid,String status) {
    	 eMapper.setEquStatus(eid,status);
    }
    // 根据状态获取有效设备杆塔ID数组
    @Override
    public List<String> getEquipmentByStatus(String status) {
    	return eMapper.getEquipmentByStatus(status);
    }
    
    @Override
    public ResponeResult insertEqu(Map map) {
    	Equipment equ = new Equipment(map);
    	Integer uid = ServletUtil.getSessionUser().getId();
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	Integer oid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"seleCompany"));
    	if(equ.getEid() == null || uid == null || cmp == 4) {
    		return new ResponeResult(true,"Add Equipment Input Error!","errorMsg");
    	}
    	if(eMapper.duplicatedEid(equ.getEid())) {
    		return new ResponeResult(true,"杆塔Id重复","errorMsg");
    	}
    	if(cmp == 2 && oid == null) {
    		return new ResponeResult(true,"Add Equipment Input Error!","errorMsg");
		}else {
    		if(cmp == 2) {
    			equ.setOwnId(oid);
    		}else {
    			equ.setOwnId(uid);
    		}
    		equ.setUpdateUid(uid);
    		equ.seteStatus("N");
    		if(eMapper.insertEquipment(equ)>0) {
    			List<String> mqId = new ArrayList<String>();
    			mqId.add(equ.getEid());
    			try {
					MqttUtil.subscribeTopicMes(mqId);
					return new ResponeResult();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
    		}
    	}
    	return  new ResponeResult(true,"Add Equipment fail","errorMsg");
    }
    
    @Override
    public ResponeResult deleteEquByID(Map map) {
    	Integer eid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"eid"));
    	Integer udid = ServletUtil.getSessionUser().getId();
    	if(  eid == null || udid == null) {
    		return new ResponeResult(true,"Delete fail!","errorMsg");
    	}else {
    		if(eMapper.delEquById(eid,udid)>0) {
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Delete Equipment fail","errorMsg");
    }

}
