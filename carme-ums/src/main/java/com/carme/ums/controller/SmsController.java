package com.carme.ums.controller;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.sms.SendSmsBo;
import com.carme.common.service.sms.SmsService;
import com.carme.common.util.JsonUtil;
import com.carme.common.util.StringUtil;
import com.carme.ums.vo.sms.SendSmsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by huangkl on 2018/4/23.
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

    private static Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService    smsService;

    /**
     * 推送接口
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/send")
    @ResponseBody
    public ResponseBo pushMessage(SendSmsVo param) {
        ResponseBo resp = smsService.send(convert(param));
        return resp;
    }

    private SendSmsBo convert(SendSmsVo param) {
        SendSmsBo result = new SendSmsBo();
        result.setDests(param.getDests());
        result.setTplNo(param.getTplNo());
        if (StringUtil.isNotBlank(param.getTplParams())) {
            HashMap<String, String> varsMap = JsonUtil
                .fromJSON(param.getTplParams(), HashMap.class);
            for (String key : varsMap.keySet()) {
                result.addTplParam(key, varsMap.get(key));
            }
        }

        return result;
    }

}
