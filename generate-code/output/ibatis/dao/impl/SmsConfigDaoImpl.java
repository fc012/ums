
package com.carme.ums.dao.sms.impl;

import org.springframework.stereotype.Repository;

import com.carme.platform.base.vo.SimplePageVo;
import com.carme.platform.dao.impl.CarBaseDaoImpl;
import java.util.List;
import com.carme.ums.bo.sms.SmsConfig;
import com.carme.ums.dao.sms.SmsConfigDao;
import com.carme.ums.vo.sms.SmsConfigVo;
import com.carme.ums.vo.sms.QuerySmsConfigVo;

@Repository
public class SmsConfigDaoImpl extends CarBaseDaoImpl<Long, SmsConfig> implements SmsConfigDao {

	public String getNameSpace(String suffix) {
		return "smsConfig." + suffix;
	}

	
	@Override
	public SimplePageVo<SmsConfigVo> querySmsConfigPage(
			QuerySmsConfigVo param) {
		return this.page(getNameSpace("querySmsConfigPage"), param);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<SmsConfig> getByIds(SmsConfigVo record) {
		
		return queryForList(getNameSpace("getByIds"), record);
	}

    @Override
    public List
    <SmsConfigVo> query(SmsConfigVo record) {
    return queryForList(getNameSpace("query"), record);
    }

}
