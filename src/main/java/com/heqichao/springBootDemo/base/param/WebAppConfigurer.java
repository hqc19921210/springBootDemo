package com.heqichao.springBootDemo.base.param;

import com.heqichao.springBootDemo.base.filter.ParamHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by heqichao on 2018-7-23.
 */
@Configuration
public class WebAppConfigurer  extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截

        //拦截后台请求 保存请求参数
        registry.addInterceptor(new ParamHandlerInterceptor()).addPathPatterns("/service/*");

        super.addInterceptors(registry);

    }
}
