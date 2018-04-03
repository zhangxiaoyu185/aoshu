package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreKnowledge;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 知识点表
*/
public class CoreKnowledgeVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crkleUuid;

	/**
	*知识点名称
	*/
	private String crkleName;

	/**
	*所属大知识点
	*/
	private String crkleTop;
	
	/**
	*所属大知识点
	*/
	private String crkleFatherName;
	
	/**
	*所属类别
	*/
	private String crkleCategory;
	
	/**
	*所属类别名称
	*/
	private String crceyName;
	
	public void setCrkleUuid(String crkleUuid) {
		this.crkleUuid = crkleUuid;
	}

	public String getCrkleUuid( ) {
		return crkleUuid;
	}

	public void setCrkleName(String crkleName) {
		this.crkleName = crkleName;
	}

	public String getCrkleName( ) {
		return crkleName;
	}

	public void setCrkleTop(String crkleTop) {
		this.crkleTop = crkleTop;
	}

	public String getCrkleTop( ) {
		return crkleTop;
	}

	public String getCrkleFatherName() {
		return crkleFatherName;
	}

	public void setCrkleFatherName(String crkleFatherName) {
		this.crkleFatherName = crkleFatherName;
	}

	public String getCrkleCategory() {
		return crkleCategory;
	}

	public void setCrkleCategory(String crkleCategory) {
		this.crkleCategory = crkleCategory;
	}

	public String getCrceyName() {
		return crceyName;
	}

	public void setCrceyName(String crceyName) {
		this.crceyName = crceyName;
	}

	public CoreKnowledgeVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreKnowledge po = (CoreKnowledge) poObj;
		this.crkleUuid = po.getCrkleUuid();
		this.crkleName = po.getCrkleName();
		this.crkleTop = po.getCrkleTop();
		this.crkleCategory = po.getCrkleCategory();
		this.crceyName = po.getCrceyName();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}