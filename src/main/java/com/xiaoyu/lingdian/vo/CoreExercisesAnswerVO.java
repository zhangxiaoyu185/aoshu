package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreExercisesAnswer;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 练习回答表
*/
public class CoreExercisesAnswerVO implements BaseVO {

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
	private String cresaCdate;

	/**
	*修改时间
	*/
	private String cresaUdate;
	
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

	public String getCresaCdate() {
		return cresaCdate;
	}

	public void setCresaCdate(String cresaCdate) {
		this.cresaCdate = cresaCdate;
	}

	public String getCresaUdate() {
		return cresaUdate;
	}

	public void setCresaUdate(String cresaUdate) {
		this.cresaUdate = cresaUdate;
	}

	public String getCresaUserCode() {
		return cresaUserCode;
	}

	public void setCresaUserCode(String cresaUserCode) {
		this.cresaUserCode = cresaUserCode;
	}

	public CoreExercisesAnswerVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreExercisesAnswer po = (CoreExercisesAnswer) poObj;
		this.cresaUuid = po.getCresaUuid();
		this.cresaExercUuid = po.getCresaExercUuid();
		this.cresaCourseName = po.getCresaCourseName();
		this.cresaUser = po.getCresaUser();
		this.cresaUserName = po.getCresaUserName();
		this.cresaUserCode = po.getCresaUserCode();
		this.cresaContent = po.getCresaContent();
		this.cresaClass = po.getCresaClass();
		this.cresaClassName = po.getCresaClassName();
		this.cresaState = po.getCresaState();
		this.cresaCdate = po.getCresaCdate()!=null?DateUtil.formatTimesTampDate(po.getCresaCdate()):"";
		this.cresaUdate = po.getCresaUdate()!=null?DateUtil.formatTimesTampDate(po.getCresaUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}