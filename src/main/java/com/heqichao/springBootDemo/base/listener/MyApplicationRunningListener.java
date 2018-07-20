package com.heqichao.springBootDemo.base.listener;

import com.heqichao.springBootDemo.module.mqtt.MqttUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by heqichao on 2018-7-15.
 */
@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class MyApplicationRunningListener implements ApplicationRunner {

    @Autowired
    private MqttUtil mqttUtil;

    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("应用启动成功："+new Date());
//        initTask();
    }

    private void initTask(){
        mqttUtil.init();
    }

}
