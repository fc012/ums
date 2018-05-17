<#include "/macro.include">
<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${className}Dao">
    <!-- ========================================================================
      	自动生成sql
    ========================================================================= -->
    <resultMap id="BaseResultMap" type="${classNameLower}Po">
    <#list table.columns as column>
        <result column="${column.sqlName}" property="${column.columnNameLower}"/>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <![CDATA[
        <#list table.columns as column>
        ${column.sqlName} <#if column_has_next>,</#if>
        </#list>
        ]]>
    </sql>

    <!-- ========================================================================
      	自动生成sql
    ========================================================================= -->
    <!--根据ID查询记录-->
    <select id="getById" resultMap="BaseResultMap" parameterType="java.lang.Long">
        select
        <include refid="Base_Column_List"/>
        from ${table.sqlName} where id = ${r"#{id}"} and is_delete=0
    </select>

    <!--通过IDS查询多条记录-->
    <select id="getByIds" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${table.sqlName} where id in
        <foreach item="item" index="index" collection="list"
                 open="(" separator="," close=")">
        ${r"#{item}"}
        </foreach>
        and is_delete = 0
    </select>

    <!--query基础查询-->
    <select id="query" resultMap="BaseResultMap" parameterType="${classNameLower}Po">
        select
        <include refid="Base_Column_List"/>
        from ${table.sqlName} where 1 = 1
    <#list table.columns as column>
        <if test="${column.columnNameLower} != null">
            and ${column.sqlName} = ${r"#{"}${column.columnNameLower}}
        </if>
    </#list>
        and is_delete = 0
    </select>


    <!--save保存-->
    <insert id="save" parameterType="${classNameLower}Po" useGeneratedKeys="true" keyProperty="id">
        insert into ${table.sqlName}
        (
    <#list table.columns as column>
        <#if !column.pk>
        ${column.sqlName} <#if column_has_next>,</#if>
        </#if>
    </#list>
        ) values(
    <#list table.columns as column>
        <#if !column.pk>
        ${r"#{"}${column.columnNameLower}}<#if column_has_next>,</#if>
        </#if>
    </#list>
        )
    </insert>

    <!--saveBatch批量保存-->
    <insert id="saveBatch" parameterType="java.util.List" useGeneratedKeys="true" keyProperty="id">
        insert into ${table.sqlName}
        (
    <#list table.columns as column>
        <#if !column.pk>
        ${column.sqlName} <#if column_has_next>,</#if>
        </#if>
    </#list>
        ) values
        <foreach collection="list" item="item" index="index" separator=",">
            (
        <#list table.columns as column>
            <#if !column.pk>
            ${r"#{item."}${column.columnNameLower}}<#if column_has_next>,</#if>
            </#if>
        </#list>
            )
        </foreach>
    </insert>

    <!--update更新-->
    <update id="update" parameterType="${classNameLower}Po">
        update ${table.sqlName}
        <set>
        <#list table.columns as column>
            <#if !column.pk>
                <if test="${column.columnNameLower} != null">
                ${column.sqlName} = ${r"#{"}${column.columnNameLower}},
                </if>
            </#if>
        </#list>
        </set>
        where id = ${r"#{id}"} and is_delete=0
    </update>

    <!--updateBatch批量更新-->
    <update id="updateBatch" parameterType="java.util.List">
        <foreach collection="list" item="item" index="index" separator=";">
            update ${table.sqlName}
            <set>
            <#list table.columns as column>
                <#if !column.pk>
                    <if test="${r"#{item."}${column.columnNameLower}} != null">
                    ${column.sqlName} = ${r"#{item."}${column.columnNameLower}},
                    </if>
                </#if>
            </#list>
            </set>
            where id = ${r"#{item.id}"} and is_delete=0
        </foreach>
    </update>

    <update id="logicDelById" parameterType="java.lang.Long">
        update ${table.sqlName} set is_delete=1 where id=${r"#{id}"}
    </update>

    <!--通过IDS查询多条记录-->
    <update id="logicDelByIds">
        update ${table.sqlName} set is_delete=1 where id in
        <foreach item="item" index="index" collection="list"
                 open="(" separator="," close=")">
        ${r"#{item}"}
        </foreach>
    </update>
    <!-- 根据id删除记录 -->
    <delete id="deleteById">
        delete from ${table.sqlName} where id=${r"#{id}"}
    </delete>
    <!-- 根据id列表删除记录 -->
    <delete id="deleteByIds">
        delete from ${table.sqlName} where id in
        <foreach item="item" index="index" collection="list"
                 open="(" separator="," close=")">
        ${r"#{item}"}
        </foreach>
    </delete>

</mapper>