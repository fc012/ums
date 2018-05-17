<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basePackage}.${table.sqlName?split("_")[1]}.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${basePackage}.bo.${table.sqlName?split("_")[1]}.${className};
import ${basePackage}.vo.${table.sqlName?split("_")[1]}.${className}Vo;
import ${basePackage}.vo.${table.sqlName?split("_")[1]}.Query${className}Vo;
import ${basePackage}.dao.${table.sqlName?split("_")[1]}.${className}Dao;
import ${basePackage}.${table.sqlName?split("_")[1]}.${className}Service;
import com.carme.platform.base.vo.SimplePageVo;

@Service
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
    private ${className}Dao ${classNameLower}Dao;
	
	 @Override
    public ${className} getById(Long id) {
        return ${classNameLower}Dao.getById(id);
    }

    @Override
    public Long save(${className} entity) {
entity.setCreatedAt(new Date());
if (StringUtil.isEmpty(entity.getCreatedBy())) {
throw new RuntimeException("createdBy is null");
}
        return ${classNameLower}Dao.save(entity);
    }

    @Override
    public void save(List<${className}> entityList) {
        ${classNameLower}Dao.save(entityList);
    }

    @Override
    public int update(${className} entity) {
entity.setChangedAt(new Date());
if (StringUtil.isEmpty(entity.getChangedBy())) {
throw new RuntimeException("changeBy is null");
}
        return ${classNameLower}Dao.update(entity);
    }

    @Override
    public void update(List<${className}> entityList) {
        ${classNameLower}Dao.update(entityList);
    }

    @Override
    public int deleteById(Long id) {
        return ${classNameLower}Dao.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        ${classNameLower}Dao.deleteByIds(ids);
    }

    @Override
    public int logicDelById(Long id) {
        return ${classNameLower}Dao.logicDelById(id);
    }

    @Override
    public void logicDelById(Long[] ids) {
        ${classNameLower}Dao.logicDelById(ids);
    }

    @Override
public List
<${className}Vo> query(${className}Vo record) {
        return ${classNameLower}Dao.query(record);
    }

    @Override
    public SimplePageVo<${className}Vo> query${className}Page(
        Query${className}Vo param) {
        return ${classNameLower}Dao.query${className}Page(param);
    }

}
