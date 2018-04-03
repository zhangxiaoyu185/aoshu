package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CorePapers;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 模拟试卷表
*/
public interface CorePapersService {

	/**
	* 添加
	* @param corePapers
	* @return
	*/
	public boolean insertCorePapers(CorePapers corePapers);

	/**
	* 修改
	* @param corePapers
	* @return
	*/
	public boolean updateCorePapers(CorePapers corePapers);

	/**
	* 删除
	* @param corePapers
	* @return
	*/
	public boolean deleteCorePapers(CorePapers corePapers);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param corePapers
	* @return
	*/
	public CorePapers getCorePapers(CorePapers corePapers);

	/**
	* 查询所有Page
	* @param crpesClass 所属年级
	* @param crpesName 试卷名称
	* @return Page
	*/
	public Page<CorePapers> findCorePapersPage(String crpesClass, String crpesName, int pageNum, int pageSize);

	/**
	* 查询所有未添加Page
	* @param crgpsGrade 所属班级
	* @param crpesClass 所属年级
	* @param crpesName 试卷名称
	* @return Page
	*/
	public Page<CorePapers> findCorePapersPageWTJ(String crgpsGrade, String crpesClass, String crpesName, int pageNum, int pageSize);
//<=================定制内容开始==============
//==================定制内容结束==============>

}