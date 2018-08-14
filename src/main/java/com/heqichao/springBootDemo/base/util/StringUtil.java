package com.heqichao.springBootDemo.base.util;

import java.util.Map;

/**
 * Created by heqichao on 2018-2-12.
 */
public class StringUtil {

    public static boolean isEmpty(String context){
        if(context==null || "".equals(context)){
            return true;
        }
        return  false;
    }

    public static boolean isNotEmpty(String context){
        return !isEmpty(context);
    }
    
    // Add by Muzzy Xu.
    public static Integer objectToInteger(Object obj) {
    	if(obj == null) {
    		return null;
    	}
    	try {
    		return Integer.valueOf(obj.toString());
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
    }
    
    public static String getStringByMap(Map map,String key) {
    	if(map.size() == 0) {
    		return null;
    	}
    	try {
    		return map.get(key).toString();
    	} catch (Exception e) {
//    		e.printStackTrace();
    		return null;
    	}
    }
}
