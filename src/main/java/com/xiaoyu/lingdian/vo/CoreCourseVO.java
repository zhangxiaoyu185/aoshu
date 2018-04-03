package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreCourse;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 课程表
*/
public class CoreCourseVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crcreUuid;

	/**
	*课程名
	*/
	private String crcreName;

	/**
	*课程内容
	*/
	private String crcreContent;
	
	/**
	*所属年级
	*/
	private String crcreClass;
	
	/**
	*所属年级名称
	*/
	private String crcreClassName;
	
	/**
	*经典练习UUID
	*/
	private String crecsUuid;
	
	/**
	*强化训练UUID
	*/
	private String crwokUuid;
	
	/**
	*强化训练分数
	*/
	private Integer crwokScore;
	
	/**
	*是否可点击强化训练
	*/
	private String isWork;
	
	public void setCrcreUuid(String crcreUuid) {
		this.crcreUuid = crcreUuid;
	}

	public String getCrcreUuid( ) {
		return crcreUuid;
	}

	public void setCrcreName(String crcreName) {
		this.crcreName = crcreName;
	}

	public String getCrcreName( ) {
		return crcreName;
	}

	public String getCrcreContent() {
		return crcreContent;
	}

	public void setCrcreContent(String crcreContent) {
		this.crcreContent = crcreContent;
	}

	public void setCrcreClass(String crcreClass) {
		this.crcreClass = crcreClass;
	}

	public String getCrcreClass( ) {
		return crcreClass;
	}

	public String getCrcreClassName() {
		return crcreClassName;
	}

	public void setCrcreClassName(String crcreClassName) {
		this.crcreClassName = crcreClassName;
	}

	public String getCrecsUuid() {
		return crecsUuid;
	}

	public void setCrecsUuid(String crecsUuid) {
		this.crecsUuid = crecsUuid;
	}

	public String getCrwokUuid() {
		return crwokUuid;
	}

	public void setCrwokUuid(String crwokUuid) {
		this.crwokUuid = crwokUuid;
	}

	public Integer getCrwokScore() {
		return crwokScore;
	}

	public void setCrwokScore(Integer crwokScore) {
		this.crwokScore = crwokScore;
	}

	public String getIsWork() {
		return isWork;
	}

	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}

	public CoreCourseVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreCourse po = (CoreCourse) poObj;
		this.crcreUuid = po.getCrcreUuid();
		this.crcreName = po.getCrcreName();
		this.crcreContent = po.getCrcreContent();
		this.crcreClass = po.getCrcreClass();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}