package com.heqichao.springBootDemo.base.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heqichao on 2018-2-12.
 */
//@RestController == @Controller + @ResponseBody
@RestController
public class LoginController extends BaseController{

 /*   @Autowired
    private LoginService loginSerice;

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponeResult login(String userNo,String password){
        ResponeResult responeResult =new ResponeResult(false,"userIsNotLogin");

        if(loginSerice.volicateLogin(userNo,password)){
            SystemUserInfo userInfo =new SystemUserInfo();
            userInfo.setPassword("123456");
            userInfo.setUserNo("admin");
            userInfo.setUserName("管理员");
            responeResult.setResultObj(userInfo);
            responeResult.setSuccess(true);
        }
        return responeResult;
    }*/


    @RequestMapping(value = "/logout" ,method = RequestMethod.GET)
    public String logout(){
     //   ResponeResult responeResult =new ResponeResult(false,"userIsNotLogin");
     //   loginSerice.volicateLogin(userNo,password);
        return "login out";
    }


}
