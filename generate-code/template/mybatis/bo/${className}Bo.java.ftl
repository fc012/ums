<#include "/macro.include"/>
<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.bo;

import ${basePackage}.po.${className}Po;
import org.apache.ibatis.type.Alias;

/**
*
* @ClassName: ${className}Bo
* @Description:
* @author: carme-generator
*
*/
@Alias(value = "${className?uncap_first}Bo")
public class ${className}Bo extends ${className}Po {

}
