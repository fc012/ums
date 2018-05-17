<#include "/macro.include">
<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE sqlMap PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd" >
 <sqlMap namespace="${classNameLower}" >

    <!-- ======================================================================== 
      COMMON （别名、resultMap、查询字段、sql...）
    ========================================================================= -->
    <typeAlias alias="${classNameLower}" type="${basePackage}.bo.${table.sqlName?split("_")[1]}.${className}" />
    <typeAlias alias="${classNameLower}Vo" type="${basePackage}.vo.${table.sqlName?split("_")[1]}.${className}Vo" />
    <typeAlias alias="query${className}Vo" type="${basePackage}.vo.${table.sqlName?split("_")[1]}.Query${className}Vo" />

    <resultMap id="BaseResultMap" class="${classNameLower}">
    <#list table.columns as column>
        <result column="${column.sqlName}" property="${column.columnNameLower}" />
    </#list>
    </resultMap>

    <resultMap id="voResultMap" class="${classNameLower}Vo">
    <#list table.columns as column>
        <result column="${column.sqlName}" property="${column.columnNameLower}" />
    </#list>
    </resultMap>

    <sql id="Base_Column_List" >
        <![CDATA[
        <#list table.columns as column>
              ${column.sqlName} <#if column_has_next>,</#if>
        </#list>
        ]]>
    </sql>

    <sql id="select_column">
	     <![CDATA[
	        <#list table.columns as column>
	        	${column.sqlName} <#if column_has_next>,</#if>
	        </#list>
	    ]]>	 		
	</sql>
	
	<sql id="query${className}Page_where">
		from ${table.sqlName} 
		where  1 = 1	
		and is_delete = 0
	</sql>
    
    <!-- ======================================================================== 
      SELECT 
    ========================================================================= -->
    <!--根据ID查询记录-->
    <select id="getById" resultMap="BaseResultMap" parameterClass="java.lang.Long" >
      select 
      <include refid="Base_Column_List" />
      <![CDATA[
      from ${table.sqlName}
      where <#list table.columns as column><#if column.pk>${column.sqlName}</#if></#list> = #id# and is_delete=0
      ]]>
    </select>
    
    <!--分页查询-->
    <select id="query${className}Page" parameterClass="query${className}Vo"
		resultMap="voResultMap">
		<include refid="Page_SqlMap.pagePrefix"/>
			select 
			<include refid="${classNameLower}.select_column"/>
			<include refid="${classNameLower}.query${className}Page_where"/>
		<include refid="Page_SqlMap.pageSuffix"/>
	</select>
	
	<!--查询数据条数-->
	<select id="query${className}Page_count" parameterClass="query${className}Vo"  resultClass="java.lang.Integer">
		select count(*)
		<include refid="query${className}Page_where"/>
	</select>
	
	<!--通过Ids查询多条记录-->
    <select id="getByIds" resultMap="BaseResultMap" parameterClass="${classNameLower}">
	   select <include refid="Base_Column_List"/>
	   from ${table.sqlName} where is_delete = 0 and <#list table.columns as column><#if column.pk>${column.sqlName}</#if></#list> in
	   <iterate property="codes" open="(" close=")" conjunction=",">#codes[]#</iterate>  
	</select>

    <!--多条件组合查询-->
    <select id="query" resultMap="voResultMap" parameterClass="${classNameLower}Vo">
        select <include refid="Base_Column_List"/>
        from ${table.sqlName} where is_delete = 0
        <dynamic>
        <#list table.columns as column>
            <isNotEmpty property="${column.columnNameLower}" prepend="and">
                <![CDATA[ ${column.sqlName} = #${column.columnNameLower}# ]]>
            </isNotEmpty>
        </#list>
        </dynamic>
    </select>
    <!-- ========================================================================
      DELETE 
    ========================================================================= -->
    <!--删除（物理）-->
    <delete id="del" parameterClass="java.lang.Long" >
      <![CDATA[
      delete from ${table.sqlName}
      where <#list table.columns as column><#if column.pk>${column.sqlName}</#if></#list> = #id#
      ]]>
    </delete>
    

    <!-- ======================================================================== 
      INSERT 
    ========================================================================= -->
    <!--保存-->
    <insert id="save" parameterClass="${classNameLower}" >
      insert into ${table.sqlName}
        <dynamic prepend="(" >
        <#list table.columns as column>
            <isNotNull prepend="," property="${column.columnNameLower}" >
        	${column.sqlName} 
        	</isNotNull>
        </#list>
        )
        </dynamic>
         VALUES 
        <dynamic prepend="(" >
        <#list table.columns as column>
            <isNotNull prepend="," property="${column.columnNameLower}" >
        	<![CDATA[ #${column.columnNameLower}#  ]]>
        	</isNotNull>
        </#list>   
        )     
        </dynamic>
         
      <selectKey resultClass="java.lang.Long" keyProperty="id">
			SELECT LAST_INSERT_ID() as id
	  </selectKey>
    </insert>
    
    <!-- ======================================================================== 
      UPDATE 
    ========================================================================= -->
    <!--更新-->
    <update id="update" parameterClass="${classNameLower}">
      update ${table.sqlName}
      <dynamic prepend="set" >
        <#list table.columns as column>
            <#if !column.pk>
        <isNotNull prepend="," property="${column.columnNameLower}" >
          <![CDATA[ ${column.sqlName} = #${column.columnNameLower}# ]]>
        </isNotNull>
            </#if>
        </#list>
      </dynamic>
      <![CDATA[
      where <#list table.columns as column><#if column.pk>${column.sqlName}</#if></#list> = #id#
      ]]>
    </update>
    
    <!--单条删除（逻辑）-->
    <update id="logicDel" parameterClass="java.lang.Long">
      <![CDATA[
      update ${table.sqlName}
      set is_delete = 1
      where <#list table.columns as column><#if column.pk>${column.sqlName}</#if></#list> = #id#
       ]]>
    </update>
    
     <!--批量删除（逻辑）-->
     <update id="delBatch" parameterClass="${classNameLower}Vo">
      update ${table.sqlName}
      set is_delete = 1
      where <#list table.columns as column><#if column.pk>${column.sqlName}</#if></#list> in
      <iterate property="codes" open="(" close=")" conjunction=",">#codes[]#</iterate>
    </update>

    <!-- ========================================================================
     自定义sql
    ========================================================================= -->


</sqlMap>