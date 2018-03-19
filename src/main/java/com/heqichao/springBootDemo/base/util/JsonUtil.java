package com.heqichao.springBootDemo.base.util;

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
        StringWriter writer = new StringWriter();
     /*   if(obj instanceof BaseParam){//BaseEntity实体对象为大写时的转换
            Map objMap=new HashMap();
            BeanUtil.copyProperties(objMap, obj);
            obj=objMap;
        }else if(obj instanceof List) {
            List list = new ArrayList();
            List objList = (List) obj;
            for (int i = 0; i < objList.size(); i++) {
                Object object = objList.get(i);
                if(object instanceof BaseParam){//BaseEntity实体对象为大写时的转换
                    Map objMap=new HashMap();
                    BeanUtil.copyProperties(objMap, object);
                    list.add(objMap);
                }else {
                    list.add(object);
                }
            }
            obj = list;
        }
        if(obj != null){
            try {
                mapper.writeValue(writer, obj);
            } catch (Exception e) {
                e.printStackTrace();
                //throw new BaseRuningTimeException("json error");
            }
        }
        return writer.toString();*/
     return null;
    }

}
