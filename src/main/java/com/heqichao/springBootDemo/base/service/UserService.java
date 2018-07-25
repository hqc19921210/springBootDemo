package com.heqichao.springBootDemo.base.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.base.param.ResponeResult;

import java.util.List;
import java.util.Map;

/**
 * @author Muzzy Xu.
 * 
 */


public interface UserService {

	PageInfo queryUsersList();

	ResponeResult insertUser(Map map);

	ResponeResult updateUserPassword(Map map);

	ResponeResult updateUserPasswordByID(Map map);

	ResponeResult deleteUserByID(Map map);


	ResponeResult getCompanySelectList();

	ResponeResult updateUserInfo(Map map);



}
