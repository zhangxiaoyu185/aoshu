package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 经典练习表
*/
public class CoreExercises extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crecsUnid;

	/**
	*标识UUID
	*/
	private String crecsUuid;

	/**
	*所属课程
	*/
	private String crecsCourse;

	/**
	*所属课程名
	*/
	private String crecsCourseName;

	/**
	*内容
	*/
	private String crecsContent;

	/**
	*所属年级
	*/
	private String crecsClass;

	/**
	*创建时间
	*/
	private Date crecsCdate;

	/**
	*修改时间
	*/
	private Date crecsUdate;

	public void setCrecsUnid(Integer crecsUnid) {
		this.crecsUnid = crecsUnid;
	}

	public Integer getCrecsUnid( ) {
		return crecsUnid;
	}

	public void setCrecsUuid(String crecsUuid) {
		this.crecsUuid = crecsUuid;
	}

	public String getCrecsUuid( ) {
		return crecsUuid;
	}

	public void setCrecsCourse(String crecsCourse) {
		this.crecsCourse = crecsCourse;
	}

	public String getCrecsCourse( ) {
		return crecsCourse;
	}

	public void setCrecsCourseName(String crecsCourseName) {
		this.crecsCourseName = crecsCourseName;
	}

	public String getCrecsCourseName( ) {
		return crecsCourseName;
	}

	public void setCrecsContent(String crecsContent) {
		this.crecsContent = crecsContent;
	}

	public String getCrecsContent( ) {
		return crecsContent;
	}

	public void setCrecsClass(String crecsClass) {
		this.crecsClass = crecsClass;
	}

	public String getCrecsClass( ) {
		return crecsClass;
	}

	public void setCrecsCdate(Date crecsCdate) {
		this.crecsCdate = crecsCdate;
	}

	public Date getCrecsCdate( ) {
		return crecsCdate;
	}

	public void setCrecsUdate(Date crecsUdate) {
		this.crecsUdate = crecsUdate;
	}

	public Date getCrecsUdate( ) {
		return crecsUdate;
	}

	public CoreExercises( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}