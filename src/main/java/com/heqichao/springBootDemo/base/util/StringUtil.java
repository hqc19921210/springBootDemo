package com.heqichao.springBootDemo.base.util;

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
}
