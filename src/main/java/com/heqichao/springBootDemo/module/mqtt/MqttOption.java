package com.heqichao.springBootDemo.module.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * MQTT配置
 * Created by heqichao on 2018-7-15.
 */
@Component
@PropertySource("classpath:/property/service.properties")
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "mqtt.option")  //读入配置失败是抛异常
public class MqttOption {
    private String userName;
    private String password;
    private String hostPort;
    //private String topic;
    private String clientId;

    private int retryTime=10; //连接重试次数 默认10
    private int retrySpace= 30;//连接重试间隔（默认30s）

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

   /* public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }*/

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public int getRetrySpace() {
        return retrySpace;
    }

    public void setRetrySpace(int retrySpace) {
        this.retrySpace = retrySpace;
    }

    @Override
    public String toString() {
        return "MqttOption{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", hostPort='" + hostPort + '\'' +
                ", clientId='" + clientId + '\'' +
                ", retryTime=" + retryTime +
                ", retrySpace=" + retrySpace +
                '}';
    }
}
