package com.carme.common.bo.sms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangkl on 2018/5/3.
 */
public class SendSmsBo {
    private String              dests;

    private String              tplNo;

    private String              thirdTplNo;

    private Map<String, String> tplParamMap;

    public void addTplParam(String key, String value) {
        if (tplParamMap == null) {
            tplParamMap = new HashMap<>();
        }
        tplParamMap.put(key, value);
    }

    public Map<String, String> getTplParamMap() {
        if (tplParamMap == null) {
            tplParamMap = new HashMap<>();
        }
        return tplParamMap;
    }

    public String getDests() {
        return dests;
    }

    public void setDests(String dests) {
        this.dests = dests;
    }

    public String getTplNo() {
        return tplNo;
    }

    public void setTplNo(String tplNo) {
        this.tplNo = tplNo;
    }

    public String getThirdTplNo() {
        return thirdTplNo;
    }

    public void setThirdTplNo(String thirdTplNo) {
        this.thirdTplNo = thirdTplNo;
    }
}
