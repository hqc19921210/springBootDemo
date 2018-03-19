package com.heqichao.springBootDemo.base.util;

import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by heqichao on 2018-2-14.
 */
public class ServletUtil {

    public static void writeToResponse(HttpServletResponse response, ResponeResult result) {
        String jsonStr = JsonUtil.getJsonString(result);
        HttpServletRequest request =RequestContext.getContext().getRequest();

        writeToResponse(response, jsonStr);
    }

    public static void writeToResponse(HttpServletResponse response, String responseStr) {
        PrintWriter out;
        try {
          //  String ip=DataUtil.getLocalIP().substring(6);
         //   response.addHeader("serverh", ip);
            out = response.getWriter();
            out.write(responseStr);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
           // throw new BaseRuningTimeException("servlet error");
        }
    }

}
