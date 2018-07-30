package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.module.entity.LightningLog;

import java.util.Date;

/**
 * Created by heqichao on 2018-7-15.
 */
public interface LightningLogService {
    String HEART_BEAT_NORMAL="0000";//心跳正常
    String HEART_BEAT_ERROR="0001";//心跳异常（设备故障）
    String LIGNHTNING_LOG="1111";//雷击日志
    String OFF_LINE ="CLOES"; //设备下线

    LightningLog queryById(String id);
    PageInfo queryAll();
    void save(LightningLog log);

    void setDevError(String devId,Date time);


}
