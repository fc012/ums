<#include "/macro.include"/>
<#include "/custom.include">
<#assign className = table.className>
package ${basePackage}.entity.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
*
* @ClassName: ${className}Po
* @Description: 映射${table.sqlName}表
* @author: carme-generator
*
*/

@Alias(value = "${className?uncap_first}Po")
public class ${className}Po extends ${className}PoVa implements Serializable{

<#list table.columns as column>
    <#if column.pk>
    /**
    * ${column.columnAlias}
    */
    private Long   ${column.columnNameLower};
    <#elseif column.javaType == "Boolean">

    /**
    * ${column.columnAlias}
    */
    private Integer ${column.columnNameLower};
    <#elseif column.javaType == "java.lang.String">

    /**
    * ${column.columnAlias}
    */
    private String ${column.columnNameLower};
    <#elseif column.javaType == "java.util.Date">

    /**
    * ${column.columnAlias}
    */
    private Date ${column.columnNameLower};
    <#elseif column.javaType == "java.lang.Long">

    /**
    * ${column.columnAlias}
    */
    private Long ${column.columnNameLower};
    <#elseif column.javaType == "java.lang.Integer">

    /**
    * ${column.columnAlias}
    */
    private Integer  ${column.columnNameLower};
    <#else>

    /**
    * ${column.columnAlias}
    */
    private ${column.javaType} ${column.columnNameLower};
    </#if>

</#list>
<#list table.columns as column>
    <@genGetterAndSetter propertyName=column.columnNameLower javaType=column.javaType></@genGetterAndSetter>
</#list>


}