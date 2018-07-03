package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.entity.UserInfo;

import java.util.List;

/**
 * Created by heqichao on 2018-2-24.
 */
public interface SystemUserInfoService {

    UserInfo queryById(String id);
    List<UserInfo> queryAll();

}
