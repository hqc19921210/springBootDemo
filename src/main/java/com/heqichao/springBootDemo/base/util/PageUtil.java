package com.heqichao.springBootDemo.base.util;

import com.github.pagehelper.PageHelper;
import com.heqichao.springBootDemo.base.param.RequestContext;

import java.util.Map;

/**
 * Created by heqichao on 2018-7-1.
 */
public class PageUtil {
    public static void setPage(){
        Map map =RequestContext.getContext().getParamMap();
        try{
            Integer page= (Integer) map.get("page");
            Integer size= (Integer) map.get("size");
            if(page!= null  && size!=null){
                PageHelper.startPage(page, size);
            }
        }catch (Exception e){
            //System.out.println(e);
        }

    }
}
