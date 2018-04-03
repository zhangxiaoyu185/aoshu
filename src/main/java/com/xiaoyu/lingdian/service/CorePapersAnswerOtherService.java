package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CorePapersAnswerOther;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 临时试卷回答表
*/
public interface CorePapersAnswerOtherService {

	/**
	* 添加
	* @param corePapersAnswer
	* @return
	*/
	public boolean insertCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther);

	/**
	* 修改
	* @param corePapersAnswer
	* @return
	*/
	public boolean updateCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther);

	/**
	* 删除
	* @param corePapersAnswer
	* @return
	*/
	public boolean deleteCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther);

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
	public CorePapersAnswerOther getCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther);

	/**
	* 查询所有List
	* 
	* @param crpsaPapersUuid
	* @param crpsaUser
	* @return List
	*/
	public List<CorePapersAnswerOther> findCorePapersAnswerOtherList(String crpsaPapersUuid, String crpsaUser);

	/**
	* 查询所有Page
	* 
	* @param crpsaUserCode
	* @param crpsaPapersName
	* @param crpsaClass
	* @return Page
	*/
	public Page<CorePapersAnswerOther> findCorePapersAnswerOtherPage(String crpsaUserCode, String crpsaPapersName, String crpsaClass, int pageNum, int pageSize);

	/**
	* 查询所有List
	* 
	* @param papersList
	* @param userList
	* @return List
	*/
	public List<CorePapersAnswerOther> findCorePapersAnswerOtherForListsByFilters(List<String> papersList, List<String> userList);
	
//<=================定制内容开始==============
//==================定制内容结束==============>

}