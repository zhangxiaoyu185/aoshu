package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreDir;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 图片目录表
*/
public interface CoreDirService {

	/**
	* 添加
	* @param coreDir
	* @return
	*/
	public boolean insertCoreDir(CoreDir coreDir);

	/**
	* 修改
	* @param coreDir
	* @return
	*/
	public boolean updateCoreDir(CoreDir coreDir);

	/**
	* 删除
	* @param coreDir
	* @return
	*/
	public boolean deleteCoreDir(CoreDir coreDir);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreDir
	* @return
	*/
	public CoreDir getCoreDir(CoreDir coreDir);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreDir> findCoreDirList();

	/**
	* 根据文件夹名查询List
	* 
	* @param
	* @return List
	*/
	public List<CoreDir> getCoreDirByName(String crdirName);
	
	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreDir> findCoreDirPage(String crdirName, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}