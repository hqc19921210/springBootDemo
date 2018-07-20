package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.param.PageInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by heqichao on 2018-2-20.
 */
@RestController
@RequestMapping(value = "/service")
public class UserInfoController extends BaseController{

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getAllUser")
    ResponeResult all() {
        return new ResponeResult(userInfoService.queryAll());
    }

    @RequestMapping(value = "/getUser")
    ResponeResult getOne(String id) {
        Map mm =RequestContext.getContext().getParamMap();
        System.out.println(mm.get("id"));
        System.out.println(id);
        return new ResponeResult(userInfoService.queryById(id));
    }
    
    

    @RequestMapping(value = "/test")
    ResponeResult test(PageInfo pageInfo) {
        Map mm =RequestContext.getContext().getParamMap();
        System.out.println(pageInfo.getPageSize());
        System.out.println(pageInfo.getSize());

        return new ResponeResult(pageInfo);
    }
}
