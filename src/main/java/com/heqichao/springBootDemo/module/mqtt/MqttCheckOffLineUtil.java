package com.heqichao.springBootDemo.module.mqtt;

import com.heqichao.springBootDemo.base.param.ApplicationContextUtil;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import com.heqichao.springBootDemo.module.service.LightningLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by heqichao on 2018-7-30.
 */
@Component
public class MqttCheckOffLineUtil {

    private static final Logger logger = LoggerFactory.getLogger(MqttCheckOffLineUtil.class);

    @Scheduled(cron="0 0/20 * * * ?")
    public void checkOffLine() {
        logger.info(" 定时任务: 检查心跳");
        EquipmentService equipmentService= (EquipmentService) ApplicationContextUtil.getBean("equipmentServiceImpl");

        List<String> allNormallist =equipmentService.getEquipmentByStatus(EquipmentService.NORMAL); //查找所有正常的设备

        if(allNormallist!=null && allNormallist.size()>0){
            List<String> errorList =new ArrayList<>();
           for(String id :allNormallist){
               errorList.add(id);
           }
            LightningLogService lightningLogService= (LightningLogService) ApplicationContextUtil.getBean("lightningLogServiceImpl");
            List<String> mesLog = lightningLogService.queryLogOnTime(20); //查询20分钟内有接收数据的设备
            if(mesLog!=null && mesLog.size()>0){
                for(String hasLogDev :mesLog){
                    for(String allDev :allNormallist){
                        if(hasLogDev.equals(allDev)){
                            errorList.remove(hasLogDev);
                            break;
                        }
                    }
                }
            }
            for(String devId :errorList){
                logger.error( devId+ " 设备下线！！！");
                equipmentService.setEquStatus(devId,EquipmentService.BREAKDOWN);
            }
        }





    }
}
