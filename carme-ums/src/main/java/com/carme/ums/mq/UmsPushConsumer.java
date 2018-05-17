package com.carme.ums.mq;

import com.carme.common.bo.push.SendPushBo;
import com.carme.common.util.DateUtil;
import com.carme.common.util.JsonUtil;
import com.carme.ums.core.push.PushApi;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Date;

public class UmsPushConsumer implements MessageListener {

    private static Logger      log     = LoggerFactory.getLogger(UmsPushConsumer.class);

    private static RateLimiter limiter = RateLimiter.create(5);

    @Autowired
    private PushApi            pushApi;

    @Override
    public void onMessage(Message message) {
        String json = null;
        try {
            TextMessage tm = (TextMessage) message;
            json = tm.getText();
            if (StringUtils.isNotBlank(json)) {
                tryPushAcquire(1);
                log.debug("push message start param=" + json);
                SendPushBo param = JsonUtil.fromJSON(json, SendPushBo.class);
                pushApi.send(param);
            }
        } catch (Exception e) {
            log.error("push message error param = " + json + e.getMessage(), e);
        }
    }

    /**
     * 限制每秒推送限制
     */
    private void tryPushAcquire(int executeNum) {
        if (limiter.tryAcquire()) {
            log.debug(DateUtil.datetime(new Date(), "yyyy-MM-dd HH:mm:ss")
                      + " try push limit success");
            return;
        } else {
            if (executeNum == 1) {
                log.debug(DateUtil.datetime(new Date(), "yyyy-MM-dd HH:mm:ss")
                          + " try push limit error");
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            executeNum++;
            tryPushAcquire(executeNum);
        }

    }
}