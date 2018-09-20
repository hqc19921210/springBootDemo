package com.heqichao.springBootDemo.base.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.ResponeResult;

import java.util.Map;

/**
 * @author Muzzy Xu.
 * 
 */


public interface UserService {
	//管理员
	Integer ROOT =2;
	//用户
	Integer CUSTOMER =3;
	//访客
	Integer VISTOR =4;
	PageInfo queryUsersList();

	ResponeResult insertUser(Map map);

	ResponeResult updateUserPassword(Map map);

	ResponeResult updateUserPasswordByID(Map map);

	ResponeResult deleteUserByID(Map map);


	ResponeResult getCompanySelectList();

	ResponeResult updateUserInfo(Map map);



}
