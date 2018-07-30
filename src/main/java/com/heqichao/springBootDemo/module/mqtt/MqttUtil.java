package com.heqichao.springBootDemo.module.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heqichao.springBootDemo.base.param.ApplicationContextUtil;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import com.heqichao.springBootDemo.base.util.Base64Encrypt;
import com.heqichao.springBootDemo.base.util.DataUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import com.heqichao.springBootDemo.module.service.LightningLogService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by heqichao on 2018-7-15.
 */
@Component
public class MqttUtil {
    static Logger logger = LoggerFactory.getLogger(MqttUtil.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

    @Resource
    private MqttOption mqttOptionSource;

    private static MqttOption  mqttOption;

    public static MqttClient getClient(){
        return client;
    }

    @PostConstruct
    public void initOption() {
        this.mqttOption = mqttOptionSource;
    }

    private static MqttClient client;
    private static MqttConnectOptions options = new MqttConnectOptions();

    public static MqttConnectOptions getOptions() {


        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(mqttOption.getUserName());
        // 设置连接的密码
        options.setPassword(mqttOption.getPassword().toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        // 设置回调

        return options;
    }
    private static void connect() throws MqttException {
        //防止重复创建MQTTClient实例
        if (client==null) {
            client = new MqttClient(mqttOption.getHostPort(), mqttOption.getClientId(), new MemoryPersistence());
            client.setCallback(new MqttUtilCallback());

        }
        MqttConnectOptions options = getOptions();
        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
        if (!client.isConnected()) {
            client.connect(options);
            logger.info("MQTT连接成功");
        }else {//这里的逻辑是如果连接成功就重新连接
            client.disconnect();
            client.connect(options);
            logger.info("MQTT连接成功");
        }
    }

    public static void subscribeTopicMes(List<String> topsics) throws Exception {
        if(topsics== null || topsics.size()<1){
            logger.info("MQTT 无订阅主题！！！");
            return;
        }
        String[] topisArr = new String[topsics.size()];

        for(int i=0 ;i< topsics.size();i++){
            String s =topsics.get(i);
            s="application/0000000000000001/node/"+s+"/rx";
            logger.info("MQTT订阅主题:"+s);
            topisArr[i]=s;

            MqttTopic topic = client.getTopic(s);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(topic, LightningLogService.OFF_LINE.getBytes(), 1, true);
        }
        //订阅消息
        int[] qos  =new int[topisArr.length];
        for(int i=0;i<topisArr.length;i++){
            qos[i]=2;
        }
        client.subscribe(topisArr,qos);
        //更改option后要重连
        connect();

    }

    private static void connect(int number,int waitSecond) {
        for (int i = 1; i <= number||number>999; i++) {
            try {
                connect();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(waitSecond *1000);
                    logger.info("连接失败,正在第"+i+"次尝试");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }finally {
                    continue;
                }

            }
            return;
        }
        throw new RuntimeException("重试次数已达限制，无法连接MQTT服务器 : "+mqttOption);
    }

    //监听设备发来的消息
    public static void init() {
        try {
            String clentId =mqttOption.getClientId();
            mqttOption.setClientId(DataUtil.getLocalIP()+"_"+clentId);
            logger.info("**** INIT MQTT START : "+mqttOption);
            connect(mqttOption.getRetryTime(),mqttOption.getRetrySpace());
            EquipmentService equipmentService= (EquipmentService) ApplicationContextUtil.getApplicationContext().getBean("equipmentServiceImpl");


            subscribeTopicMes(equipmentService.getEquipmentIdListAll());
            logger.info("**** INIT MQTT SUCCESS! ");
        } catch (Exception e) {
            logger.error("**** INIT MQTT FAIL! ",e);

        }
    }


    public static LightningLog saveTransData(String mes){
        int defaule=100;
        LightningLog log =new LightningLog();
        JSONObject jsonObject = JSON.parseObject(mes);
        String devEUI=jsonObject.getString("devEUI"); //设备id
        log.setDevEUI(devEUI);
        String data =jsonObject.getString("data");
        log.setData(data);
        String time =jsonObject.getString("time");
        try {
            log.setTime(sdf.parse(time));
        } catch (Exception e) {}
        //不一定有用
        try {
            log.setfPort(jsonObject.getInteger("fPort"));
        } catch (Exception e) {}
        try {
            log.setGatewayCount(jsonObject.getInteger("gatewayCount"));
        } catch (Exception e) {}
        try {
            log.setGatewayCount(jsonObject.getInteger("gatewayCount"));
        } catch (Exception e) {}
        try {
            log.setfCnt(jsonObject.getInteger("fCnt"));
        }catch (Exception e1){}
        try {
            log.setGatewayCount(jsonObject.getInteger("gatewayCount"));
        }catch (Exception e1){}
        try {
            log.setRssi(jsonObject.getInteger("rssi"));
        }catch (Exception e1){}
        try {
            log.setLoRaSNR(jsonObject.getFloat("loRaSNR"));
        }catch (Exception e1){}

        try {
            if(StringUtil.isNotEmpty(data)){
                String[] transDatas =Base64Encrypt.decodeToHexStr(data);
                if(transDatas.length == 25){
                    //正常雷击数据
                    log.setDevicePath(transDatas[0]);  //设备地址
                    log.setFunctionCode(transDatas[1]);//功能码
                    log.setDataLen(transDatas[2]);//数据区长度
                    log.setLigntningCount(Integer.parseInt(transDatas[3]+transDatas[4],16));//雷击次数
                    String ligntningTime="20"+transDatas[5]+"-"+transDatas[6]+"-"+transDatas[7]+" "+transDatas[8]+":"+transDatas[9]+":"+transDatas[10];
                    log.setLigntningTime(ligntningTime); //雷击时间
                    log.setPeakValue(Long.parseLong(transDatas[11]+transDatas[12],16)/1000*defaule +"KA");//电流峰值
                    log.setEffectiveValue(Long.parseLong(transDatas[13]+transDatas[14],16)/1000*defaule +"KA"); //电流有效值
                    log.setWaveHeadTime(Long.parseLong(transDatas[15]+transDatas[16],16)/10+".0uS");//电流波头时间
                    log.setHalfPeakTime(Long.parseLong(transDatas[17]+transDatas[18],16)/10+".0uS"); //电流半峰值时间
                    log.setActionTime(Long.parseLong(transDatas[19]+transDatas[20],16)/10+".0uS");//电流作用时间
                    log.setEnergy(Long.parseLong(transDatas[21]+transDatas[22],16)/1000*defaule+"KA.uS"); //能量
                    //后两位作校验
                    log.setStatus(LightningLogService.LIGNHTNING_LOG);
                }else if(transDatas.length == 11){
                    log.setDevicePath(transDatas[0]);  //设备地址
                    log.setFunctionCode(transDatas[1]);//功能码
                    log.setDataLen(transDatas[2]);//数据区长度
                    String status =transDatas[3]+transDatas[4];
                    //后两位作校验
                    log.setStatus(status);

                }else{
                    logger.error(" ******** 无法解析接收数据 ："+mes+" 正文数据长度为："+transDatas.length);
                }

                return log;
            }

        } catch (Exception e) {
            logger.error(" ******** TRANS LIGHTNING LOG ERRPR ! ",e);
        }

        return null;

    }
}
