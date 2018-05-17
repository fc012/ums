<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basePackage}.${table.sqlName?split("_")[1]};



import java.util.List;

import ${basePackage}.bo.${table.sqlName?split("_")[1]}.${className};
import ${basePackage}.vo.${table.sqlName?split("_")[1]}.${className}Vo;
import ${basePackage}.vo.${table.sqlName?split("_")[1]}.Query${className}Vo;
import com.carme.platform.base.vo.SimplePageVo;


public interface ${className}Service {

	
    /**
     * 
     * @Description: 根据ID获取记录
     * @param id
     * @return
     */
    ${className} getById(Long id);

    /**
     * 
     * @Description: 保存记录并返回主键
     * @param entity
     * @return
     */
    Long save(${className} entity);

    /**
     * 
     * @Description: 批量保存 记录
     * @param entityList
     */
    void save(List<${className}> entityList);

    /**
     * 
     * @Description: 更新记录（主键不为空）
     * @param entity
     * @return
     */
    int update(${className} entity);

    /**
     * 
     * @Description: 批量更新记录（记录主键不为空）
     * @param entityList
     */
    void update(List<${className}> entityList);

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
<${className}Vo> query(${className}Vo record);

    /**
    * 分页查找
    *
    * @param param
    * @return
    */
    SimplePageVo<${className}Vo> query${className}Page(Query${className}Vo param);

}