package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 课程表
*/
public class CoreCourse extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crcreUnid;

	/**
	*标识UUID
	*/
	private String crcreUuid;

	/**
	*课程名
	*/
	private String crcreName;

	/**
	*课程内容
	*/
	private String crcreContent;
	
	/**
	*所属年级
	*/
	private String crcreClass;
	
	public void setCrcreUnid(Integer crcreUnid) {
		this.crcreUnid = crcreUnid;
	}

	public Integer getCrcreUnid( ) {
		return crcreUnid;
	}

	public void setCrcreUuid(String crcreUuid) {
		this.crcreUuid = crcreUuid;
	}

	public String getCrcreUuid( ) {
		return crcreUuid;
	}

	public void setCrcreName(String crcreName) {
		this.crcreName = crcreName;
	}

	public String getCrcreName( ) {
		return crcreName;
	}

	public String getCrcreContent() {
		return crcreContent;
	}

	public void setCrcreContent(String crcreContent) {
		this.crcreContent = crcreContent;
	}

	public void setCrcreClass(String crcreClass) {
		this.crcreClass = crcreClass;
	}

	public String getCrcreClass( ) {
		return crcreClass;
	}

	public CoreCourse( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}