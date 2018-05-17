package com.carme.ums.dao.sms;



import java.util.List;

import com.carme.platform.base.vo.SimplePageVo;
import com.carme.platform.base.dao.BaseDao;
import com.carme.ums.bo.sms.SmsTemplate;
import com.carme.ums.vo.sms.SmsTemplateVo;
import com.carme.ums.vo.sms.QuerySmsTemplateVo;


public interface SmsTemplateDao extends BaseDao<Long, SmsTemplate>{

	
	/**
	 * 分页查找
	 * 
	 * @param param
	 * @return
	 */
	SimplePageVo<SmsTemplateVo> querySmsTemplatePage(QuerySmsTemplateVo param);

	/**
	 * 
	 * @Description: 根据Ids获取记录
	 * @param record
	 * @return
	 */
	List<SmsTemplate> getByIds(SmsTemplateVo record);

    /**
    *
    * @Description: 多条件组合查询
    * @param record
    * @return
    */
    List
    <SmsTemplateVo> query(SmsTemplateVo record);

}