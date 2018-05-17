package com.carme.ums.mq;

import com.carme.common.bo.sms.SendSmsBo;
import com.carme.common.util.JsonUtil;
import com.carme.ums.component.SmsProxy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class UmsSmsConsumer implements MessageListener {

    private static Logger log = LoggerFactory.getLogger(UmsSmsConsumer.class);

    @Autowired
    private SmsProxy smsProxy;

    @Override
    public void onMessage(Message message) {
        String json = null;
        try {
            TextMessage tm = (TextMessage) message;
            json = tm.getText();
            if (StringUtils.isNotBlank(json)) {
                log.debug("send sms start param=" + json);
                SendSmsBo param = JsonUtil.fromJSON(json, SendSmsBo.class);
                smsProxy.send(param);
            }
        } catch (Exception e) {
            log.error("send sms start error param = " + json + e.getMessage(), e);
        }

    }

}