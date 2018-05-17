
package com.carme.ums.dao.sms.impl;

import org.springframework.stereotype.Repository;

import com.carme.platform.base.vo.SimplePageVo;
import com.carme.platform.dao.impl.CarBaseDaoImpl;
import java.util.List;
import com.carme.ums.bo.sms.SmsTemplate;
import com.carme.ums.dao.sms.SmsTemplateDao;
import com.carme.ums.vo.sms.SmsTemplateVo;
import com.carme.ums.vo.sms.QuerySmsTemplateVo;

@Repository
public class SmsTemplateDaoImpl extends CarBaseDaoImpl<Long, SmsTemplate> implements SmsTemplateDao {

	public String getNameSpace(String suffix) {
		return "smsTemplate." + suffix;
	}

	
	@Override
	public SimplePageVo<SmsTemplateVo> querySmsTemplatePage(
			QuerySmsTemplateVo param) {
		return this.page(getNameSpace("querySmsTemplatePage"), param);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<SmsTemplate> getByIds(SmsTemplateVo record) {
		
		return queryForList(getNameSpace("getByIds"), record);
	}

    @Override
    public List
    <SmsTemplateVo> query(SmsTemplateVo record) {
    return queryForList(getNameSpace("query"), record);
    }

}
