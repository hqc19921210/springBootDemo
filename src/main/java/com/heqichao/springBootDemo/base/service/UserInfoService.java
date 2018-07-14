package com.heqichao.springBootDemo.base.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.base.entity.UserInfo;

import java.util.List;

/**
 * Created by heqichao on 2018-2-24.
 */
public interface UserInfoService {

    UserInfo queryById(String id);
    PageInfo queryAll();
	List<User> queryUsersList();



}
