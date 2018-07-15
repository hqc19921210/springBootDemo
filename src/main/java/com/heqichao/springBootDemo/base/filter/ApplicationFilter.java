package com.heqichao.springBootDemo.base.filter;

import com.heqichao.springBootDemo.base.exception.ResponeException;
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
            RequestContext.setRequestContext(request,response);
            filterChain.doFilter(request, response);

        }catch (Exception e){
            throw new ResponeException(e);
        }


        RequestContext.removeRequestContext();
    }

    @Override
    public void destroy() {
        System.out.println(" AAAAAAAAAAAAAAAAAAAAAAA destroy");
    }


}