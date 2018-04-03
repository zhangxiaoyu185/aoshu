package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 类别表
*/
public class CoreCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crceyUnid;

	/**
	*标识UUID
	*/
	private String crceyUuid;

	/**
	*类别名称
	*/
	private String crceyName;

	public void setCrceyUnid(Integer crceyUnid) {
		this.crceyUnid = crceyUnid;
	}

	public Integer getCrceyUnid( ) {
		return crceyUnid;
	}

	public void setCrceyUuid(String crceyUuid) {
		this.crceyUuid = crceyUuid;
	}

	public String getCrceyUuid( ) {
		return crceyUuid;
	}

	public void setCrceyName(String crceyName) {
		this.crceyName = crceyName;
	}

	public String getCrceyName( ) {
		return crceyName;
	}

	public CoreCategory( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}