package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.param.ResponeResult;

import java.io.IOException;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface LoginService {



     ResponeResult login(String userNo,String password) throws IOException;
}
