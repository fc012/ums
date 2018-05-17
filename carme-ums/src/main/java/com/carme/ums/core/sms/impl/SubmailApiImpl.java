package com.carme.ums.core.sms.impl;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.sms.SendSmsBo;
import com.carme.common.util.JsonUtil;
import com.carme.ums.core.sms.SmsApi;
import com.submail.lib.MESSAGEXsend;
import com.submail.utils.AppConfig;
import com.submail.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangkl on 2018/5/3.
 */
public class SubmailApiImpl implements SmsApi {

    private static Logger log = LoggerFactory.getLogger(SubmailApiImpl.class);

    private MESSAGEXsend  submail;

    public void init(String appId, String appKey, String signType) {
        AppConfig config = ConfigLoader.createConfig(appId, appKey, signType);
        submail = new MESSAGEXsend(config);
    }

    @Override
    public ResponseBo<Object> send(SendSmsBo param) {
        ResponseBo resp = new ResponseBo();
        resp.setCode(ResponseBo.ERROR);
        try {
            String[] destsArr = param.getDests().split(",");
            for (String dest : destsArr) {
                submail.addTo(dest);
            }
            submail.setProject(param.getThirdTplNo());
            Map<String, String> varsMap = param.getTplParamMap();
            for (String key : param.getTplParamMap().keySet()) {
                submail.addVar(key, varsMap.get(key));
            }
            String result = submail.xsend();

            if (result.startsWith("{")) {
                HashMap resultMap = JsonUtil.fromJSON(result, HashMap.class);
                if (resultMap.get("status") != null
                    && "success".equals(resultMap.get("status").toString())) {
                    resp.setCode(ResponseBo.SUCCESS);
                }
            }
            resp.setMessage(result);
            if (!resp.success()) {
                log.warn("send sms error param={} result={}", JsonUtil.toJson(param), result);
            }
        } catch (Exception e) {
            resp.setMessage("推送失败:" + e.getMessage());
            log.error(e.getMessage(), e);
        }
        return resp;
    }
}
