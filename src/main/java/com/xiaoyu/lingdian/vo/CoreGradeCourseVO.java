package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreGradeCourse;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 班级课程表
*/
public class CoreGradeCourseVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crgceUuid;

	/**
	*班级UUID
	*/
	private String crgceGrade;

	/**
	*课程UUID
	*/
	private String crgceCourse;

	/**
	*课程UUID
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
	
	/**
	*所属年级名称
	*/
	private String crcreClassName;
	
	/**
	*经典练习UUID
	*/
	private String crecsUuid;
	
	/**
	*强化训练UUID
	*/
	private String crwokUuid;
	
	/**
	*强化训练分数
	*/
	private Integer crwokScore;
	
	/**
	*是否可点击强化训练
	*/
	private String isWork;
	
	/**
	*排序号
	*/
	private Integer crgceOrd;
	
	/**
	*上次回答时间
	*/
	private String answerDate;

	/**
	*过期时间
	*/
	private String crgceGqsj;
	
	public void setCrgceUuid(String crgceUuid) {
		this.crgceUuid = crgceUuid;
	}

	public String getCrgceUuid( ) {
		return crgceUuid;
	}

	public void setCrgceGrade(String crgceGrade) {
		this.crgceGrade = crgceGrade;
	}

	public String getCrgceGrade( ) {
		return crgceGrade;
	}

	public void setCrgceCourse(String crgceCourse) {
		this.crgceCourse = crgceCourse;
	}

	public String getCrgceCourse( ) {
		return crgceCourse;
	}

	public void setCrgceOrd(Integer crgceOrd) {
		this.crgceOrd = crgceOrd;
	}

	public Integer getCrgceOrd( ) {
		return crgceOrd;
	}

	public String getCrcreUuid() {
		return crcreUuid;
	}

	public void setCrcreUuid(String crcreUuid) {
		this.crcreUuid = crcreUuid;
	}

	public String getCrcreName() {
		return crcreName;
	}

	public void setCrcreName(String crcreName) {
		this.crcreName = crcreName;
	}

	public String getCrcreContent() {
		return crcreContent;
	}

	public void setCrcreContent(String crcreContent) {
		this.crcreContent = crcreContent;
	}

	public String getCrcreClass() {
		return crcreClass;
	}

	public void setCrcreClass(String crcreClass) {
		this.crcreClass = crcreClass;
	}

	public String getCrcreClassName() {
		return crcreClassName;
	}

	public void setCrcreClassName(String crcreClassName) {
		this.crcreClassName = crcreClassName;
	}

	public String getCrecsUuid() {
		return crecsUuid;
	}

	public void setCrecsUuid(String crecsUuid) {
		this.crecsUuid = crecsUuid;
	}

	public String getCrwokUuid() {
		return crwokUuid;
	}

	public void setCrwokUuid(String crwokUuid) {
		this.crwokUuid = crwokUuid;
	}

	public Integer getCrwokScore() {
		return crwokScore;
	}

	public void setCrwokScore(Integer crwokScore) {
		this.crwokScore = crwokScore;
	}

	public String getIsWork() {
		return isWork;
	}

	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

	public String getCrgceGqsj() {
		return crgceGqsj;
	}

	public void setCrgceGqsj(String crgceGqsj) {
		this.crgceGqsj = crgceGqsj;
	}

	public CoreGradeCourseVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreGradeCourse po = (CoreGradeCourse) poObj;
		this.crgceUuid = po.getCrgceUuid();
		this.crgceGrade = po.getCrgceGrade();
		this.crgceCourse = po.getCrgceCourse();
		this.crgceOrd = po.getCrgceOrd();
		this.crcreUuid = po.getCrcreUuid();
		this.crcreName = po.getCrcreName();
		this.crcreContent = po.getCrcreContent();
		this.crcreClass = po.getCrcreClass();
		this.crgceGqsj = po.getCrgceGqsj();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}