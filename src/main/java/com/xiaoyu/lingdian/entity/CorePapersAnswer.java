package com.xiaoyu.lingdian.entity;

import java.util.Date;
import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 试卷回答表
*/
public class CorePapersAnswer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crpsaUnid;

	/**
	*标识UUID
	*/
	private String crpsaUuid;

	/**
	*模拟试卷UUID
	*/
	private String crpsaPapersUuid;

	/**
	*模拟试卷名称
	*/
	private String crpsaPapersName;

	/**
	*用户UUID
	*/
	private String crpsaUser;

	/**
	*用户账号
	*/
	private String crpsaUserName;
	
	/**
	*用户姓名
	*/
	private String crpsaUserCode;
	
	/**
	*回答内容
	*/
	private String crpsaContent;

	/**
	*所属年级
	*/
	private String crpsaClass;

	/**
	*得分
	*/
	private Integer crpsaScore;

	/**
	*时间
	*/
	private String crpsaTime;
	
	/**
	*状态：0已完结/1需订正
	*/
	private Integer crpsaState;

	/**
	*创建时间
	*/
	private Date crpsaCdate;

	/**
	*修改时间
	*/
	private Date crpsaUdate;
	
	public void setCrpsaUnid(Integer crpsaUnid) {
		this.crpsaUnid = crpsaUnid;
	}

	public Integer getCrpsaUnid( ) {
		return crpsaUnid;
	}

	public void setCrpsaUuid(String crpsaUuid) {
		this.crpsaUuid = crpsaUuid;
	}

	public String getCrpsaUuid( ) {
		return crpsaUuid;
	}

	public void setCrpsaPapersUuid(String crpsaPapersUuid) {
		this.crpsaPapersUuid = crpsaPapersUuid;
	}

	public String getCrpsaPapersUuid( ) {
		return crpsaPapersUuid;
	}

	public void setCrpsaPapersName(String crpsaPapersName) {
		this.crpsaPapersName = crpsaPapersName;
	}

	public String getCrpsaPapersName( ) {
		return crpsaPapersName;
	}

	public void setCrpsaUser(String crpsaUser) {
		this.crpsaUser = crpsaUser;
	}

	public String getCrpsaUser( ) {
		return crpsaUser;
	}

	public String getCrpsaUserName() {
		return crpsaUserName;
	}

	public void setCrpsaUserName(String crpsaUserName) {
		this.crpsaUserName = crpsaUserName;
	}

	public String getCrpsaUserCode() {
		return crpsaUserCode;
	}

	public void setCrpsaUserCode(String crpsaUserCode) {
		this.crpsaUserCode = crpsaUserCode;
	}

	public void setCrpsaContent(String crpsaContent) {
		this.crpsaContent = crpsaContent;
	}

	public String getCrpsaContent( ) {
		return crpsaContent;
	}

	public void setCrpsaClass(String crpsaClass) {
		this.crpsaClass = crpsaClass;
	}

	public String getCrpsaClass( ) {
		return crpsaClass;
	}

	public void setCrpsaScore(Integer crpsaScore) {
		this.crpsaScore = crpsaScore;
	}

	public Integer getCrpsaScore( ) {
		return crpsaScore;
	}

	public String getCrpsaTime() {
		return crpsaTime;
	}

	public void setCrpsaTime(String crpsaTime) {
		this.crpsaTime = crpsaTime;
	}

	public void setCrpsaState(Integer crpsaState) {
		this.crpsaState = crpsaState;
	}

	public Integer getCrpsaState( ) {
		return crpsaState;
	}

	public Date getCrpsaCdate() {
		return crpsaCdate;
	}

	public void setCrpsaCdate(Date crpsaCdate) {
		this.crpsaCdate = crpsaCdate;
	}

	public Date getCrpsaUdate() {
		return crpsaUdate;
	}

	public void setCrpsaUdate(Date crpsaUdate) {
		this.crpsaUdate = crpsaUdate;
	}

	public CorePapersAnswer( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}