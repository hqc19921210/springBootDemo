package com.heqichao.springBootDemo.base.exception;

import com.heqichao.springBootDemo.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-20.
 */
//@RestControllerAdvice  则可以不用添加ResponseBody
@ControllerAdvice
public class ResponeExceptionHandler {

    private String DEFAULT_ERROR_MSG ="系统错误";
    @Autowired
    private ExceptionMap exceptionMap;
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", DEFAULT_ERROR_MSG);
        return map;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ResponeException.class)
    public Map myErrorHandler(ResponeException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        if(StringUtil.isNotEmpty((String) exceptionMap.getExceptionMap().get(ex.getMsg()))){
            map.put("msg", exceptionMap.getExceptionMap().get(ex.getMsg()));
        }else{
            map.put("msg", DEFAULT_ERROR_MSG);
        }

        return map;
    }
}
