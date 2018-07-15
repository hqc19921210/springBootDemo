package com.heqichao.springBootDemo.base.util;

import java.io.IOException;

/**
 * Created by heqichao on 2018-7-14.
 */
public class Base64Encrypt {
    /**
     * 编码
     *
     * @param bstr
     * @return String
     */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt;
    }

    public static void main(String[] args) {
        String base64String = "whuang123";
        Base64Encrypt base64=new Base64Encrypt();
        String str1=base64.encode(base64String.getBytes());
        System.out.println("str1:"+str1);
        String str2=new String(base64.decode(str1));
        System.out.println("str2:"+str2);
    }
}
