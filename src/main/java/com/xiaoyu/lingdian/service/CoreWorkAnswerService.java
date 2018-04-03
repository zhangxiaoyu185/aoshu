package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreWorkAnswer;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 训练回答表
*/
public interface CoreWorkAnswerService {

	/**
	* 添加
	* @param coreWorkAnswer
	* @return
	*/
	public boolean insertCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer);

	/**
	* 修改
	* @param coreWorkAnswer
	* @return
	*/
	public boolean updateCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer);

	/**
	* 删除
	* @param coreWorkAnswer
	* @return
	*/
	public boolean deleteCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreWorkAnswer
	* @return
	*/
	public CoreWorkAnswer getCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreWorkAnswer> findCoreWorkAnswerList(String crwkaWorkUuid, String crwkaUser);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreWorkAnswer> findCoreWorkAnswerPage(String crwkaUserCode, String crwkaCourseName, String crwkaClass, int pageNum, int pageSize);

	/**
	* 查询所有List
	* 
	* @param workList
	* @param userList
	* @return List
	*/
	public List<CoreWorkAnswer> findCoreWorkAnswerForListsByFilters(List<String> workList, List<String> userList);
	
//<=================定制内容开始==============
//==================定制内容结束==============>

}