package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.system.entity.SystemUserInfo;

import java.util.List;

/**
 * Created by heqichao on 2018-2-24.
 */
public interface SystemUserInfoService {

    SystemUserInfo queryById(String id);
    List<SystemUserInfo> queryAll();

}
