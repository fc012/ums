package com.carme.ums.sms.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carme.ums.bo.sms.SmsConfig;
import com.carme.ums.vo.sms.SmsConfigVo;
import com.carme.ums.vo.sms.QuerySmsConfigVo;
import com.carme.ums.dao.sms.SmsConfigDao;
import com.carme.ums.sms.SmsConfigService;
import com.carme.platform.base.vo.SimplePageVo;

@Service
public class SmsConfigServiceImpl implements SmsConfigService {

	@Autowired
    private SmsConfigDao smsConfigDao;
	
	 @Override
    public SmsConfig getById(Long id) {
        return smsConfigDao.getById(id);
    }

    @Override
    public Long save(SmsConfig entity) {
entity.setCreatedAt(new Date());
if (StringUtil.isEmpty(entity.getCreatedBy())) {
throw new RuntimeException("createdBy is null");
}
        return smsConfigDao.save(entity);
    }

    @Override
    public void save(List<SmsConfig> entityList) {
        smsConfigDao.save(entityList);
    }

    @Override
    public int update(SmsConfig entity) {
entity.setChangedAt(new Date());
if (StringUtil.isEmpty(entity.getChangedBy())) {
throw new RuntimeException("changeBy is null");
}
        return smsConfigDao.update(entity);
    }

    @Override
    public void update(List<SmsConfig> entityList) {
        smsConfigDao.update(entityList);
    }

    @Override
    public int deleteById(Long id) {
        return smsConfigDao.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        smsConfigDao.deleteByIds(ids);
    }

    @Override
    public int logicDelById(Long id) {
        return smsConfigDao.logicDelById(id);
    }

    @Override
    public void logicDelById(Long[] ids) {
        smsConfigDao.logicDelById(ids);
    }

    @Override
public List
<SmsConfigVo> query(SmsConfigVo record) {
        return smsConfigDao.query(record);
    }

    @Override
    public SimplePageVo<SmsConfigVo> querySmsConfigPage(
        QuerySmsConfigVo param) {
        return smsConfigDao.querySmsConfigPage(param);
    }

}
