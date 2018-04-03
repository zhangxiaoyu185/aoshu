package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CorePapersAnswerOther;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 临时试卷回答表
*/
public class CorePapersAnswerOtherVO implements BaseVO {

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
	*所属年级名称
	*/
	private String crpsaClassName;

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
	private String crpsaCdate;

	/**
	*修改时间
	*/
	private String crpsaUdate;
	
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

	public String getCrpsaClassName() {
		return crpsaClassName;
	}

	public void setCrpsaClassName(String crpsaClassName) {
		this.crpsaClassName = crpsaClassName;
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

	public String getCrpsaCdate() {
		return crpsaCdate;
	}

	public void setCrpsaCdate(String crpsaCdate) {
		this.crpsaCdate = crpsaCdate;
	}

	public String getCrpsaUdate() {
		return crpsaUdate;
	}

	public void setCrpsaUdate(String crpsaUdate) {
		this.crpsaUdate = crpsaUdate;
	}

	public CorePapersAnswerOtherVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CorePapersAnswerOther po = (CorePapersAnswerOther) poObj;
		this.crpsaUuid = po.getCrpsaUuid();
		this.crpsaPapersUuid = po.getCrpsaPapersUuid();
		this.crpsaPapersName = po.getCrpsaPapersName();
		this.crpsaUser = po.getCrpsaUser();
		this.crpsaUserName = po.getCrpsaUserName();
		this.crpsaUserCode = po.getCrpsaUserCode();
		this.crpsaContent = po.getCrpsaContent();
		this.crpsaClass = po.getCrpsaClass();
		this.crpsaScore = po.getCrpsaScore();
		this.crpsaTime = po.getCrpsaTime();
		this.crpsaState = po.getCrpsaState();
		this.crpsaCdate = po.getCrpsaCdate()!=null?DateUtil.formatTimesTampDate(po.getCrpsaCdate()):"";
		this.crpsaUdate = po.getCrpsaUdate()!=null?DateUtil.formatTimesTampDate(po.getCrpsaUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}