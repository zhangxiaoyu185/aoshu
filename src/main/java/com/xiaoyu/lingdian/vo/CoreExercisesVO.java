package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreExercises;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 经典练习表
*/
public class CoreExercisesVO implements BaseVO {

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
	*所属年级
	*/
	private String crecsClassName;

	/**
	*创建时间
	*/
	private String crecsCdate;

	/**
	*修改时间
	*/
	private String crecsUdate;

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

	public String getCrecsClassName() {
		return crecsClassName;
	}

	public void setCrecsClassName(String crecsClassName) {
		this.crecsClassName = crecsClassName;
	}

	public void setCrecsCdate(String crecsCdate) {
		this.crecsCdate = crecsCdate;
	}

	public String getCrecsCdate( ) {
		return crecsCdate;
	}

	public void setCrecsUdate(String crecsUdate) {
		this.crecsUdate = crecsUdate;
	}

	public String getCrecsUdate( ) {
		return crecsUdate;
	}

	public CoreExercisesVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreExercises po = (CoreExercises) poObj;
		this.crecsUuid = po.getCrecsUuid();
		this.crecsCourse = po.getCrecsCourse();
		this.crecsCourseName = po.getCrecsCourseName();
		this.crecsContent = po.getCrecsContent();
		this.crecsClass = po.getCrecsClass();
		this.crecsCdate = po.getCrecsCdate()!=null?DateUtil.formatTimesTampDate(po.getCrecsCdate()):"";
		this.crecsUdate = po.getCrecsUdate()!=null?DateUtil.formatTimesTampDate(po.getCrecsUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}