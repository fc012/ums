package com.carme.ums.core.push.impl;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import com.carme.common.bo.ResponseBo;
import com.carme.common.bo.push.SendPushBo;
import com.carme.ums.core.push.PushApi;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JPushApiImpl implements PushApi {

    private static Logger log         = LoggerFactory.getLogger(JPushApiImpl.class);

    private String        appKey;

    private String        secret;

    JPushClient           jpushClient = null;

    public void init() {
        jpushClient = new JPushClient(secret, appKey, null, ClientConfig.getInstance());
    }

    /**
     * 封装推送
     * @param msg
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public ResponseBo<Object> send(SendPushBo param) {
        PushPayload payload = createPayLoad(param);
        ResponseBo resp = new ResponseBo();
        try {
            PushResult result = jpushClient.sendPush(payload);
            log.debug("get result  : {}", result);
            resp.setMessage("发送成功");

        } catch (Exception e) {
            resp.setCode(ResponseBo.ERROR);
            resp.setMessage("推送失败:" + e.getMessage());
            log.error(e.getMessage(), e);
        }
        return resp;
    }

    public PushPayload createPayLoad(SendPushBo param) {
        Audience audience = null;
        if (param.getDests().equals("all")) {
            audience = Audience.all();
        } else {
            String[] destArr = param.getDests().split(",");
            audience = Audience.newBuilder()
                .addAudienceTarget(AudienceTarget.alias(param.getDests())).build();
        }

        PlatformNotification androidNotification = AndroidNotification.newBuilder()
            .setAlert(param.getTitle()).addExtras(param.getVarsMap()).build();

        PlatformNotification iosNotification = IosNotification.newBuilder()
            .setAlert(param.getTitle()).addExtras(param.getVarsMap()).build();

        return PushPayload
            .newBuilder()
            .setPlatform(Platform.all())
            .setAudience(audience)
            .setNotification(
                Notification.newBuilder().setAlert(param.getTitle())
                    .addPlatformNotification(androidNotification)
                    .addPlatformNotification(iosNotification).build()).build();

    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
