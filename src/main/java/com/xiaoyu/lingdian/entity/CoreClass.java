package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 年级表
*/
public class CoreClass extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crcasUnid;

	/**
	*标识UUID
	*/
	private String crcasUuid;

	/**
	*年级名称
	*/
	private String crcasName;

	public void setCrcasUnid(Integer crcasUnid) {
		this.crcasUnid = crcasUnid;
	}

	public Integer getCrcasUnid( ) {
		return crcasUnid;
	}

	public void setCrcasUuid(String crcasUuid) {
		this.crcasUuid = crcasUuid;
	}

	public String getCrcasUuid( ) {
		return crcasUuid;
	}

	public void setCrcasName(String crcasName) {
		this.crcasName = crcasName;
	}

	public String getCrcasName( ) {
		return crcasName;
	}

	public CoreClass( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}