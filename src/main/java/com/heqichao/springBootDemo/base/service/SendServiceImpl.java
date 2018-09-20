package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by heqichao on 2018-2-12.
 */
@Service
//@EnableConfigurationProperties(SmsUtil.class)
public class SendServiceImpl implements SendService {

    @Autowired
    private SmsUtil smsUtil;

    //如果配置在类上 则整个类的方法均有事务
    @Transactional
    @Override
    public void sendSms(String sendUserId, String formUserId, String context, boolean useQueue) {
        System.out.println(smsUtil.getUserNo()+":"+smsUtil.getPassword());
    }
}
