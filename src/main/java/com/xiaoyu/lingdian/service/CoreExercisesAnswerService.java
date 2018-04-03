package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreExercisesAnswer;

import java.util.List;

import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 练习回答表
*/
public interface CoreExercisesAnswerService {

	/**
	* 添加
	* @param coreExercisesAnswer
	* @return
	*/
	public boolean insertCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer);

	/**
	* 修改
	* @param coreExercisesAnswer
	* @return
	*/
	public boolean updateCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer);

	/**
	* 删除
	* @param coreExercisesAnswer
	* @return
	*/
	public boolean deleteCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreExercisesAnswer
	* @return
	*/
	public CoreExercisesAnswer getCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreExercisesAnswer> findCoreExercisesAnswerList(String cresaExercUuid, String cresaUser);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreExercisesAnswer> findCoreExercisesAnswerPage(String cresaUserCode, String cresaCourseName, String cresaClass, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}