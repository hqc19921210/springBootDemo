package com.heqichao.springBootDemo.module.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.heqichao.springBootDemo.base.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 雷击记录
 * Created by heqichao on 2018-7-15.
 */
@Component("lightning_log")
public class LightningLog extends BaseEntity {

    //设备id
    private String devEUI;
    //上报时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date time;
    private int fPort;
    private int gatewayCount;
    private int rssi;
    private int fCnt;
    private float loRaSNR;
    //数据
    private String data;

    //以下为data数据解析后
    //产品地址
    private String devicePath;
    //功能码
    private String functionCode;
    //数据区长度
    private String dataLen;
    //雷击次数
    private int ligntningCount;
    //雷击时间
    private String ligntningTime;
    //电流峰值
    private String peakValue;
    //电流有效值
    private String effectiveValue;
    //电流波头时间
    private String waveHeadTime;
    //电流半峰值时间
    private String halfPeakTime;
    //电流作用时间
    private String actionTime;
    //能量
    private String energy;
    //心跳状态 0000正常 0001故障 空未知 1111为雷击记录 -1为已删除数据
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevEUI() {
        return devEUI;
    }

    public void setDevEUI(String devEUI) {
        this.devEUI = devEUI;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getfPort() {
        return fPort;
    }

    public void setfPort(int fPort) {
        this.fPort = fPort;
    }

    public int getGatewayCount() {
        return gatewayCount;
    }

    public void setGatewayCount(int gatewayCount) {
        this.gatewayCount = gatewayCount;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getfCnt() {
        return fCnt;
    }

    public void setfCnt(int fCnt) {
        this.fCnt = fCnt;
    }

    public float getLoRaSNR() {
        return loRaSNR;
    }

    public void setLoRaSNR(float loRaSNR) {
        this.loRaSNR = loRaSNR;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDevicePath() {
        return devicePath;
    }

    public void setDevicePath(String devicePath) {
        this.devicePath = devicePath;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getDataLen() {
        return dataLen;
    }

    public void setDataLen(String dataLen) {
        this.dataLen = dataLen;
    }

    public int getLigntningCount() {
        return ligntningCount;
    }

    public void setLigntningCount(int ligntningCount) {
        this.ligntningCount = ligntningCount;
    }

    public String getLigntningTime() {
        return ligntningTime;
    }

    public void setLigntningTime(String ligntningTime) {
        this.ligntningTime = ligntningTime;
    }

    public String getPeakValue() {
        return peakValue;
    }

    public void setPeakValue(String peakValue) {
        this.peakValue = peakValue;
    }

    public String getEffectiveValue() {
        return effectiveValue;
    }

    public void setEffectiveValue(String effectiveValue) {
        this.effectiveValue = effectiveValue;
    }

    public String getWaveHeadTime() {
        return waveHeadTime;
    }

    public void setWaveHeadTime(String waveHeadTime) {
        this.waveHeadTime = waveHeadTime;
    }

    public String getHalfPeakTime() {
        return halfPeakTime;
    }

    public void setHalfPeakTime(String halfPeakTime) {
        this.halfPeakTime = halfPeakTime;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "LightningLog{" +
                "devEUI='" + devEUI + '\'' +
                ", time=" + time +
                ", fPort=" + fPort +
                ", gatewayCount=" + gatewayCount +
                ", rssi=" + rssi +
                ", fCnt=" + fCnt +
                ", loRaSNR=" + loRaSNR +
                ", data='" + data + '\'' +
                ", devicePath='" + devicePath + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", dataLen='" + dataLen + '\'' +
                ", ligntningCount=" + ligntningCount +
                ", ligntningTime='" + ligntningTime + '\'' +
                ", peakValue='" + peakValue + '\'' +
                ", effectiveValue='" + effectiveValue + '\'' +
                ", waveHeadTime='" + waveHeadTime + '\'' +
                ", halfPeakTime='" + halfPeakTime + '\'' +
                ", actionTime='" + actionTime + '\'' +
                ", energy='" + energy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
