package com.carme.ums.dao;

import com.carme.ums.po.SmsConfigPo;
import com.carme.ums.bo.SmsConfigBo;
import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.dao.BaseDao;
import java.util.List;

public interface SmsConfigDao extends BaseDao<Long, SmsConfigPo>{
    /**
    * 查询对象
    * @param query
    * @return
    */
    List<SmsConfigBo> extQuery(SmsConfigBo query);

    /**
    * 分页查询
    * @param param
    * @param page
    * @return
    */
    List<SmsConfigBo> extQuery(SmsConfigBo param, Page<SmsConfigBo> page);
}