package com.carme.ums.core.sms.entity;

/**
 * Created by huangkl on 2018/5/3.
 */
public class SubmailConfig {
    private String appId;

    private String appKey;

    private String signType;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
