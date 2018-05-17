package com.carme.ums.dao.sms;



import java.util.List;

import com.carme.platform.base.vo.SimplePageVo;
import com.carme.platform.base.dao.BaseDao;
import com.carme.ums.bo.sms.SmsConfig;
import com.carme.ums.vo.sms.SmsConfigVo;
import com.carme.ums.vo.sms.QuerySmsConfigVo;


public interface SmsConfigDao extends BaseDao<Long, SmsConfig>{

	
	/**
	 * 分页查找
	 * 
	 * @param param
	 * @return
	 */
	SimplePageVo<SmsConfigVo> querySmsConfigPage(QuerySmsConfigVo param);

	/**
	 * 
	 * @Description: 根据Ids获取记录
	 * @param record
	 * @return
	 */
	List<SmsConfig> getByIds(SmsConfigVo record);

    /**
    *
    * @Description: 多条件组合查询
    * @param record
    * @return
    */
    List
    <SmsConfigVo> query(SmsConfigVo record);

}