package com.carme.ums.entity.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
*
* @ClassName: SmsTemplatePo
* @Description: 映射m_sms_template表
* @author: carme-generator
*
*/

@Alias(value = "smsTemplatePo")
public class SmsTemplatePo extends SmsTemplatePoVa implements Serializable{

    /**
    * id
    */
    private Long   id;


    /**
    * 来源
    */
    private String source;


    /**
    * 模版号编号
    */
    private String templateNo;


    /**
    * 三方模板编号
    */
    private String thirdTemplateNo;


    /**
    * 是否删除
    */
    private Integer isDelete;


    /**
    * 创建人
    */
    private String createdBy;


    /**
    * 创建时间
    */
    private Date createdAt;


    /**
    * 修改人
    */
    private String changedBy;


    /**
    * 修改时间
    */
    private Date changedAt;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTemplateNo() {
		return templateNo;
	}
	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}
	public String getThirdTemplateNo() {
		return thirdTemplateNo;
	}
	public void setThirdTemplateNo(String thirdTemplateNo) {
		this.thirdTemplateNo = thirdTemplateNo;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public java.util.Date getChangedAt() {
		return changedAt;
	}
	public void setChangedAt(java.util.Date changedAt) {
		this.changedAt = changedAt;
	}


}