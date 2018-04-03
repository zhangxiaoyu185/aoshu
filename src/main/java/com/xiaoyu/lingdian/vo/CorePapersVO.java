package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CorePapers;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 模拟试卷表
*/
public class CorePapersVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crpesUuid;

	/**
	*试卷名
	*/
	private String crpesName;

	/**
	*内容
	*/
	private String crpesContent;

	/**
	*分数
	*/
	private Integer crpesScore;

	/**
	*所属年级
	*/
	private String crpesClass;
	
	/**
	*所属年级名称
	*/
	private String crpesClassName;

	/**
	*创建时间
	*/
	private String crpesCdate;

	/**
	*修改时间
	*/
	private String crpesUdate;
	
	public void setCrpesUuid(String crpesUuid) {
		this.crpesUuid = crpesUuid;
	}

	public String getCrpesUuid( ) {
		return crpesUuid;
	}

	public void setCrpesName(String crpesName) {
		this.crpesName = crpesName;
	}

	public String getCrpesName( ) {
		return crpesName;
	}

	public void setCrpesContent(String crpesContent) {
		this.crpesContent = crpesContent;
	}

	public String getCrpesContent( ) {
		return crpesContent;
	}

	public void setCrpesScore(Integer crpesScore) {
		this.crpesScore = crpesScore;
	}

	public Integer getCrpesScore( ) {
		return crpesScore;
	}

	public void setCrpesClass(String crpesClass) {
		this.crpesClass = crpesClass;
	}

	public String getCrpesClass( ) {
		return crpesClass;
	}

	public void setCrpesCdate(String crpesCdate) {
		this.crpesCdate = crpesCdate;
	}

	public String getCrpesCdate( ) {
		return crpesCdate;
	}

	public void setCrpesUdate(String crpesUdate) {
		this.crpesUdate = crpesUdate;
	}

	public String getCrpesUdate( ) {
		return crpesUdate;
	}

	public String getCrpesClassName() {
		return crpesClassName;
	}

	public void setCrpesClassName(String crpesClassName) {
		this.crpesClassName = crpesClassName;
	}

	public CorePapersVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CorePapers po = (CorePapers) poObj;
		this.crpesUuid = po.getCrpesUuid();
		this.crpesName = po.getCrpesName();
		this.crpesContent = po.getCrpesContent();
		this.crpesScore = po.getCrpesScore();
		this.crpesClass = po.getCrpesClass();
		this.crpesCdate = po.getCrpesCdate()!=null?DateUtil.formatTimesTampDate(po.getCrpesCdate()):"";
		this.crpesUdate = po.getCrpesUdate()!=null?DateUtil.formatTimesTampDate(po.getCrpesUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}