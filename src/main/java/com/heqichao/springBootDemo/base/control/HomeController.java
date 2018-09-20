package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muzzy Xu.
 * 
 */


@RestController
@RequestMapping(value = "/service")
public class HomeController extends BaseController{

    @Autowired
    private HomeService hService;

    
    @RequestMapping(value = "/getHomePie")
    public ResponeResult getUsers() {
    	return new ResponeResult(hService.queryPieData());
    }

}
