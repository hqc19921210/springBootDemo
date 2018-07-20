package com.heqichao.springBootDemo.base.util;

import org.springframework.util.DigestUtils;

/**
 * Created by heqichao on 2018-7-14.
 */
public class Md5Util {

    public static String encord(String src){
       return  DigestUtils.md5DigestAsHex(src.getBytes());
    }
    
    public static void main(String[] args) {
    	System.out.println(encord("admin"));
    	encord("admin");
	}
}
