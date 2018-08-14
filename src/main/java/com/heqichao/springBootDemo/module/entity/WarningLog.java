package com.heqichao.springBootDemo.module.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.heqichao.springBootDemo.base.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 报警
 * Created by heqichao on 2018-8-13.
 */
@Component("warning_log")
public class WarningLog extends BaseEntity {
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

    //状态 0 故障 1 已修复
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
