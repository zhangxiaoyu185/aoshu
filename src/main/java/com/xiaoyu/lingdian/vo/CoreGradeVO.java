package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 班级表
*/
public class CoreGradeVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crgaeUuid;

	/**
	*班级名
	*/
	private String crgaeName;

	/**
	*开通年级UUID集合，逗号隔开
	*/
	private String crgaeClasss;

	/**
	*开通年级名称集合
	*/
	private String crgaeClasssName;
	
	public void setCrgaeUuid(String crgaeUuid) {
		this.crgaeUuid = crgaeUuid;
	}

	public String getCrgaeUuid( ) {
		return crgaeUuid;
	}

	public void setCrgaeName(String crgaeName) {
		this.crgaeName = crgaeName;
	}

	public String getCrgaeName( ) {
		return crgaeName;
	}

	public void setCrgaeClasss(String crgaeClasss) {
		this.crgaeClasss = crgaeClasss;
	}

	public String getCrgaeClasss( ) {
		return crgaeClasss;
	}

	public String getCrgaeClasssName() {
		return crgaeClasssName;
	}

	public void setCrgaeClasssName(String crgaeClasssName) {
		this.crgaeClasssName = crgaeClasssName;
	}

	public CoreGradeVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreGrade po = (CoreGrade) poObj;
		this.crgaeUuid = po.getCrgaeUuid();
		this.crgaeName = po.getCrgaeName();
		this.crgaeClasss = po.getCrgaeClasss();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}