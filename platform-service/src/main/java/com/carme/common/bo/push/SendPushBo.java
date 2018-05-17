package com.carme.common.bo.push;

import java.util.HashMap;
import java.util.Map;

public class SendPushBo {

    private String              title;

    private String              dests;

    private Map<String, String> varsMap = null;

    public void add(String key, String value) {
        if (varsMap == null) {
            varsMap = new HashMap<>();
        }
        varsMap.put(key, value);
    }

    public Map<String, String> getVarsMap() {
        if (varsMap == null) {
            varsMap = new HashMap<>();
        }
        return varsMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDests() {
        return dests;
    }

    public void setDests(String dests) {
        this.dests = dests;
    }

}
