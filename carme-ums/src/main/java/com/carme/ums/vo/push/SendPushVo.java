package com.carme.ums.vo.push;

import com.carme.common.framework.validate.annotation.RequireAnno;

/**
 * Created by huangkl on 2018/5/2.
 */
public class SendPushVo {
    @RequireAnno
    private String dests;

    @RequireAnno
    private String title;

    private String vars;

    public String getDests() {
        return dests;
    }

    public void setDests(String dests) {
        this.dests = dests;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVars() {
        return vars;
    }

    public void setVars(String vars) {
        this.vars = vars;
    }

}
