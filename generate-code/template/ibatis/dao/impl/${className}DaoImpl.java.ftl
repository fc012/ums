<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basePackage}.dao.${table.sqlName?split("_")[1]}.impl;

import org.springframework.stereotype.Repository;

import com.carme.platform.base.vo.SimplePageVo;
import com.carme.platform.dao.impl.CarBaseDaoImpl;
import java.util.List;
import ${basePackage}.bo.${table.sqlName?split("_")[1]}.${className};
import ${basePackage}.dao.${table.sqlName?split("_")[1]}.${className}Dao;
import ${basePackage}.vo.${table.sqlName?split("_")[1]}.${className}Vo;
import ${basePackage}.vo.${table.sqlName?split("_")[1]}.Query${className}Vo;

@Repository
public class ${className}DaoImpl extends CarBaseDaoImpl<Long, ${className}> implements ${className}Dao {

	public String getNameSpace(String suffix) {
		return "${classNameLower}." + suffix;
	}

	
	@Override
	public SimplePageVo<${className}Vo> query${className}Page(
			Query${className}Vo param) {
		return this.page(getNameSpace("query${className}Page"), param);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<${className}> getByIds(${className}Vo record) {
		
		return queryForList(getNameSpace("getByIds"), record);
	}

    @Override
    public List
    <${className}Vo> query(${className}Vo record) {
    return queryForList(getNameSpace("query"), record);
    }

}
