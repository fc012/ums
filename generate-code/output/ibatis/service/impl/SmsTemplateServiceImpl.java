package com.carme.ums.sms.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carme.ums.bo.sms.SmsTemplate;
import com.carme.ums.vo.sms.SmsTemplateVo;
import com.carme.ums.vo.sms.QuerySmsTemplateVo;
import com.carme.ums.dao.sms.SmsTemplateDao;
import com.carme.ums.sms.SmsTemplateService;
import com.carme.platform.base.vo.SimplePageVo;

@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {

	@Autowired
    private SmsTemplateDao smsTemplateDao;
	
	 @Override
    public SmsTemplate getById(Long id) {
        return smsTemplateDao.getById(id);
    }

    @Override
    public Long save(SmsTemplate entity) {
entity.setCreatedAt(new Date());
if (StringUtil.isEmpty(entity.getCreatedBy())) {
throw new RuntimeException("createdBy is null");
}
        return smsTemplateDao.save(entity);
    }

    @Override
    public void save(List<SmsTemplate> entityList) {
        smsTemplateDao.save(entityList);
    }

    @Override
    public int update(SmsTemplate entity) {
entity.setChangedAt(new Date());
if (StringUtil.isEmpty(entity.getChangedBy())) {
throw new RuntimeException("changeBy is null");
}
        return smsTemplateDao.update(entity);
    }

    @Override
    public void update(List<SmsTemplate> entityList) {
        smsTemplateDao.update(entityList);
    }

    @Override
    public int deleteById(Long id) {
        return smsTemplateDao.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        smsTemplateDao.deleteByIds(ids);
    }

    @Override
    public int logicDelById(Long id) {
        return smsTemplateDao.logicDelById(id);
    }

    @Override
    public void logicDelById(Long[] ids) {
        smsTemplateDao.logicDelById(ids);
    }

    @Override
public List
<SmsTemplateVo> query(SmsTemplateVo record) {
        return smsTemplateDao.query(record);
    }

    @Override
    public SimplePageVo<SmsTemplateVo> querySmsTemplatePage(
        QuerySmsTemplateVo param) {
        return smsTemplateDao.querySmsTemplatePage(param);
    }

}
