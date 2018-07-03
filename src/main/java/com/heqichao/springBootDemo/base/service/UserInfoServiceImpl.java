package com.heqichao.springBootDemo.base.service;

/**
 * Created by heqichao on 2018-2-24.
 */

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.mapper.UserInfoMapper;
import com.heqichao.springBootDemo.base.entity.UserInfo;
import com.heqichao.springBootDemo.base.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo queryById(String id) {
        return userInfoMapper.getOne(id);
    }

    @Override
    public PageInfo queryAll() {
        //如果需要后台分页 要设置这个 前端传page\size
        PageUtil.setPage();
        List<UserInfo> list= userInfoMapper.getAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
