package com.heqichao.springBootDemo.base.filter;

import com.heqichao.springBootDemo.base.exception.ResponeException;
import com.heqichao.springBootDemo.base.param.RequestContext;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-14.
 */
@Component

//@Order(value = 2)
@WebFilter(urlPatterns = "/service/*",filterName = "SessionFilter")
public class SessionFilter implements Filter {
    private static Map noLoginUrl=new HashMap();
    static {
        noLoginUrl.put("/service/login",new Object());
        noLoginUrl.put("/service/updatePassword",new Object());
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        //设置应用request、response
       HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            String uri = request.getServletPath();
            HttpSession session = RequestContext.getContext().getSession();
            if(noLoginUrl.get(uri) == null){
                if(session== null || session.getAttribute("USER") == null){
                      response.sendRedirect("login.html");
                       return;
                }
            }

            filterChain.doFilter(request, response);
        }catch (Exception e){
            throw new ResponeException(e);
        }



    }

    @Override
    public void destroy() {

    }

}
