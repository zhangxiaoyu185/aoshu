package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 强化训练表
*/
public class CoreWork extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crwokUnid;

	/**
	*标识UUID
	*/
	private String crwokUuid;

	/**
	*所属课程
	*/
	private String crwokCourse;

	/**
	*所属课程名
	*/
	private String crwokCourseName;

	/**
	*内容
	*/
	private String crwokContent;

	/**
	*所属年级
	*/
	private String crwokClass;

	/**
	*创建时间
	*/
	private Date crwokCdate;

	/**
	*修改时间
	*/
	private Date crwokUdate;

	public void setCrwokUnid(Integer crwokUnid) {
		this.crwokUnid = crwokUnid;
	}

	public Integer getCrwokUnid( ) {
		return crwokUnid;
	}

	public void setCrwokUuid(String crwokUuid) {
		this.crwokUuid = crwokUuid;
	}

	public String getCrwokUuid( ) {
		return crwokUuid;
	}

	public void setCrwokCourse(String crwokCourse) {
		this.crwokCourse = crwokCourse;
	}

	public String getCrwokCourse( ) {
		return crwokCourse;
	}

	public void setCrwokCourseName(String crwokCourseName) {
		this.crwokCourseName = crwokCourseName;
	}

	public String getCrwokCourseName( ) {
		return crwokCourseName;
	}

	public void setCrwokContent(String crwokContent) {
		this.crwokContent = crwokContent;
	}

	public String getCrwokContent( ) {
		return crwokContent;
	}

	public void setCrwokClass(String crwokClass) {
		this.crwokClass = crwokClass;
	}

	public String getCrwokClass( ) {
		return crwokClass;
	}

	public void setCrwokCdate(Date crwokCdate) {
		this.crwokCdate = crwokCdate;
	}

	public Date getCrwokCdate( ) {
		return crwokCdate;
	}

	public void setCrwokUdate(Date crwokUdate) {
		this.crwokUdate = crwokUdate;
	}

	public Date getCrwokUdate( ) {
		return crwokUdate;
	}

	public CoreWork( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}