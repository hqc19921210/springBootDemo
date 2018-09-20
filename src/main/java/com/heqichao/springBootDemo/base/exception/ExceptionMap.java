package com.heqichao.springBootDemo.base.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by heqichao on 2018-2-20.
 */
@Component
@PropertySource("classpath:/property/error.properties")
//@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sms.config")  //读入配置失败是抛异常
public class ExceptionMap {

    @Value("#{${exceptionMap}}")
    private Map exceptionMap;

    public Map getExceptionMap() {
        return exceptionMap;
    }

    public void setExceptionMap(Map exceptionMap) {
        this.exceptionMap = exceptionMap;
    }
}
