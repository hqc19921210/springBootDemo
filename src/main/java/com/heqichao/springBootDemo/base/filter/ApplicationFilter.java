package com.heqichao.springBootDemo.base.fiter;

import com.heqichao.springBootDemo.base.param.RequestContext;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by heqichao on 2018-2-14.
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "ApplicationFilter")
public class ApplicationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置应用request、response
       HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
         request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        RequestContext.setRequestContext(request,response);

        filterChain.doFilter(request, response);

        RequestContext.removeRequestContext();
    }

    @Override
    public void destroy() {

    }
}
