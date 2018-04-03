package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreQuestions;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 题库表
*/
public interface CoreQuestionsService {

	/**
	* 添加
	* @param coreQuestions
	* @return
	*/
	public boolean insertCoreQuestions(CoreQuestions coreQuestions);

	/**
	* 修改
	* @param coreQuestions
	* @return
	*/
	public boolean updateCoreQuestions(CoreQuestions coreQuestions);

	/**
	* 删除
	* @param coreQuestions
	* @return
	*/
	public boolean deleteCoreQuestions(CoreQuestions coreQuestions);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreQuestions
	* @return
	*/
	public CoreQuestions getCoreQuestions(CoreQuestions coreQuestions);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreQuestions> findCoreQuestionsList();

	/**
	* 根据题目编号查询List
	* 
	* @param crqtsCode
	* @return List
	*/
	public List<CoreQuestions> getCoreQuestionsByCode(String crqtsCode);
	
	/**
	* 查询所有Page
	* 
	* @param crqtsClass
	* @param crqtsCode
	* @return Page
	*/
	public Page<CoreQuestions> findCoreQuestionsPage(String crqtsClass, String crqtsCode, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}