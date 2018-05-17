package com.carme.common.service.push.impl;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.push.SendPushBo;
import com.carme.common.service.push.PushService;
import com.carme.common.util.JsonUtil;
import com.carme.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by huangkl on 2018/5/2.
 */
@Service
public class PushServiceImpl implements PushService {

    private static Logger log = LoggerFactory.getLogger(PushServiceImpl.class);

    @Autowired
    @Qualifier("umsPushQueue")
    private Destination   umsPushQueue;

    @Autowired
    private JmsTemplate   jmsTemplate;

    public ResponseBo send(SendPushBo param) {
        ResponseBo<String> resp = new ResponseBo<>();
        //检查参数
        checkParam(param, resp);
        if (!resp.success()) {
            return resp;
        }
        resp.setResult("");
        try {
            final String message = JsonUtil.toJson(param);
            jmsTemplate.send(umsPushQueue, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(message);
                }
            });
        } catch (Exception e) {
            resp.setCode(ResponseBo.ERROR);
            resp.setMessage(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return resp;
    }

    private void checkParam(SendPushBo param, ResponseBo<String> resp) {
        String message = "";
        if (param == null) {
            message = "param object is null";
        } else if (StringUtil.isBlank(param.getTitle())) {
            message = "title is empty";
        } else if (StringUtil.isBlank(param.getDests())) {
            message = "dests is empty";
        }
        if (StringUtil.isNotBlank(message)) {
            resp.setCode(ResponseBo.ERROR);
            resp.setMessage(message);
        }

    }
}
