<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.service;

import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.service.BaseService;
import ${basePackage}.bo.${className}Bo;
import ${basePackage}.po.${className}Po;
import java.util.List;

public interface ${className}Service extends BaseService<${table.columns[0].javaType} , ${className}Po>{

    List<${className}Bo> extQuery(${className}Bo query);

    List<${className}Bo> extQuery(${className}Bo query, Page<${className}Bo> page);

}