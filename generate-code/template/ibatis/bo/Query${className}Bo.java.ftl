<#include "/macro.include"/>
<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.vo.${table.sqlName?split("_")[1]};

import com.carme.trade.bo.base.AbstractPageParamBo;

/**
*
* @ClassName: Query${className}Vo
* @Description:
* @author: carme-generator
*
*/
public class Query${className}Bo extends AbstractPageParamBo {


}
