package com.heqichao.springBootDemo.base.entity;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;
import com.heqichao.springBootDemo.base.util.StringUtil;

/**
 * @author Muzzy Xu.
 */
@Component("equipment")
public class Equipment extends BaseEntity  {


	private static final long serialVersionUID = -1596449961625624849L;
	private String eid;
	private String eType;
	private Integer amount;
	private String eRange;
    private Integer total;
    private Integer alarms;
    private String eStatus;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date onlineTime;
    
    private String remark;
    private Integer ownId;
    private String status;
    private Integer updateUid;
    
    public Equipment() {
    	
    }
    public Equipment(Map map) {
    	this.eid = StringUtil.getStringByMap(map,"eid");
    	this.eType = StringUtil.getStringByMap(map,"eType");
    	this.amount = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"amount"));
    	this.eRange = StringUtil.getStringByMap(map,"eRange");
    	this.total = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"total"));
    	this.alarms = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"alarms"));
    	this.eStatus = StringUtil.getStringByMap(map,"eStatus");
    	this.remark = StringUtil.getStringByMap(map,"remark");
    	this.ownId = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"ownId"));
    }
    
    public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getType() {
		return eType;
	}
	public void setType(String eType) {
		this.eType = eType;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getRange() {
		return eRange;
	}
	public void setRange(String eRange) {
		this.eRange = eRange;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getAlarms() {
		return alarms;
	}
	public void setAlarms(Integer alarms) {
		this.alarms = alarms;
	}
	public String geteStatus() {
		return eStatus;
	}
	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}
	public Date getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Integer getOwnId() {
		return ownId;
	}
	public void setOwnId(Integer ownId) {
		this.ownId = ownId;
	}
	public Integer getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
