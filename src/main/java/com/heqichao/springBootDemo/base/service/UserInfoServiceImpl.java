package com.heqichao.springBootDemo.base.service;

/**
 * Created by heqichao on 2018-2-24.
 */

import com.heqichao.springBootDemo.base.mapper.SystemUserInfoMapper;
import com.heqichao.springBootDemo.base.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SystemUserInfoServiceImpl  implements SystemUserInfoService {
    @Autowired
    private SystemUserInfoMapper systemUserInfoMapper;

    @Override
    public UserInfo queryById(String id) {
        return null;
    }

    @Override
    public List<UserInfo> queryAll() {
        return null;
    }
}
