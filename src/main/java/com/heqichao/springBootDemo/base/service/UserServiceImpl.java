package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.mapper.UserMapper;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Muzzy Xu.
 * 
 */


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUsersList() {
    	return userMapper.getUsers(ServletUtil.getSessionUser());
    }
    
	@Override
    public ResponeResult insertUser(Map map) {
    	String account = StringUtil.getStringByMap(map,"account").replaceAll(" ", "");
    	Integer uid = ServletUtil.getSessionUser().getId();
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	boolean checkAcc = userMapper.duplicatedAccount(account);
    	if(		account == null || uid == null ||
    			StringUtil.getStringByMap(map,"encordPwd") == null ||
    			StringUtil.getStringByMap(map,"company") == null ||
    			StringUtil.getStringByMap(map,"contact") == null ||
    			StringUtil.getStringByMap(map,"phone") == null ||
    			StringUtil.getStringByMap(map,"competence") == null ||
    			checkAcc || cmp != 4) {
    		return new ResponeResult(true,"Create User Input Error!","errorMsg");
    	}else {
    		User user = new User(map);
    		user.setParentId(uid);
    		user.setAccount(account);
    		user.setUpadteUID(uid);
    		if(userMapper.insertUser(user)>0) {
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Create user fail","errorMsg");
    }
    
    @Override
    public ResponeResult updateUserPassword(Map map) {
    	String srcPwd = StringUtil.getStringByMap(map,"srcPwd");
    	String newPwd = StringUtil.getStringByMap(map,"newPwd");
    	Integer uid = userMapper.checkPassword(ServletUtil.getSessionUser().getAccount(), srcPwd);
    	if(srcPwd == null || newPwd == null || uid == null) {
    		return new ResponeResult(true,"Password Input Error!","errorMsg");
    	}else {
    		if(userMapper.updateUserPassword(uid,uid,newPwd)>0) {
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Updated password fail","errorMsg");
    }
    
    @Override
    public ResponeResult updateUserPasswordByID(Map map) {
    	String newPwd = StringUtil.getStringByMap(map,"newPwd");
    	Integer uid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"uid"));
    	Integer udid = ServletUtil.getSessionUser().getId();
    	if( newPwd == null || uid == null) {
    		return new ResponeResult(true,"Password Input Error!","errorMsg");
    	}else {
    		if(userMapper.updateUserPassword(uid,udid,newPwd)>0) {
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Updated password fail","errorMsg");
    }
    @Override
    public ResponeResult deleteUserByID(Map map) {
    	Integer uid = StringUtil.objectToInteger(StringUtil.getStringByMap(map,"uid"));
    	Integer udid = ServletUtil.getSessionUser().getId();
    	if(  uid == null || udid == null) {
    		return new ResponeResult(true,"Delete fail!","errorMsg");
    	}else {
    		if(userMapper.delUserById(uid,udid)>0) {
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Delete user fail","errorMsg");
    }

}
