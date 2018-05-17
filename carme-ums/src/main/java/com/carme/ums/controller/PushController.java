package com.carme.ums.controller;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.push.SendPushBo;
import com.carme.common.service.push.PushService;
import com.carme.common.util.JsonUtil;
import com.carme.common.util.StringUtil;
import com.carme.ums.vo.push.SendPushVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * UMS系统的主控制类
 *
 * @author carme
 *
 */
@Controller
@RequestMapping("/push")
public class PushController {

    private static Logger logger = LoggerFactory.getLogger(PushController.class);

    @Autowired
    private PushService   pushService;

    /**
     * 推送接口
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/send")
    @ResponseBody
    public ResponseBo pushMessage(SendPushVo param) {
        ResponseBo resp = pushService.send(convert(param));
        return resp;
    }

    private SendPushBo convert(SendPushVo param) {
        SendPushBo result = new SendPushBo();
        result.setTitle(param.getTitle());
        result.setDests(param.getDests());
        if (StringUtil.isNotBlank(param.getVars())) {
            HashMap<String, String> varsMap = JsonUtil.fromJSON(param.getVars(), HashMap.class);
            for (String key : varsMap.keySet()) {
                result.add(key, varsMap.get(key));
            }
        }
        return result;
    }
}
