package com.heqichao.springBootDemo.system.service;

import org.springframework.stereotype.Service;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface LoginService {

     boolean volicateLogin(String userNo,String password);
}
