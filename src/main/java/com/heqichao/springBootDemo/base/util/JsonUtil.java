package com.heqichao.springBootDemo.base.util;

import com.alibaba.fastjson.JSON;

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

    public static  <T> T getObjByJsonString(String jsonString,Class<T> tClass){
      return  JSON.parseObject(jsonString,tClass);
    }

}
