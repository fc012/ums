<#include "/macro.include">
<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${className}Dao">

    <!-- ========================================================================
     								自定义sql
    ========================================================================= -->
    <resultMap id="ExtResultMap" type="${classNameLower}Bo">
    <#list table.columns as column>
        <result column="${column.sqlName}" property="${column.columnNameLower}"/>
    </#list>
    </resultMap>

    <sql id="Ext_Column_List">
        <![CDATA[
        <#list table.columns as column>
        ${column.sqlName} <#if column_has_next>,</#if>
        </#list>
        ]]>
    </sql>

    <!--可扩展查询-->
    <select id="extQuery" resultMap="ExtResultMap" parameterType="${classNameLower}Bo">
        select
        <include refid="Ext_Column_List"/>
        from ${table.sqlName} where 1 = 1
        <#list table.columns as column>
            <if test="${column.columnNameLower} != null">
                and ${column.sqlName} = ${r"#{"}${column.columnNameLower}}
            </if>
        </#list>
        and is_delete = 0
    </select>



</mapper>