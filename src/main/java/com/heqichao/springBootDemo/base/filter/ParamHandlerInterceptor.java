package com.heqichao.springBootDemo.base.filter;

import com.heqichao.springBootDemo.base.param.RequestContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by heqichao on 2018-7-23.
 */
public class ParamHandlerInterceptor  implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
        RequestContext.removeRequestContext();
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        // 在请求处理之前进行调用（Controller方法调用之前）
        try{
            response.setHeader("Content-type", "application/json;charset=UTF-8");

            String requestMethord = request.getRequestURI();//请求方法
            if(requestMethord==null){
                return false;
            }


            RequestContext.getContext().setRequestMap(request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
