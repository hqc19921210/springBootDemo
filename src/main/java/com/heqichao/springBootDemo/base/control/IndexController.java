package com.heqichao.springBootDemo.base.action;

import com.heqichao.springBootDemo.base.exception.ResponeException;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.exception.ExceptionMap;
import com.heqichao.springBootDemo.base.util.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heqichao on 2018-2-20.
 */
@Controller
public class IndexController {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private ExceptionMap exceptionMap;


    @GetMapping("/")
    String index() {
        return "/index.html";
    }

/*    @RequestMapping(value = "/systemIndex")
    ResponeResult systemIndex(){
        System.out.println("SystemEnviromment:"+propertiesConfig.getSystemEnviromment());
      try{
          int a =1/0;
      }catch (Exception e){
          throw new ResponeException("001","notLogin");
      }
        return new ResponeResult(false,"xxxx",null);
    }*/

}
