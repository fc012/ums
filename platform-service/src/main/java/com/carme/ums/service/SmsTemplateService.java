package com.carme.ums.service;

import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.service.BaseService;
import com.carme.ums.bo.SmsTemplateBo;
import com.carme.ums.po.SmsTemplatePo;
import java.util.List;

public interface SmsTemplateService extends BaseService<Long, SmsTemplatePo> {

    List<SmsTemplateBo> extQuery(SmsTemplateBo query);

    List<SmsTemplateBo> extQuery(SmsTemplateBo query, Page<SmsTemplateBo> page);

    /**
     * 根据模板编号获得模板配置
     * @param templateId
     * @return
     */
    SmsTemplatePo getTemplateNoByCache(String templateNo);

}