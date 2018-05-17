package com.carme.ums.service.impl;

import com.carme.common.framework.base.dao.BaseDao;
import com.carme.common.framework.base.service.BaseServiceImpl;
import com.carme.common.framework.mybatis.Page;
import com.carme.ums.bo.SmsConfigBo;
import com.carme.ums.dao.SmsConfigDao;
import com.carme.ums.po.SmsConfigPo;
import com.carme.ums.service.SmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
