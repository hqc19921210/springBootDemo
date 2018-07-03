package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.entity.UserInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.LoginService;
import com.heqichao.springBootDemo.base.util.PropertiesConfig;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.serviceloader.ServiceFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-12.
 */
//@RestController == @Controller + @ResponseBody
@RestController
@RequestMapping(value = "/service")
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginSerice;


    @RequestMapping(value = "/login" )
    @ResponseBody
    public ResponeResult login(@RequestBody Map map) throws Exception {
        String userNo = (String) map.get("userNo");
        System.out.println(userNo);
        ResponeResult responeResult=loginSerice.login("","");
        return responeResult;
    }


    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResponeResult logout(){
        ServletUtil.setSessionUser(null);
        return   ServletUtil.NO_LOGIN_RESULT;
    }


    @RequestMapping(value = "/checkLogin" )
    @ResponseBody
    public ResponeResult checkLogin() throws Exception {
       /* if(ServletUtil.getSessionUser()==null){
            return new ResponeResult(false);
        }*/
        return new ResponeResult(ServletUtil.getSessionUser());
    }



}
