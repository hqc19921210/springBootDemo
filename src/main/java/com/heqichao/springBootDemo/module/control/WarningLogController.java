package com.heqichao.springBootDemo.module.control;

import com.heqichao.springBootDemo.base.control.BaseController;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.module.service.LightningLogService;
import com.heqichao.springBootDemo.module.service.WarningLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heqichao on 2018-7-15.
 */
@RestController
@RequestMapping(value = "/service")
public class WarningLogController extends BaseController{

    @Autowired
    private WarningLogService warningLogService;

    @RequestMapping(value = "/queryWarnings")
    ResponeResult queryLightLogs() {
        return new ResponeResult(warningLogService.queryAll());
    }

    @RequestMapping(value = "/queryFaultCount")
    ResponeResult queryFaultCount() {
        return new ResponeResult(warningLogService.queryFaultCount());
    }
}
