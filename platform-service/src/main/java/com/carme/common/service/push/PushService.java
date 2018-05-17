package com.carme.common.service.push;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.push.SendPushBo;

/**
 * Created by huangkl on 2018/5/2.
 */
public interface PushService {

    /**
     * 消息推送
     * @param param
     * @return
     */
    ResponseBo send(SendPushBo param);
}
