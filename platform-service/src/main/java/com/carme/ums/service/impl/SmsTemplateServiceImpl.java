package com.carme.ums.service.impl;

import com.carme.common.framework.base.dao.BaseDao;
import com.carme.common.framework.base.service.BaseServiceImpl;
import com.carme.common.framework.cache.CacheAdd;
import com.carme.common.framework.mybatis.Page;
import com.carme.ums.bo.SmsTemplateBo;
import com.carme.ums.constants.UmsCacheConstants;
import com.carme.ums.dao.SmsTemplateDao;
import com.carme.ums.po.SmsTemplatePo;
import com.carme.ums.service.SmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("smsTemplateService")
public class SmsTemplateServiceImpl extends BaseServiceImpl<Long, SmsTemplatePo> implements
                                                                                SmsTemplateService {

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

    @Override
    @CacheAdd(key = UmsCacheConstants.TEMPLATE_ID, expireTime = 10 * 60)
    public SmsTemplatePo getTemplateNoByCache(String templateNo) {
        SmsTemplatePo queryParam = new SmsTemplatePo();
        queryParam.setTemplateNo(templateNo);
        List<SmsTemplatePo> list = smsTemplateDao.query(queryParam);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
