package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.module.entity.WarningLog;

import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 */
public interface WarningLogService {
    String FAULT="0";//故障
    String FIXED="1";//已处理


    WarningLog queryById(String id);

    PageInfo queryAll();

    void save(WarningLog log);

    void updateFix(String devId);

    int queryFaultCount();

    List<WarningLog> queryByDevAndStatus(String devId,String status);

}
