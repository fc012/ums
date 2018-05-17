package com.carme.ums.core.push;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.push.SendPushBo;

/**
 * Created by huangkl on 2018/5/2.
 */
public interface PushApi {

    /**
     * 推送消息
     * @param param
     * @return
     */
    ResponseBo<Object> send(SendPushBo param);
}
