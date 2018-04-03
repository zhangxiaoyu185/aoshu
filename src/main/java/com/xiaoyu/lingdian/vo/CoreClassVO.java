package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreClass;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 年级表
*/
public class CoreClassVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crcasUuid;

	/**
	*年级名称
	*/
	private String crcasName;

	public void setCrcasUuid(String crcasUuid) {
		this.crcasUuid = crcasUuid;
	}

	public String getCrcasUuid( ) {
		return crcasUuid;
	}

	public void setCrcasName(String crcasName) {
		this.crcasName = crcasName;
	}

	public String getCrcasName( ) {
		return crcasName;
	}

	public CoreClassVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreClass po = (CoreClass) poObj;
		this.crcasUuid = po.getCrcasUuid();
		this.crcasName = po.getCrcasName();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}