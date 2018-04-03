package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 模拟试卷表
*/
public class CorePapers extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crpesUnid;

	/**
	*标识UUID
	*/
	private String crpesUuid;

	/**
	*试卷名
	*/
	private String crpesName;

	/**
	*内容
	*/
	private String crpesContent;

	/**
	*分数
	*/
	private Integer crpesScore;

	/**
	*所属年级
	*/
	private String crpesClass;

	/**
	*创建时间
	*/
	private Date crpesCdate;

	/**
	*修改时间
	*/
	private Date crpesUdate;

	public void setCrpesUnid(Integer crpesUnid) {
		this.crpesUnid = crpesUnid;
	}

	public Integer getCrpesUnid( ) {
		return crpesUnid;
	}

	public void setCrpesUuid(String crpesUuid) {
		this.crpesUuid = crpesUuid;
	}

	public String getCrpesUuid( ) {
		return crpesUuid;
	}

	public void setCrpesName(String crpesName) {
		this.crpesName = crpesName;
	}

	public String getCrpesName( ) {
		return crpesName;
	}

	public void setCrpesContent(String crpesContent) {
		this.crpesContent = crpesContent;
	}

	public String getCrpesContent( ) {
		return crpesContent;
	}

	public void setCrpesScore(Integer crpesScore) {
		this.crpesScore = crpesScore;
	}

	public Integer getCrpesScore( ) {
		return crpesScore;
	}

	public void setCrpesClass(String crpesClass) {
		this.crpesClass = crpesClass;
	}

	public String getCrpesClass( ) {
		return crpesClass;
	}

	public void setCrpesCdate(Date crpesCdate) {
		this.crpesCdate = crpesCdate;
	}

	public Date getCrpesCdate( ) {
		return crpesCdate;
	}

	public void setCrpesUdate(Date crpesUdate) {
		this.crpesUdate = crpesUdate;
	}

	public Date getCrpesUdate( ) {
		return crpesUdate;
	}

	public CorePapers( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}