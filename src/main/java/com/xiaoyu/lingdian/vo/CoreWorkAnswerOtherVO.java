package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreWorkAnswerOther;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 训练回答表
*/
public class CoreWorkAnswerOtherVO implements BaseVO {

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
	*所属年级
	*/
	private String crwkaClassName;

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
	private String crwkaCdate;

	/**
	*修改时间
	*/
	private String crwkaUdate;

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

	public String getCrwkaClassName() {
		return crwkaClassName;
	}

	public void setCrwkaClassName(String crwkaClassName) {
		this.crwkaClassName = crwkaClassName;
	}

	public String getCrwkaCdate() {
		return crwkaCdate;
	}

	public void setCrwkaCdate(String crwkaCdate) {
		this.crwkaCdate = crwkaCdate;
	}

	public String getCrwkaUdate() {
		return crwkaUdate;
	}

	public void setCrwkaUdate(String crwkaUdate) {
		this.crwkaUdate = crwkaUdate;
	}

	public String getCrwkaUserCode() {
		return crwkaUserCode;
	}

	public void setCrwkaUserCode(String crwkaUserCode) {
		this.crwkaUserCode = crwkaUserCode;
	}

	public CoreWorkAnswerOtherVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreWorkAnswerOther po = (CoreWorkAnswerOther) poObj;
		this.crwkaUuid = po.getCrwkaUuid();
		this.crwkaWorkUuid = po.getCrwkaWorkUuid();
		this.crwkaCourseName = po.getCrwkaCourseName();
		this.crwkaUser = po.getCrwkaUser();
		this.crwkaUserName = po.getCrwkaUserName();
		this.crwkaUserCode = po.getCrwkaUserCode();
		this.crwkaContent = po.getCrwkaContent();
		this.crwkaClass = po.getCrwkaClass();
		this.crwkaScore = po.getCrwkaScore();
		this.crwkaTime = po.getCrwkaTime();
		this.crwkaState = po.getCrwkaState();
		this.crwkaCdate = po.getCrwkaCdate()!=null?DateUtil.formatTimesTampDate(po.getCrwkaCdate()):"";
		this.crwkaUdate = po.getCrwkaUdate()!=null?DateUtil.formatTimesTampDate(po.getCrwkaUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}