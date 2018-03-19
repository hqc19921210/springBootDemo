package com.heqichao.springBootDemo.base.service;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface SendService {

    void sendSms(String sendUserId,String formUserId ,String context,boolean useQueue);
}
