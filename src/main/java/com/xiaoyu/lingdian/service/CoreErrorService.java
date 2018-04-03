package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreError;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 错题表
*/
public interface CoreErrorService {
	
	/**
	* 查询所有Page
	* 
	* @param crerrUser
	* @param crqtsQuesClass
	* @param crerrJudge
	* @return Page
	*/
	public Page<CoreError> findCoreErrorPageBy(String crerrUser, String crqtsQuesClass, Integer crerrJudge, int pageNum, int pageSize);
	
	/**
	* 添加
	* @param coreError
	* @return
	*/
	public boolean insertCoreError(CoreError coreError);

	/**
	* 修改
	* @param coreError
	* @return
	*/
	public boolean updateCoreError(CoreError coreError);

	/**
	* 删除
	* @param coreError
	* @return
	*/
	public boolean deleteCoreError(CoreError coreError);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreError
	* @return
	*/
	public CoreError getCoreError(CoreError coreError);

	/**
	* 查询重复错题集List
	* @return List
	*/
	public List<CoreError> findCoreErrorList(String crerrUser, String crerrFrom, String crerrQues);

	/**
	* 查询所有Page
	* 
	* @param crerrFromName
	* @param crqtsQuesClass
	* @param crusrGrade
	* @param crqtsQuesCode
	* @param crusrCode
	* @return Page
	*/
	public Page<CoreError> findCoreErrorPage(String crerrFromName, String crqtsQuesClass, String crusrGrade, String crqtsQuesCode, String crusrCode, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}