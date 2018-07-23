package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.util.PageUtil;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import com.heqichao.springBootDemo.module.mapper.LightningLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 */
@Service
public class LightningLogServiceImpl implements LightningLogService {

    @Autowired
    private LightningLogMapper lightningLogMapper;

    @Override
    public LightningLog queryById(String id) {
        return null;
    }

    @Override
    public PageInfo queryAll() {
        List<String> list =new ArrayList<String>();
        list.add("1");
        PageUtil.setPage();
        PageInfo pageInfo = new PageInfo(lightningLogMapper.queryLightningLogByDevIds(list));
        return pageInfo;

    }

    @Override
    public void save(LightningLog log) {
        lightningLogMapper.saveLightningLog(log);
    }

    @Override
    public void setDevError(String devId, Date time) {

    }
}
