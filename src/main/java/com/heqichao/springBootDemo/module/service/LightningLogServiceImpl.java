package com.heqichao.springBootDemo.module.service;

import com.heqichao.springBootDemo.base.param.PageInfo;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by heqichao on 2018-7-15.
 */
@Service
public class LightningLogServiceImpl implements LightningLogService {

    @Override
    public LightningLog queryById(String id) {
        return null;
    }

    @Override
    public PageInfo queryAll() {
        return null;
    }

    @Override
    public void save(LightningLog log) {

    }

    @Override
    public void setDevError(String devId, Date time) {

    }
}
