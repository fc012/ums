package com.carme.ums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.service.BaseService;
import com.carme.ums.dao.SmsConfigDao;
import com.carme.ums.bo.SmsConfigBo;
import com.carme.ums.po.SmsConfigBo;
import com.carme.ums.service.SmsConfigService;

import java.util.List;

@Service("smsConfigService")
public class SmsConfigServiceImpl extends BaseServiceImpl<Long,SmsConfigPo> implements SmsConfigService {

	@Autowired
    private SmsConfigDao smsConfigDao;

    @Override
    public BaseDao getDao() {
        return smsConfigDao;
    }

    public List<SmsConfigBo> extQuery(SmsConfigBo query) {
        return smsConfigDao.extQuery(query);
    }

    public List<SmsConfigBo> extQuery(SmsConfigBo query, Page<SmsConfigBo> page) {
        return smsConfigDao.extQuery(query, page);
    }
}
