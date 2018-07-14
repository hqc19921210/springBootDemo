package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.base.mapper.UserMapper;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.util.AesUtil;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by heqichao on 2018-2-12.
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMap;

    @Override
    public ResponeResult login(String userNo, String password) throws IOException {
            ResponeResult responeResult =new ResponeResult(false,"");
            String enPassword = AesUtil.aesEncrypt(password);
            User user = userMap.getUserInfo(userNo,enPassword);
//            UserInfo userInfo =new UserInfo();
//            userInfo.setId(123);
            responeResult.setResultObj(user);
            responeResult.setSuccess(true);
            ServletUtil.setSessionUser(user);
        return responeResult;
    }
}
