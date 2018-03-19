package com.heqichao.springBootDemo.system.service;

import org.springframework.stereotype.Service;

/**
 * Created by heqichao on 2018-2-12.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public boolean volicateLogin(String userNo, String password) {
        return "admin".equals(userNo) || "123456".equals(password);
    }
}
