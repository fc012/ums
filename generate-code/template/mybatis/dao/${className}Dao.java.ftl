<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.dao;

import ${basePackage}.po.${className}Po;
import ${basePackage}.bo.${className}Bo;
import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.dao.BaseDao;
import java.util.List;

public interface ${className}Dao extends BaseDao<${table.columns[0].javaType}, ${className}Po>{
    /**
    * 查询对象
    * @param query
    * @return
    */
    List<${className}Bo> extQuery(${className}Bo query);

    /**
    * 分页查询
    * @param param
    * @param page
    * @return
    */
    List<${className}Bo> extQuery(${className}Bo param, Page<${className}Bo> page);
}