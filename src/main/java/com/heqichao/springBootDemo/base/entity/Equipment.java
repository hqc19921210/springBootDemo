package com.heqichao.springBootDemo.base.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Muzzy Xu.
 */
@Component("equipment")
public class Equipment extends BaseEntity  {


	private static final long serialVersionUID = -1596449961625624849L;
	private Integer eid;
	private String type;
	private Integer amount;
	private String range;
    private Integer total;
    private Integer alarms;
    private String eStatus;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date onlineTime;
    
    private Integer ownId;
    private Integer updateUid;
    
    public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
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
	

}
