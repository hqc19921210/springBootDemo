package com.heqichao.springBootDemo.base.entity;

import java.io.Serializable;
import java.util.Map;

public class HomeEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	//管理员或用户
	private String tag;
	//客户总数
	private Integer custAll;
	//用户总数
	private Integer userAll;
	//设备总数
	private Integer equAll;
	//设备在线总数
	private Integer equNom;
	//设备离线总数
	private Integer equBrD;
	//设备故障总数
	private Integer warNum;
	//设备待处理总数
	private Integer toDeal;
	//年雷击总数
	private Integer lightingYear;
	//月平均雷击数
	private String lightingMonth;
	//周平均雷击数
	private String lightingWeek;
	//折线图数据
	private Map plotMap;
	//饼图数据
	private Map pieMap;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getCustAll() {
		return custAll;
	}
	public void setCustAll(Integer custAll) {
		this.custAll = custAll;
	}
	public Integer getUserAll() {
		return userAll;
	}
	public void setUserAll(Integer userAll) {
		this.userAll = userAll;
	}
	public Integer getEquAll() {
		return equAll;
	}
	public void setEquAll(Integer equAll) {
		this.equAll = equAll;
	}
	public Integer getEquNom() {
		return equNom;
	}
	public void setEquNom(Integer equNom) {
		this.equNom = equNom;
	}
	public Integer getEquBrD() {
		return equBrD;
	}
	public void setEquBrD(Integer equBrD) {
		this.equBrD = equBrD;
	}
	public Integer getWarNum() {
		return warNum;
	}
	public void setWarNum(Integer warNum) {
		this.warNum = warNum;
	}
	public Integer getToDeal() {
		return toDeal;
	}
	public void setToDeal(Integer toDeal) {
		this.toDeal = toDeal;
	}
	public Integer getLightingYear() {
		return lightingYear;
	}
	public void setLightingYear(Integer lightingYear) {
		this.lightingYear = lightingYear;
	}
	public String getLightingMonth() {
		return lightingMonth;
	}
	public void setLightingMonth(String lightingMonth) {
		this.lightingMonth = lightingMonth;
	}
	public String getLightingWeek() {
		return lightingWeek;
	}
	public void setLightingWeek(String lightingWeek) {
		this.lightingWeek = lightingWeek;
	}
	public Map getPlotMap() {
		return plotMap;
	}
	public void setPlotMap(Map plotMap) {
		this.plotMap = plotMap;
	}
	public Map getPieMap() {
		return pieMap;
	}
	public void setPieMap(Map pieMap) {
		this.pieMap = pieMap;
	}
	
}
