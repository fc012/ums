package com.carme.ums.sms;



import java.util.List;

import com.carme.ums.bo.sms.SmsTemplate;
import com.carme.ums.vo.sms.SmsTemplateVo;
import com.carme.ums.vo.sms.QuerySmsTemplateVo;
import com.carme.platform.base.vo.SimplePageVo;


public interface SmsTemplateService {

	
    /**
     * 
     * @Description: 根据ID获取记录
     * @param id
     * @return
     */
    SmsTemplate getById(Long id);

    /**
     * 
     * @Description: 保存记录并返回主键
     * @param entity
     * @return
     */
    Long save(SmsTemplate entity);

    /**
     * 
     * @Description: 批量保存 记录
     * @param entityList
     */
    void save(List<SmsTemplate> entityList);

    /**
     * 
     * @Description: 更新记录（主键不为空）
     * @param entity
     * @return
     */
    int update(SmsTemplate entity);

    /**
     * 
     * @Description: 批量更新记录（记录主键不为空）
     * @param entityList
     */
    void update(List<SmsTemplate> entityList);

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
<SmsTemplateVo> query(SmsTemplateVo record);

    /**
    * 分页查找
    *
    * @param param
    * @return
    */
    SimplePageVo<SmsTemplateVo> querySmsTemplatePage(QuerySmsTemplateVo param);

}