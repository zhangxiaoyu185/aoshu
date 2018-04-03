package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 班级课程表
*/
public class CoreGradeCourse extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crgceUnid;

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
	*排序号
	*/
	private Integer crgceOrd;

	/**
	*过期时间
	*/
	private String crgceGqsj;
	
	public void setCrgceUnid(Integer crgceUnid) {
		this.crgceUnid = crgceUnid;
	}

	public Integer getCrgceUnid( ) {
		return crgceUnid;
	}

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

	public String getCrgceGqsj() {
		return crgceGqsj;
	}

	public void setCrgceGqsj(String crgceGqsj) {
		this.crgceGqsj = crgceGqsj;
	}

	public CoreGradeCourse( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}