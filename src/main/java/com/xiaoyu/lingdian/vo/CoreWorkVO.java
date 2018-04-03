package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreWork;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 强化训练表
*/
public class CoreWorkVO implements BaseVO {

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
	*所属年级
	*/
	private String crwokClassName;

	/**
	*创建时间
	*/
	private String crwokCdate;

	/**
	*修改时间
	*/
	private String crwokUdate;

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

	public String getCrwokClassName() {
		return crwokClassName;
	}

	public void setCrwokClassName(String crwokClassName) {
		this.crwokClassName = crwokClassName;
	}

	public void setCrwokCdate(String crwokCdate) {
		this.crwokCdate = crwokCdate;
	}

	public String getCrwokCdate( ) {
		return crwokCdate;
	}

	public void setCrwokUdate(String crwokUdate) {
		this.crwokUdate = crwokUdate;
	}

	public String getCrwokUdate( ) {
		return crwokUdate;
	}

	public CoreWorkVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreWork po = (CoreWork) poObj;
		this.crwokUuid = po.getCrwokUuid();
		this.crwokCourse = po.getCrwokCourse();
		this.crwokCourseName = po.getCrwokCourseName();
		this.crwokContent = po.getCrwokContent();
		this.crwokClass = po.getCrwokClass();
		this.crwokCdate = po.getCrwokCdate()!=null?DateUtil.formatTimesTampDate(po.getCrwokCdate()):"";
		this.crwokUdate = po.getCrwokUdate()!=null?DateUtil.formatTimesTampDate(po.getCrwokUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}