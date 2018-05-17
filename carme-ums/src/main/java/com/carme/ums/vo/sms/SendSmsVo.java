package com.carme.ums.vo.sms;

import com.carme.common.framework.validate.annotation.RequireAnno;

/**
 * Created by huangkl on 2018/5/3.
 */
public class SendSmsVo {

    @RequireAnno
    private String dests;

    @RequireAnno
    private String tplNo;

    @RequireAnno
    private String tplParams;

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

    public String getTplParams() {
        return tplParams;
    }

    public void setTplParams(String tplParams) {
        this.tplParams = tplParams;
    }

}
