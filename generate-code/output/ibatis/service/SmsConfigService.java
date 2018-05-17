package com.carme.ums.sms;



import java.util.List;

import com.carme.ums.bo.sms.SmsConfig;
import com.carme.ums.vo.sms.SmsConfigVo;
import com.carme.ums.vo.sms.QuerySmsConfigVo;
import com.carme.platform.base.vo.SimplePageVo;


public interface SmsConfigService {

	
    /**
     * 
     * @Description: 根据ID获取记录
     * @param id
     * @return
     */
    SmsConfig getById(Long id);

    /**
     * 
     * @Description: 保存记录并返回主键
     * @param entity
     * @return
     */
    Long save(SmsConfig entity);

    /**
     * 
     * @Description: 批量保存 记录
     * @param entityList
     */
    void save(List<SmsConfig> entityList);

    /**
     * 
     * @Description: 更新记录（主键不为空）
     * @param entity
     * @return
     */
    int update(SmsConfig entity);

    /**
     * 
     * @Description: 批量更新记录（记录主键不为空）
     * @param entityList
     */
    void update(List<SmsConfig> entityList);

    /**
     * 
     * @Description: 根据ID删除记录（物理）
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 
     * @Description: 根据ID集合批量删除记录（物理）
     * @param ids
     */
    void deleteByIds(Long[] ids);

    /**
     * 
     * @Description: 根据ID删除记录（逻辑）
     * @param id
     * @return
     */
    int logicDelById(Long id);

    /**
     * 
     * @Description: 根据ID集合批量删除记录（逻辑）
     * @param ids
     */
    void logicDelById(Long[] ids);

    /**
    *
    * @Description: 多条件组合查询
    * @param record
    * @return
    */
List
<SmsConfigVo> query(SmsConfigVo record);

    /**
    * 分页查找
    *
    * @param param
    * @return
    */
    SimplePageVo<SmsConfigVo> querySmsConfigPage(QuerySmsConfigVo param);

}