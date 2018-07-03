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
            String page= (String) map.get("page");
            String size= (String) map.get("size");
            if( StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(size)){
                PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(size));
            }
        }catch (Exception e){

        }

    }
}
