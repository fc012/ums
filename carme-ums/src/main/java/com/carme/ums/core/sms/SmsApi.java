package com.carme.ums.core.sms;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.sms.SendSmsBo;

/**
 * Created by huangkl on 2018/5/3.
 */
public interface SmsApi {

    /**
     * 发送短信消息
     * @param param
     * @return
     */
    ResponseBo<Object> send(SendSmsBo param);

}
