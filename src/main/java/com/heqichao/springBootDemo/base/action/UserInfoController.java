package com.heqichao.springBootDemo.base.action;

import com.heqichao.springBootDemo.base.mapper.SystemUserInfoMapper;
import com.heqichao.springBootDemo.base.param.PageInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-20.
 */
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private SystemUserInfoMapper systemUserInfoMapper;

    @RequestMapping(value = "/all")
    ResponeResult all() {
        return new ResponeResult(systemUserInfoMapper.getAll());
    }

    @RequestMapping(value = "/get")
    ResponeResult getOne(String id) {
        Map mm =RequestContext.getContext().getParamMap();
        System.out.println(mm.get("id"));
        System.out.println(id);
        return new ResponeResult(systemUserInfoMapper.getOne(id));
    }

    @RequestMapping(value = "/test")
    ResponeResult test(PageInfo pageInfo) {
        Map mm =RequestContext.getContext().getParamMap();
        System.out.println(pageInfo.getPageSize());
        System.out.println(pageInfo.getSize());
        return new ResponeResult(pageInfo);
    }
}
