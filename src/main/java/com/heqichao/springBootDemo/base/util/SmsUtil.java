package com.heqichao.springBootDemo.base.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 注入配置文件信息
 * Created by heqichao on 2018-2-12.
 */
/*@Configuration*/
@Component
@PropertySource("classpath:/property/service.properties")
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sms.config")  //读入配置失败是抛异常
public class SmsUtil {
    private String userNo;
    private String password;
    private String apiUrl;
    private String apiService;

    public String getUserNo() {
        return userNo;
    }

    public String getPassword() {
        return password;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiService() {
        return apiService;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setApiService(String apiService) {
        this.apiService = apiService;
    }
}
