package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muzzy Xu.
 * 
 */


@RestController
@RequestMapping(value = "/service")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    
    @RequestMapping(value = "/getUsers")
    ResponeResult getUsers() {
    	return new ResponeResult(userService.queryUsersList());
    }
    
    @RequestMapping(value = "/addUser" )
    @ResponseBody
    public ResponeResult addUser(@RequestBody Map map) throws Exception {
        return userService.insertUser(map);
    }
    
    @RequestMapping(value = "/updatePwd" )
    @ResponseBody
    public ResponeResult updatePwd(@RequestBody Map map) throws Exception {
    	return userService.updateUserPassword(map);
    }
    
    @RequestMapping(value = "/updatePwdById" )
    @ResponseBody
    public ResponeResult updatePwdById(@RequestBody Map map) throws Exception {
    	return userService.updateUserPasswordByID(map);
    }
    
    @RequestMapping(value = "/deleteUserById" )
    @ResponseBody
    public ResponeResult deleteUserById(@RequestBody Map map) throws Exception {
    	return userService.deleteUserByID(map);
    }

}
