package com.carme.ums.service;

import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.service.BaseService;
import com.carme.ums.bo.SmsConfigBo;
import com.carme.ums.po.SmsConfigPo;
import java.util.List;

public interface SmsConfigService extends BaseService<Long , SmsConfigPo>{

    List<SmsConfigBo> extQuery(SmsConfigBo query);

    List<SmsConfigBo> extQuery(SmsConfigBo query, Page<SmsConfigBo> page);

}