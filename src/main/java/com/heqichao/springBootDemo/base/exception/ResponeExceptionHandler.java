package com.heqichao.springBootDemo.base.exception;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by heqichao on 2018-2-20.
 */
//@RestControllerAdvice  则可以不用添加ResponseBody
@ControllerAdvice
public class ResponeExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());
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
    public ResponeResult errorHandler(Exception ex) {
        logger.error("System_Exception:",ex);
        return new ResponeResult(false,DEFAULT_ERROR_MSG);
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ResponeException.class)
    public ResponeResult myErrorHandler(ResponeException ex) {
        logger.error("System_ResponeException:",ex);
        ResponeResult responeResult= new ResponeResult(false,DEFAULT_ERROR_MSG);

        if(StringUtil.isNotEmpty((String) exceptionMap.getExceptionMap().get(ex.getMsg()))){
            responeResult.setMessage((String) exceptionMap.getExceptionMap().get(ex.getMsg()));
        }

        return responeResult;
    }
}
