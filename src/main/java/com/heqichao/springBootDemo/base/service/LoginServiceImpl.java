package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.entity.UserInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.util.PropertiesConfig;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-12.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public ResponeResult login(String userNo, String password) throws IOException {
            ResponeResult responeResult =new ResponeResult(false,"");
            UserInfo userInfo =new UserInfo();
            userInfo.setId(123);
            responeResult.setResultObj(userInfo);
            responeResult.setSuccess(true);
            ServletUtil.setSessionUser(userInfo);
        return responeResult;
    }
}
