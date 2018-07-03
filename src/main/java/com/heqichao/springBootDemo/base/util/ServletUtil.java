package com.heqichao.springBootDemo.base.util;

import com.heqichao.springBootDemo.base.entity.UserInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-14.
 */
public class ServletUtil {
    public static String SESSION_USER="SESSION_USER";
    public static ResponeResult NO_LOGIN_RESULT=new ResponeResult(false,"isNotLogin");



    public static UserInfo getSessionUser(){
        HttpSession session = RequestContext.getContext().getSession();
        if(session == null || session.getAttribute(SESSION_USER) == null){
            return null;
        }else{
            return (UserInfo) session.getAttribute(SESSION_USER);
        }
    }

    public static void setSessionUser( UserInfo userInfo){
        HttpSession session = RequestContext.getContext().getSession();
        if(session != null){
            session.setAttribute(SESSION_USER,userInfo);
        }
    }

/*    public static boolean   checkLoginForHtml(HttpServletRequest request ,  HttpServletResponse response ) throws IOException {
        String uri = request.getServletPath();
        HttpSession session = RequestContext.getContext().getSession();
        if( "/".equals(uri) || "/index".equals(uri) || "/index.html".equals(uri)){
            if(getSessionUser() == null){
                return false;
            }
        }
        return true;
    }*/

    public static void writeToResponse(HttpServletResponse response, ResponeResult result) {
        String jsonStr = JsonUtil.getJsonString(result);
        HttpServletRequest request =RequestContext.getContext().getRequest();

        writeToResponse(response, jsonStr);
    }

    public static void writeToResponse(HttpServletResponse response, String responseStr) {
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write(responseStr);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
