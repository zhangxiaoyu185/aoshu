package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 班级模拟试卷表
*/
public class CoreGradePapers extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crgpsUnid;

	/**
	*标识UUID
	*/
	private String crgpsUuid;

	/**
	*班级UUID
	*/
	private String crgpsGrade;

	/**
	*试卷UUID
	*/
	private String crgpsPapers;

	/**
	*试卷UUID
	*/
	private String crpesUuid;
	
	/**
	*排序号
	*/
	private Integer crgpsOrd;

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
	*过期时间
	*/
	private String crgpsGqsj;
	
	public void setCrgpsUnid(Integer crgpsUnid) {
		this.crgpsUnid = crgpsUnid;
	}

	public Integer getCrgpsUnid( ) {
		return crgpsUnid;
	}

	public void setCrgpsUuid(String crgpsUuid) {
		this.crgpsUuid = crgpsUuid;
	}

	public String getCrgpsUuid( ) {
		return crgpsUuid;
	}

	public void setCrgpsGrade(String crgpsGrade) {
		this.crgpsGrade = crgpsGrade;
	}

	public String getCrgpsGrade( ) {
		return crgpsGrade;
	}

	public void setCrgpsPapers(String crgpsPapers) {
		this.crgpsPapers = crgpsPapers;
	}

	public String getCrgpsPapers( ) {
		return crgpsPapers;
	}

	public void setCrgpsOrd(Integer crgpsOrd) {
		this.crgpsOrd = crgpsOrd;
	}

	public Integer getCrgpsOrd( ) {
		return crgpsOrd;
	}

	public String getCrpesName() {
		return crpesName;
	}

	public void setCrpesName(String crpesName) {
		this.crpesName = crpesName;
	}

	public String getCrpesContent() {
		return crpesContent;
	}

	public void setCrpesContent(String crpesContent) {
		this.crpesContent = crpesContent;
	}

	public Integer getCrpesScore() {
		return crpesScore;
	}

	public void setCrpesScore(Integer crpesScore) {
		this.crpesScore = crpesScore;
	}

	public String getCrpesClass() {
		return crpesClass;
	}

	public void setCrpesClass(String crpesClass) {
		this.crpesClass = crpesClass;
	}

	public String getCrpesUuid() {
		return crpesUuid;
	}

	public void setCrpesUuid(String crpesUuid) {
		this.crpesUuid = crpesUuid;
	}

	public String getCrgpsGqsj() {
		return crgpsGqsj;
	}

	public void setCrgpsGqsj(String crgpsGqsj) {
		this.crgpsGqsj = crgpsGqsj;
	}

	public CoreGradePapers( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}