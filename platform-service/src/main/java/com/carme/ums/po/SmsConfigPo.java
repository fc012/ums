package com.carme.ums.po;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
*
* @ClassName: SmsConfigPo
* @Description: 映射m_sms_config表
* @author: carme-generator
*
*/

@Alias(value = "smsConfigPo")
public class SmsConfigPo extends SmsConfigPoVa implements Serializable{

    /**
    * id
    */
    private Long   id;


    /**
    * 来源
    */
    private String source;


    /**
    * 平台
    */
    private String platform;


    /**
    * 配置
    */
    private String config;


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
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public Date getChangedAt() {
		return changedAt;
	}
	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
	}


}