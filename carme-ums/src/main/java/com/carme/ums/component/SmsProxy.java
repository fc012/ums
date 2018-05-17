package com.carme.ums.component;

import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.sms.SendSmsBo;
import com.carme.common.constants.SmsConstants;
import com.carme.common.util.JsonUtil;
import com.carme.ums.core.sms.SmsApi;
import com.carme.ums.core.sms.entity.SubmailConfig;
import com.carme.ums.core.sms.impl.SubmailApiImpl;
import com.carme.ums.po.SmsConfigPo;
import com.carme.ums.po.SmsTemplatePo;
import com.carme.ums.service.SmsConfigService;
import com.carme.ums.service.SmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangkl on 2018/5/3.
 */
@Component
public class SmsProxy {

    private Map<String, SmsApi> smsMap = new HashMap<>();

    @Autowired
    private SmsConfigService    smsConfigService;

    @Autowired
    private SmsTemplateService  smsTemplateService;

    public ResponseBo<Object> send(SendSmsBo param) {
        SmsTemplatePo template = smsTemplateService.getTemplateNoByCache(param.getTplNo());
        if (template == null) {
            throw new RuntimeException("cant't find template by tplNo=" + param.getTplNo());
        }
        param.setThirdTplNo(template.getThirdTemplateNo());
        String source = template.getSource();
        SmsApi smsApi = getInstance(source);
        return smsApi.send(param);
    }

    /**
     * 获得sms实例
     * @param source
     * @return
     */
    private SmsApi getInstance(String source) {
        SmsApi api = smsMap.get(source);
        if (api == null) {
            api = createProxy(source);
            smsMap.put(source, api);
        }

        return api;
    }

    /**
     * sms实现代理
     * @param source
     * @return
     */
    private SmsApi createProxy(String source) {
        //获得来源配置
        SmsConfigPo config = getConfigBySource(source);
        //平台
        String platform = config.getPlatform();
        //submail实现
        if (SmsConstants.PLATFORM_SUBMAIL.equals(platform)) {
            //获得submail平台配置
            SubmailConfig platConfig = JsonUtil.fromJSON(config.getConfig(), SubmailConfig.class);
            SubmailApiImpl api = new SubmailApiImpl();
            api.init(platConfig.getAppId(), platConfig.getAppKey(), platConfig.getSignType());
            return api;
        } else {
            throw new RuntimeException("not support service for platform=" + platform);
        }

    }

    private SmsConfigPo getConfigBySource(String source) {
        SmsConfigPo query = new SmsConfigPo();
        query.setSource(source);
        List<SmsConfigPo> configList = smsConfigService.query(query);
        if (configList == null || configList.size() == 0) {
            throw new RuntimeException("can't find config for souce=" + source);
        }
        return configList.get(0);
    }
}
