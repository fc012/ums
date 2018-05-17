package com.carme.ums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.service.BaseService;
import com.carme.ums.dao.SmsTemplateDao;
import com.carme.ums.bo.SmsTemplateBo;
import com.carme.ums.po.SmsTemplateBo;
import com.carme.ums.service.SmsTemplateService;

import java.util.List;

@Service("smsTemplateService")
public class SmsTemplateServiceImpl extends BaseServiceImpl<Long,SmsTemplatePo> implements SmsTemplateService {

	@Autowired
    private SmsTemplateDao smsTemplateDao;

    @Override
    public BaseDao getDao() {
        return smsTemplateDao;
    }

    public List<SmsTemplateBo> extQuery(SmsTemplateBo query) {
        return smsTemplateDao.extQuery(query);
    }

    public List<SmsTemplateBo> extQuery(SmsTemplateBo query, Page<SmsTemplateBo> page) {
        return smsTemplateDao.extQuery(query, page);
    }
}
