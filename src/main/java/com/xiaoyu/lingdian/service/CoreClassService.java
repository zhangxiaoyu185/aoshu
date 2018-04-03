package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreClass;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 年级表
*/
public interface CoreClassService {

	/**
	* 查询ids对应名称年级
	* @param ids
	* @return
	*/
	public List<CoreClass> findCoreClassByIds(List<String> ids);
	
	/**
	* 添加
	* @param coreClass
	* @return
	*/
	public boolean insertCoreClass(CoreClass coreClass);

	/**
	* 修改
	* @param coreClass
	* @return
	*/
	public boolean updateCoreClass(CoreClass coreClass);

	/**
	* 删除
	* @param coreClass
	* @return
	*/
	public boolean deleteCoreClass(CoreClass coreClass);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreClass
	* @return
	*/
	public CoreClass getCoreClass(CoreClass coreClass);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreClass> findCoreClassList();

	/**
	* 查询所有List,不存在无
	* @return List
	*/
	public List<CoreClass> findCoreClassForListsNothing();
	
	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreClass> findCoreClassPage(String crcasName, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}