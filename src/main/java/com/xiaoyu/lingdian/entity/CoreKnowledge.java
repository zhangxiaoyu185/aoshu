package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 知识点表
*/
public class CoreKnowledge extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crkleUnid;

	/**
	*标识UUID
	*/
	private String crkleUuid;

	/**
	*知识点名称
	*/
	private String crkleName;

	/**
	*所属大知识点
	*/
	private String crkleTop;

	/**
	*所属类别
	*/
	private String crkleCategory;
	
	/**
	*所属类别名称
	*/
	private String crceyName;
	
	public void setCrkleUnid(Integer crkleUnid) {
		this.crkleUnid = crkleUnid;
	}

	public Integer getCrkleUnid( ) {
		return crkleUnid;
	}

	public void setCrkleUuid(String crkleUuid) {
		this.crkleUuid = crkleUuid;
	}

	public String getCrkleUuid( ) {
		return crkleUuid;
	}

	public void setCrkleName(String crkleName) {
		this.crkleName = crkleName;
	}

	public String getCrkleName( ) {
		return crkleName;
	}

	public void setCrkleTop(String crkleTop) {
		this.crkleTop = crkleTop;
	}

	public String getCrkleTop( ) {
		return crkleTop;
	}

	public String getCrkleCategory() {
		return crkleCategory;
	}

	public void setCrkleCategory(String crkleCategory) {
		this.crkleCategory = crkleCategory;
	}

	public String getCrceyName() {
		return crceyName;
	}

	public void setCrceyName(String crceyName) {
		this.crceyName = crceyName;
	}

	public CoreKnowledge( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}