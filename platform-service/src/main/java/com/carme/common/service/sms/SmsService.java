package com.carme.common.service.sms;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.sms.SendSmsBo;

/**
 * Created by huangkl on 2018/5/3.
 */
public interface SmsService {

    /**
     * 发送消息
     * @param param
     * @return
     */
    ResponseBo send(SendSmsBo param);
}
