package com.heqichao.springBootDemo.module.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by heqichao on 2018-7-15.
 */
@Component
public class MqttUtil {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MqttOption mqttOption;

    private static MqttClient client;
    public MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();

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

        MqttTopic topic = client.getTopic(mqttOption.getTopic());
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        //options.setWill(topic, "CLOSE".getBytes(), 1, true);

        return options;
    }
    private void connect() throws MqttException {
        //防止重复创建MQTTClient实例
        if (client==null) {
            client = new MqttClient(mqttOption.getHostPort(), mqttOption.getClientId(), new MemoryPersistence());
            client.setCallback(new MqttUtilCallback(this));

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

    private void getTopicMes() throws MqttException {
        logger.info("MQTT订阅主题:"+mqttOption.getTopic());
        //订阅消息
        int[] Qos  = {1};
        String[] topic1 = {mqttOption.getTopic()};
        client.subscribe(topic1, Qos);
    }

    private void connect(int number,int waitSecond) {
        for (int i = 0; i < number||number>999; i++) {
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
    public void init() {
        try {
            logger.info("**** INIT MQTT START : "+mqttOption);
            connect(mqttOption.getRetryTime(),mqttOption.getRetrySpace());
            getTopicMes();
            logger.info("**** INIT MQTT SUCCESS! ");
        } catch (MqttException e) {
            logger.error("**** INIT MQTT FAIL! ",e);
        }
    }

}
