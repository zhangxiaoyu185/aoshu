package com.xiaoyu.lingdian.entity;

import java.util.Date;
import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 练习回答表
*/
public class CoreExercisesAnswer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer cresaUnid;

	/**
	*标识UUID
	*/
	private String cresaUuid;

	/**
	*练习UUID
	*/
	private String cresaExercUuid;

	/**
	*课程名称
	*/
	private String cresaCourseName;

	/**
	*用户UUID
	*/
	private String cresaUser;
	
	/**
	*用户账号
	*/
	private String cresaUserName;

	/**
	*用户姓名
	*/
	private String cresaUserCode;
	
	/**
	*回答内容
	*/
	private String cresaContent;

	/**
	*所属年级
	*/
	private String cresaClass;
	
	/**
	*所属年级
	*/
	private String cresaClassName;

	/**
	*状态：0已完结/1需订正
	*/
	private Integer cresaState;

	/**
	*创建时间
	*/
	private Date cresaCdate;

	/**
	*修改时间
	*/
	private Date cresaUdate;
	
	public void setCresaUnid(Integer cresaUnid) {
		this.cresaUnid = cresaUnid;
	}

	public Integer getCresaUnid( ) {
		return cresaUnid;
	}

	public void setCresaUuid(String cresaUuid) {
		this.cresaUuid = cresaUuid;
	}

	public String getCresaUuid( ) {
		return cresaUuid;
	}

	public void setCresaExercUuid(String cresaExercUuid) {
		this.cresaExercUuid = cresaExercUuid;
	}

	public String getCresaExercUuid( ) {
		return cresaExercUuid;
	}

	public void setCresaCourseName(String cresaCourseName) {
		this.cresaCourseName = cresaCourseName;
	}

	public String getCresaCourseName( ) {
		return cresaCourseName;
	}

	public void setCresaUser(String cresaUser) {
		this.cresaUser = cresaUser;
	}

	public String getCresaUser( ) {
		return cresaUser;
	}

	public void setCresaContent(String cresaContent) {
		this.cresaContent = cresaContent;
	}

	public String getCresaContent( ) {
		return cresaContent;
	}

	public void setCresaClass(String cresaClass) {
		this.cresaClass = cresaClass;
	}

	public String getCresaClass( ) {
		return cresaClass;
	}

	public void setCresaState(Integer cresaState) {
		this.cresaState = cresaState;
	}

	public Integer getCresaState( ) {
		return cresaState;
	}

	public String getCresaUserName() {
		return cresaUserName;
	}

	public void setCresaUserName(String cresaUserName) {
		this.cresaUserName = cresaUserName;
	}

	public String getCresaClassName() {
		return cresaClassName;
	}

	public void setCresaClassName(String cresaClassName) {
		this.cresaClassName = cresaClassName;
	}

	public Date getCresaCdate() {
		return cresaCdate;
	}

	public void setCresaCdate(Date cresaCdate) {
		this.cresaCdate = cresaCdate;
	}

	public Date getCresaUdate() {
		return cresaUdate;
	}

	public void setCresaUdate(Date cresaUdate) {
		this.cresaUdate = cresaUdate;
	}

	public String getCresaUserCode() {
		return cresaUserCode;
	}

	public void setCresaUserCode(String cresaUserCode) {
		this.cresaUserCode = cresaUserCode;
	}

	public CoreExercisesAnswer( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}