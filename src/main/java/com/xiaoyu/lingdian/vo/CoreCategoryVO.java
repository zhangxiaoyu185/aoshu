package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreCategory;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 类别表
*/
public class CoreCategoryVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crceyUuid;

	/**
	*类别名称
	*/
	private String crceyName;

	public void setCrceyUuid(String crceyUuid) {
		this.crceyUuid = crceyUuid;
	}

	public String getCrceyUuid( ) {
		return crceyUuid;
	}

	public void setCrceyName(String crceyName) {
		this.crceyName = crceyName;
	}

	public String getCrceyName( ) {
		return crceyName;
	}

	public CoreCategoryVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreCategory po = (CoreCategory) poObj;
		this.crceyUuid = po.getCrceyUuid();
		this.crceyName = po.getCrceyName();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}