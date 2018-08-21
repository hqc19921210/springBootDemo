package com.heqichao.springBootDemo.module.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heqichao.springBootDemo.base.param.ApplicationContextUtil;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import com.heqichao.springBootDemo.base.util.JsonUtil;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import com.heqichao.springBootDemo.module.entity.WarningLog;
import com.heqichao.springBootDemo.module.service.LightningLogService;
import com.heqichao.springBootDemo.module.service.WarningLogService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heqichao on 2018-7-9.
 */

/**
 * 发布消息的回调类
 *
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * 必须在回调类中实现三个方法：
 *
 *  public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 *
 *  public void connectionLost(Throwable cause)在断开连接时调用。
 *
 *  public void deliveryComplete(MqttDeliveryToken token))
 *  接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
 *  由 MqttClient.connect 激活此回调。
 *
 */
@Component
public class MqttUtilCallback implements MqttCallback {


    public static MqttUtilCallback mqttUtilCallback;

    public MqttUtilCallback(){}

    @PostConstruct
    public void init() {
        mqttUtilCallback = this;
        mqttUtilCallback.lightningLogService = this.lightningLogService;
        mqttUtilCallback.equipmentService = this.equipmentService;
    }

    @Autowired
    private LightningLogService lightningLogService;

    @Autowired
    private EquipmentService equipmentService;


    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        logger.error(" MQTT 连接异常断开，开始重连",throwable);
        MqttUtil.init();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
// subscribe后得到的消息会执行到这里面
        String devId =topic.replace("application/0000000000000001/node/","");
        devId =devId.replace("/rx","");
        Date date =new Date();
        String mes =new String(message.getPayload());
        logger.info("接收消息主题 : " + topic);
        logger.info("接收消息Qos : " + message.getQos());
        logger.info("接收消息内容 : " + mes);
        if(LightningLogService.OFF_LINE.equalsIgnoreCase(mes)) {
            logger.error( devId+":"+LightningLogService.OFF_LINE+" 设备下线！！！");
            LightningLog log =new LightningLog();
            log.setDevEUI(devId);
            log.setCreateTime(date);
            log.setUpdateTime(date);
            log.setStatus(LightningLogService.OFF_LINE);
            mqttUtilCallback.lightningLogService.save(log);

            //todo
            mqttUtilCallback.equipmentService.setEquStatus(devId,EquipmentService.BREAKDOWN);
        }else{
            LightningLog log =MqttUtil.saveTransData(mes);
            if(log!=null){
                log.setCreateTime(date);
                log.setUpdateTime(date);
                mqttUtilCallback.lightningLogService.save(log);
                WarningLogService warningLogService=ApplicationContextUtil.getBean(WarningLogService.class);
                logger.error( devId+":"+log.getStatus()+"  ！！！");
                if(LightningLogService.HEART_BEAT_ERROR.equals(log.getStatus())){
                    //设置设备故障
                    logger.error( devId+":"+LightningLogService.HEART_BEAT_ERROR+" 设备故障,但状态仍为在线！！！");
                    mqttUtilCallback.equipmentService.setEquStatus(devId,EquipmentService.BREAKDOWN);

                    //故障提醒
                    WarningLog warningLog=new WarningLog();
                    warningLog.setDevEUI(devId);
                    warningLog.setCreateTime(date);
                    warningLog.setUpdateTime(date);
                    warningLog.setStatus(WarningLogService.FAULT);
                    warningLog.setData(log.getData());
                  /*  warningLog.setDataLen(log.getDataLen());
                    warningLog.setDevicePath(log.getDevicePath());
                    warningLog.setfCnt(log.getfCnt());
                    warningLog.setfPort(log.getfPort());
                    warningLog.setFunctionCode(log.getFunctionCode());
                    warningLog.setGatewayCount(log.getGatewayCount());
                    warningLog.setLoRaSNR(log.getLoRaSNR());
                    warningLog.setRssi(log.getRssi());*/
                    warningLog.setTime(log.getTime());
                    warningLogService.save(warningLog);

                }else{
                    logger.error(devId+ " 设备正常！！！");
                    mqttUtilCallback.equipmentService.setEquStatus(devId,EquipmentService.NORMAL);

                    //更新故障信息 有心跳则为已修复
                    warningLogService.updateFix(devId);
                }
            }
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("deliveryComplete---------" + token.isComplete());
    }
}
