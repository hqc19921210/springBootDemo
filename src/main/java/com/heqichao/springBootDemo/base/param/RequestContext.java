package com.heqichao.springBootDemo.base.param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-14.
 */
public class RequestContext {

    private static ThreadLocal<RequestContext> contextThreadLocal = new ThreadLocal<RequestContext>();

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private PageInfo pageInfo;
    private Map paramMap;

    public static void removeRequestContext(){
        contextThreadLocal.remove();
    }

    public static void setRequestContext(HttpServletRequest request,HttpServletResponse response){
        RequestContext requestContext = new RequestContext();
        requestContext.setRequest(request);
        requestContext.setResponse(response);
        if(request!=null){
            requestContext.setSession(request.getSession());
            requestContext.setParamMap(getMapByRequest(request));
        }
        contextThreadLocal.set(requestContext);
    }


    public static RequestContext getContext(){
        RequestContext requestContext = contextThreadLocal.get();
        if (requestContext == null) {
            requestContext = new RequestContext();
            contextThreadLocal.set(requestContext);
        }
        return contextThreadLocal.get();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Map getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map paramMap) {
        this.paramMap = paramMap;
    }

    public static HashMap getMapByRequest(HttpServletRequest request) {
        HashMap paramMap = new HashMap();
        if(request!=null){
            Enumeration enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                String[] paraValue = request.getParameterValues(paraName);
                if (paraValue.length > 1) {
                    paramMap.put(paraName, paraValue);
                } else {
                    paramMap.put(paraName, paraValue[0]);
                }
            }
        }
        return paramMap;
    }
}
