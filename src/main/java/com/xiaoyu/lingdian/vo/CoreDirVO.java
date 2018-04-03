package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreDir;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 图片目录表
*/
public class CoreDirVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crdirUuid;

	/**
	*目录名称
	*/
	private String crdirName;

	public void setCrdirUuid(String crdirUuid) {
		this.crdirUuid = crdirUuid;
	}

	public String getCrdirUuid( ) {
		return crdirUuid;
	}

	public void setCrdirName(String crdirName) {
		this.crdirName = crdirName;
	}

	public String getCrdirName( ) {
		return crdirName;
	}

	public CoreDirVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreDir po = (CoreDir) poObj;
		this.crdirUuid = po.getCrdirUuid();
		this.crdirName = po.getCrdirName();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}