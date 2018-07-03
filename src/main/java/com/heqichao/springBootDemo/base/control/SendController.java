package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.SendService;
import com.heqichao.springBootDemo.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heqichao on 2018-2-12.
 */
@RestController
public class SendController extends BaseController{

    @Autowired
    private SendService sendService;

    @RequestMapping(value = "/send")
    @ResponseBody
    public ResponeResult sendSms(String type){
        if(StringUtil.isNotEmpty(type)){
            switch (type){
                case "SMS":sendService.sendSms("","","",false);break;
                default:break;
            }
        }

        return new ResponeResult();
    }

}
