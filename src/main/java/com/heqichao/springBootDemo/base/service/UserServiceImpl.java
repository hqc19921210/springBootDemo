package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.mapper.UserMapper;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		User user = new User(map);
    	Integer uid = ServletUtil.getSessionUser().getId();
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	boolean checkAcc = userMapper.duplicatedAccount(user.getAccount(),user.getCompany());
    	if(		user.getAccount() == null || user.getParentId() == null || user.getCompany() == null ||
    			user.getPassword() == null ||user.getContact() == null || user.getPhone() == null ||
    			user.getCompetence() == null || checkAcc || uid == null || cmp == 4) {
    		String errorMsg = "Create User Input Error!";
    		if(checkAcc) {
    			 errorMsg = "用户名或公司名称重复";
    		}
    		return new ResponeResult(true,errorMsg,"errorMsg");
    	}else {
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
    public ResponeResult updateUserInfo(Map map) {
    	User user = new User(map);
    	Integer uid = ServletUtil.getSessionUser().getId();
    	if(user.getCompany() == null || user.getContact() == null || user.getPhone() == null || uid == null) {
    		return new ResponeResult(true,"UserInfo Input Error!","errorMsg");
    	}else {
    		user.setId(uid);
    		if(userMapper.updateUserInfo(user)>0) {
    			User newUser = ServletUtil.getSessionUser();
    			newUser.setCompany(user.getCompany());
    			newUser.setContact(user.getContact());
    			newUser.setPhone(user.getPhone());
    			newUser.setFax(user.getFax());
    			newUser.setEmail(user.getEmail());
    			newUser.setSite(user.getSite());
    			newUser.setRemark(user.getRemark());
    			ServletUtil.setSessionUser(newUser);
    			return new ResponeResult();
    		}
    	}
    	return  new ResponeResult(true,"Updated UserInfo fail","errorMsg");
    }
    
    @Override
    public ResponeResult getCompanySelectList() {
    	Integer cmp = ServletUtil.getSessionUser().getCompetence();
    	if(cmp == 2) {
    		Map<String, Integer> res =  userMapper.getCompanySelectList().stream().collect(
    						Collectors.toMap(User::getCompany,User::getId, (k1,k2)->k1)
    					);
    		return new ResponeResult(res);
    	}
    	return  new ResponeResult(false,"");
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
