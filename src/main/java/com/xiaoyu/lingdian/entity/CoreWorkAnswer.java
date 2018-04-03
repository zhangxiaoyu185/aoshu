package com.xiaoyu.lingdian.entity;

import java.util.Date;
import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 训练回答表
*/
public class CoreWorkAnswer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crwkaUnid;

	/**
	*标识UUID
	*/
	private String crwkaUuid;

	/**
	*训练UUID
	*/
	private String crwkaWorkUuid;

	/**
	*课程名称
	*/
	private String crwkaCourseName;

	/**
	*用户UUID
	*/
	private String crwkaUser;
	
	/**
	*用户账号
	*/
	private String crwkaUserName;

	/**
	*用户姓名
	*/
	private String crwkaUserCode;
	
	/**
	*回答内容
	*/
	private String crwkaContent;

	/**
	*所属年级
	*/
	private String crwkaClass;

	/**
	*得分
	*/
	private Integer crwkaScore;

	/**
	*时间
	*/
	private String crwkaTime;

	/**
	*状态：0已完结/1需订正
	*/
	private Integer crwkaState;

	/**
	*创建时间
	*/
	private Date crwkaCdate;

	/**
	*修改时间
	*/
	private Date crwkaUdate;
	
	public void setCrwkaUnid(Integer crwkaUnid) {
		this.crwkaUnid = crwkaUnid;
	}

	public Integer getCrwkaUnid( ) {
		return crwkaUnid;
	}

	public void setCrwkaUuid(String crwkaUuid) {
		this.crwkaUuid = crwkaUuid;
	}

	public String getCrwkaUuid( ) {
		return crwkaUuid;
	}

	public void setCrwkaWorkUuid(String crwkaWorkUuid) {
		this.crwkaWorkUuid = crwkaWorkUuid;
	}

	public String getCrwkaWorkUuid( ) {
		return crwkaWorkUuid;
	}

	public void setCrwkaCourseName(String crwkaCourseName) {
		this.crwkaCourseName = crwkaCourseName;
	}

	public String getCrwkaCourseName( ) {
		return crwkaCourseName;
	}

	public void setCrwkaUser(String crwkaUser) {
		this.crwkaUser = crwkaUser;
	}

	public String getCrwkaUser( ) {
		return crwkaUser;
	}

	public void setCrwkaContent(String crwkaContent) {
		this.crwkaContent = crwkaContent;
	}

	public String getCrwkaContent( ) {
		return crwkaContent;
	}

	public void setCrwkaClass(String crwkaClass) {
		this.crwkaClass = crwkaClass;
	}

	public String getCrwkaClass( ) {
		return crwkaClass;
	}

	public void setCrwkaScore(Integer crwkaScore) {
		this.crwkaScore = crwkaScore;
	}

	public Integer getCrwkaScore( ) {
		return crwkaScore;
	}

	public void setCrwkaTime(String crwkaTime) {
		this.crwkaTime = crwkaTime;
	}

	public String getCrwkaTime( ) {
		return crwkaTime;
	}

	public void setCrwkaState(Integer crwkaState) {
		this.crwkaState = crwkaState;
	}

	public Integer getCrwkaState( ) {
		return crwkaState;
	}

	public String getCrwkaUserName() {
		return crwkaUserName;
	}

	public void setCrwkaUserName(String crwkaUserName) {
		this.crwkaUserName = crwkaUserName;
	}

	public Date getCrwkaCdate() {
		return crwkaCdate;
	}

	public void setCrwkaCdate(Date crwkaCdate) {
		this.crwkaCdate = crwkaCdate;
	}

	public Date getCrwkaUdate() {
		return crwkaUdate;
	}

	public void setCrwkaUdate(Date crwkaUdate) {
		this.crwkaUdate = crwkaUdate;
	}

	public String getCrwkaUserCode() {
		return crwkaUserCode;
	}

	public void setCrwkaUserCode(String crwkaUserCode) {
		this.crwkaUserCode = crwkaUserCode;
	}

	public CoreWorkAnswer( ) { 
	}
	
//<=================定制内容开始==============
//==================定制内容结束==============>

}