package com.carme.ums.dao;

import com.carme.ums.po.SmsTemplatePo;
import com.carme.ums.bo.SmsTemplateBo;
import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.dao.BaseDao;
import java.util.List;

public interface SmsTemplateDao extends BaseDao<Long, SmsTemplatePo>{
    /**
    * 查询对象
    * @param query
    * @return
    */
    List<SmsTemplateBo> extQuery(SmsTemplateBo query);

    /**
    * 分页查询
    * @param param
    * @param page
    * @return
    */
    List<SmsTemplateBo> extQuery(SmsTemplateBo param, Page<SmsTemplateBo> page);
}