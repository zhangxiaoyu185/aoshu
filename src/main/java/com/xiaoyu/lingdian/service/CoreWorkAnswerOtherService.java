package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreWorkAnswerOther;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 临时训练回答表
*/
public interface CoreWorkAnswerOtherService {

	/**
	* 添加
	* @param coreWorkAnswerOther
	* @return
	*/
	public boolean insertCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther);

	/**
	* 修改
	* @param coreWorkAnswerOther
	* @return
	*/
	public boolean updateCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther);

	/**
	* 删除
	* @param coreWorkAnswerOther
	* @return
	*/
	public boolean deleteCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreWorkAnswerOther
	* @return
	*/
	public CoreWorkAnswerOther getCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreWorkAnswerOther> findCoreWorkAnswerOtherList(String crwkaWorkUuid, String crwkaUser);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreWorkAnswerOther> findCoreWorkAnswerOtherPage(String crwkaUserCode, String crwkaCourseName, String crwkaClass, int pageNum, int pageSize);

	/**
	* 查询所有List
	* 
	* @param workList
	* @param userList
	* @return List
	*/
	public List<CoreWorkAnswerOther> findCoreWorkAnswerOtherForListsByFilters(List<String> workList, List<String> userList);
	
//<=================定制内容开始==============
//==================定制内容结束==============>

}