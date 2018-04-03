package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreGrade;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 班级表
*/
public interface CoreGradeService {

	/**
	* 添加
	* @param coreGrade
	* @return
	*/
	public boolean insertCoreGrade(CoreGrade coreGrade);

	/**
	* 修改
	* @param coreGrade
	* @return
	*/
	public boolean updateCoreGrade(CoreGrade coreGrade);

	/**
	* 删除
	* @param coreGrade
	* @return
	*/
	public boolean deleteCoreGrade(CoreGrade coreGrade);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreGrade
	* @return
	*/
	public CoreGrade getCoreGrade(CoreGrade coreGrade);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreGrade> findCoreGradeList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreGrade> findCoreGradePage(String crgaeName, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}