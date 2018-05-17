<#include "/macro.include"/>
<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.vo.${table.sqlName?split("_")[1]};

import ${basePackage}.bo.${table.sqlName?split("_")[1]}.${className};

/**
 *
 * @ClassName: ${className}Bo
 * @Description:
 * @author: carme-generator
 *
 */
public class ${className}Bo extends ${className}Po {


	/**Po
	 * id集合
	 */
	private String[] codes;



	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}



}
