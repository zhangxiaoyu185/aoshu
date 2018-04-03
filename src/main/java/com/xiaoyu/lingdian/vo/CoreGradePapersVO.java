package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreGradePapers;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 班级模拟试卷表
*/
public class CoreGradePapersVO implements BaseVO {

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
	*所属年级名称
	*/
	private String crpesClassName;

	/**
	*是否测验过
	*/
	private String isTest;
	
	/**
	*测验UUID
	*/
	private String answerUuid;
	
	/**
	*分数
	*/
	private String score;
	
	/**
	*用时
	*/
	private String time;
	
	/**
	*上次回答时间
	*/
	private String answerDate;
	
	/**
	*过期时间
	*/
	private String crgpsGqsj;
	
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

	public String getCrpesClassName() {
		return crpesClassName;
	}

	public void setCrpesClassName(String crpesClassName) {
		this.crpesClassName = crpesClassName;
	}

	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}

	public String getAnswerUuid() {
		return answerUuid;
	}

	public void setAnswerUuid(String answerUuid) {
		this.answerUuid = answerUuid;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCrpesUuid() {
		return crpesUuid;
	}

	public void setCrpesUuid(String crpesUuid) {
		this.crpesUuid = crpesUuid;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

	public String getCrgpsGqsj() {
		return crgpsGqsj;
	}

	public void setCrgpsGqsj(String crgpsGqsj) {
		this.crgpsGqsj = crgpsGqsj;
	}

	public CoreGradePapersVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreGradePapers po = (CoreGradePapers) poObj;
		this.crgpsUuid = po.getCrgpsUuid();
		this.crgpsGrade = po.getCrgpsGrade();
		this.crgpsPapers = po.getCrgpsPapers();
		this.crgpsOrd = po.getCrgpsOrd();
		this.crpesName = po.getCrpesName();
		this.crpesContent = po.getCrpesContent();
		this.crpesScore = po.getCrpesScore();
		this.crpesClass = po.getCrpesClass();
		this.crpesUuid = po.getCrpesUuid();
		this.crgpsGqsj = po.getCrgpsGqsj();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}