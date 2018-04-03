package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreWork;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 强化训练表
*/
public interface CoreWorkService {

	/**
	* 查询
	* @param coreWork
	* @return
	*/
	public CoreWork getCoreWorkByCourse(CoreWork coreWork);
	
	/**
	* 删除
	* @param coreWork
	* @return
	*/
	public boolean deleteCoreWorkByCourse(CoreWork coreWork);
	
	/**
	* 修改
	* @param coreWork
	* @return
	*/
	public boolean updateCoreWorkByCourse(CoreWork coreWork);
	
	/**
	* 添加
	* @param coreWork
	* @return
	*/
	public boolean insertCoreWork(CoreWork coreWork);

	/**
	* 修改
	* @param coreWork
	* @return
	*/
	public boolean updateCoreWork(CoreWork coreWork);

	/**
	* 删除
	* @param coreWork
	* @return
	*/
	public boolean deleteCoreWork(CoreWork coreWork);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreWork
	* @return
	*/
	public CoreWork getCoreWork(CoreWork coreWork);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreWork> findCoreWorkList(String crwokCourse,String crwokClass);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreWork> findCoreWorkPage(String crwokCourse,String crwokClass, int pageNum, int pageSize);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreWork> findCoreWorkForListByFilters(List<String> courseList);
//<=================定制内容开始==============
//==================定制内容结束==============>

}