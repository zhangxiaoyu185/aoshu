package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreUser;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户表
*/
public interface CoreUserService {
	
	/**
	* 查询根据账户名称查询
	* @param coreUser
	* @return
	*/
	public CoreUser getCoreUserByName(CoreUser coreUser);

	/**
	* 添加
	* @param coreUser
	* @return
	*/
	public boolean insertCoreUser(CoreUser coreUser);

	/**
	* 修改
	* @param coreUser
	* @return
	*/
	public boolean updateCoreUser(CoreUser coreUser);

	/**
	* 删除
	* @param coreUser
	* @return
	*/
	public boolean deleteCoreUser(CoreUser coreUser);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreUser
	* @return
	*/
	public CoreUser getCoreUser(CoreUser coreUser);

	/**
	* 查询所有List
	* @param crusrGrade
	* @return List
	*/
	public List<CoreUser> findCoreUserList(String crusrGrade);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreUser> findCoreUserPage(String crusrName, String crusrGrade, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}