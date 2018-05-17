<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carme.common.framework.mybatis.Page;
import com.carme.common.framework.base.service.BaseService;
import ${basePackage}.dao.${className}Dao;
import ${basePackage}.bo.${className}Bo;
import ${basePackage}.po.${className}Bo;
import ${basePackage}.service.${className}Service;

import java.util.List;

@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${table.columns[0].javaType},${className}Po> implements ${className}Service {

	@Autowired
    private ${className}Dao ${classNameLower}Dao;

    @Override
    public BaseDao getDao() {
        return ${classNameLower}Dao;
    }

    public List<${className}Bo> extQuery(${className}Bo query) {
        return ${classNameLower}Dao.extQuery(query);
    }

    public List<${className}Bo> extQuery(${className}Bo query, Page<${className}Bo> page) {
        return ${classNameLower}Dao.extQuery(query, page);
    }
}
