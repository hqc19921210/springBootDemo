package com.heqichao.springBootDemo.base.util;

import com.alibaba.fastjson.JSON;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-14.
 */
public class JsonUtil {

    /**
     * 对象转json串
     * @param obj 待序列化对象
     * @return
     */
    public static String getJsonString(Object obj){
        if(obj==null){
            return "";
        }
        String objJson = JSON.toJSONString(obj);
     return objJson;
    }

}
