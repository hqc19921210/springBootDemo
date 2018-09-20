package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.base.mapper.UserMapper;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by heqichao on 2018-2-12.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMap;

    @Override
    public ResponeResult login(String userNo, String password) throws IOException {
            ResponeResult responeResult =new ResponeResult(false,"userLoginError");
        // String enPassword = AesUtil.aesEncrypt(password);
            //直接用前端传来的密文与数据库里的密文对比
            User user = userMap.getUserInfo(userNo,password);
            if(user!=null){
                responeResult =new ResponeResult(user);
                ServletUtil.setSessionUser(user);
            }

        return responeResult;
    }
}
