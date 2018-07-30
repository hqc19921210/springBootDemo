package com.heqichao.springBootDemo.module.mqtt;

import com.heqichao.springBootDemo.base.param.ApplicationContextUtil;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by heqichao on 2018-7-30.
 */
@Component
public class MqttCheckOffLineUtil {

    private static final Logger logger = LoggerFactory.getLogger(MqttCheckOffLineUtil.class);

    @Scheduled(cron="0 0/1 * * * ?")
    public void checkOffLine() {

        EquipmentService service= (EquipmentService) ApplicationContextUtil.getBean("equipmentServiceImpl");
        service.getEquipmentIdListAll();
        // 间隔5分钟,执行任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务1:"+current.getId());
        logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:"+current.getId()+ ",name:"+current.getName());
    }
}
