package com.heqichao.springBootDemo.base.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by heqichao on 2018-2-14.
 */
@Aspect//切面标识
@Component //将该类交给spring容器管理
public class RequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    /**
     * 选取切入点为自定义注解
     */
    @Around("execution(* com.heqichao.springBootDemo.*.action.*Controller.*(..))")
    public Object  requestFilt(ProceedingJoinPoint pjp) throws Throwable{

        Object obj= pjp.proceed();
        return obj;
    }

}
