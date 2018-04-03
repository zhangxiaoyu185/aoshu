package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreKnowledge;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 知识点表
*/
public interface CoreKnowledgeService {

	/**
	* 查询所有大知识点
	*@return Page
	*/
	public Page<CoreKnowledge> findCoreKnowledgeTop(String crkleName, int pageNum, int pageSize);
	
	/**
	* 添加
	* @param coreKnowledge
	* @return
	*/
	public boolean insertCoreKnowledge(CoreKnowledge coreKnowledge);

	/**
	* 修改
	* @param coreKnowledge
	* @return
	*/
	public boolean updateCoreKnowledge(CoreKnowledge coreKnowledge);

	/**
	* 删除
	* @param coreKnowledge
	* @return
	*/
	public boolean deleteCoreKnowledge(CoreKnowledge coreKnowledge);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreKnowledge
	* @return
	*/
	public CoreKnowledge getCoreKnowledge(CoreKnowledge coreKnowledge);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreKnowledge> findCoreKnowledgeList();
	
	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreKnowledge> findCoreKnowledgeListBys(String crkleCategory);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreKnowledge> findCoreKnowledgePage(String crkleName, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}