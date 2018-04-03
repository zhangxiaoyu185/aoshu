package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CorePapersAnswer;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 试卷回答表
*/
public interface CorePapersAnswerService {

	/**
	* 添加
	* @param corePapersAnswer
	* @return
	*/
	public boolean insertCorePapersAnswer(CorePapersAnswer corePapersAnswer);

	/**
	* 修改
	* @param corePapersAnswer
	* @return
	*/
	public boolean updateCorePapersAnswer(CorePapersAnswer corePapersAnswer);

	/**
	* 删除
	* @param corePapersAnswer
	* @return
	*/
	public boolean deleteCorePapersAnswer(CorePapersAnswer corePapersAnswer);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param corePapersAnswer
	* @return
	*/
	public CorePapersAnswer getCorePapersAnswer(CorePapersAnswer corePapersAnswer);

	/**
	* 查询所有List
	* 
	* @param crpsaPapersUuid
	* @param crpsaUser
	* @return List
	*/
	public List<CorePapersAnswer> findCorePapersAnswerList(String crpsaPapersUuid, String crpsaUser);

	/**
	* 查询所有Page
	* 
	* @param crpsaUserCode
	* @param crpsaPapersName
	* @param crpsaClass
	* @return Page
	*/
	public Page<CorePapersAnswer> findCorePapersAnswerPage(String crpsaUserCode, String crpsaPapersName, String crpsaClass, int pageNum, int pageSize);

	/**
	* 查询所有List
	* 
	* @param papersList
	* @param userList
	* @return List
	*/
	public List<CorePapersAnswer> findCorePapersAnswerForListsByFilters(List<String> papersList, List<String> userList);
	
//<=================定制内容开始==============
//==================定制内容结束==============>

}