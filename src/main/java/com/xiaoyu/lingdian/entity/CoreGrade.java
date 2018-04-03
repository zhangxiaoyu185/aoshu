package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 班级表
*/
public class CoreGrade extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crgaeUnid;

	/**
	*标识UUID
	*/
	private String crgaeUuid;

	/**
	*班级名
	*/
	private String crgaeName;

	/**
	*开通年级UUID集合，逗号隔开
	*/
	private String crgaeClasss;

	public void setCrgaeUnid(Integer crgaeUnid) {
		this.crgaeUnid = crgaeUnid;
	}

	public Integer getCrgaeUnid( ) {
		return crgaeUnid;
	}

	public void setCrgaeUuid(String crgaeUuid) {
		this.crgaeUuid = crgaeUuid;
	}

	public String getCrgaeUuid( ) {
		return crgaeUuid;
	}

	public void setCrgaeName(String crgaeName) {
		this.crgaeName = crgaeName;
	}

	public String getCrgaeName( ) {
		return crgaeName;
	}

	public void setCrgaeClasss(String crgaeClasss) {
		this.crgaeClasss = crgaeClasss;
	}

	public String getCrgaeClasss( ) {
		return crgaeClasss;
	}

	public CoreGrade( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}