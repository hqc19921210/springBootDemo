package com.heqichao.springBootDemo.base.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heqichao.springBootDemo.base.exception.ResponeException;
import com.heqichao.springBootDemo.base.param.BodyReaderHttpServletRequestWrapper;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.util.PropertiesConfig;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by heqichao on 2018-2-14.
 */
//@Component

//@Order(value = 1)
@WebFilter(urlPatterns = "/*",filterName = "ApplicationFilter")
public class ApplicationFilter implements Filter {
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        //设置应用request、response
       HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            RequestContext.setRequestContext(request,response);

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");


            // 防止流读取一次后就没有了, 所以需要将流继续写出去
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
            filterChain.doFilter(requestWrapper, servletResponse);
           // filterChain.doFilter(request, response);

        }catch (Exception e){
            throw new ResponeException(e);
        }


        RequestContext.removeRequestContext();
    }

    @Override
    public void destroy() {
        System.out.println(" application destroy !");
    }


}
