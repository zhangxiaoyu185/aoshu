package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreCategory;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 类别表
*/
public interface CoreCategoryService {

	/**
	* 添加
	* @param coreCategory
	* @return
	*/
	public boolean insertCoreCategory(CoreCategory coreCategory);

	/**
	* 修改
	* @param coreCategory
	* @return
	*/
	public boolean updateCoreCategory(CoreCategory coreCategory);

	/**
	* 删除
	* @param coreCategory
	* @return
	*/
	public boolean deleteCoreCategory(CoreCategory coreCategory);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreCategory
	* @return
	*/
	public CoreCategory getCoreCategory(CoreCategory coreCategory);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreCategory> findCoreCategoryList();

	/**
	* 查询所有Page
	* @param crceyName 类别名称
	* @return Page
	*/
	public Page<CoreCategory> findCoreCategoryPage(String crceyName, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}