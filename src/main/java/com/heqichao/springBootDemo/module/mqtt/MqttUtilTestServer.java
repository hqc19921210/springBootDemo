package com.heqichao.springBootDemo.module.mqtt;

/**
 * Created by heqichao on 2018-7-9.
 */

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * Title:Server
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @author chenrl
 * 2016年1月6日下午3:29:28
 */
public class MqttUtilTestServer {



    public static final String HOST = "tcp://127.0.0.1:61613";
    public static final String TOPIC = "toptic";
    private static final String clientid = "clientid";

    private MqttClient client;
    private MqttTopic topic;
    private String userName = "admin";
    private String passWord = "password";

    private MqttMessage message;

    public MqttUtilTestServer() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new MqttUtilTestCallback());
            client.connect(options);
            topic = client.getTopic(TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }

    public static void main(String[] args) throws MqttException {
        MqttUtilTestServer server = new MqttUtilTestServer();

        server.message = new MqttMessage();
        server.message.setQos(1);
        server.message.setRetained(true);
        server.message.setPayload("发送测试信息".getBytes());
        server.publish(server.topic , server.message);



        System.out.println(server.message.isRetained() + "------ratained状态");
    }
}
