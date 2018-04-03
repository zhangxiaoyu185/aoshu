package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreGradePapers;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 班级模拟试卷表
*/
public interface CoreGradePapersService {

	/**
	* 添加
	* @param coreGradePapers
	* @return
	*/
	public boolean insertCoreGradePapers(CoreGradePapers coreGradePapers);

	/**
	* 修改
	* @param coreGradePapers
	* @return
	*/
	public boolean updateCoreGradePapers(CoreGradePapers coreGradePapers);

	/**
	* 删除
	* @param coreGradePapers
	* @return
	*/
	public boolean deleteCoreGradePapers(CoreGradePapers coreGradePapers);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreGradePapers
	* @return
	*/
	public CoreGradePapers getCoreGradePapers(CoreGradePapers coreGradePapers);

	/**
	* 查询班级年级下最大排序
	* 
	* @param crgpsGrade
	* @param crpesClass
	* @return
	*/
	public int getMaxOrdByGradePapersClass(String crgpsGrade, String crpesClass);
	
	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreGradePapers> findCoreGradePapersPage(String crgpsGrade, String crpesClass, String crpesName, int pageNum, int pageSize);

	/**
	* 查询
	* 
	* @param crgpsGrade
	* @param crgpsPapers
	* @return
	*/
	public CoreGradePapers getCoreGradePapersByGradePapers(String crgpsGrade, String crgpsPapers);
	
	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreGradePapers> findCoreGradePapersList(String crgpsGrade, String crpesClass);
	
//<=================定制内容开始==============
//==================定制内容结束==============>

}