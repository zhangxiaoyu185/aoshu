package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreExercises;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 经典练习表
*/
public interface CoreExercisesService {

	/**
	* 根据crecsCourse查询
	* @param coreExercises
	* @return
	*/
	public CoreExercises getCoreExercisesByCourse(CoreExercises coreExercises);
	
	/**
	* 修改
	* @param coreExercises
	* @return
	*/
	public boolean updateCoreExercisesByCourse(CoreExercises coreExercises);

	/**
	* 删除
	* @param coreExercises
	* @return
	*/
	public boolean deleteCoreExercisesByCourse(CoreExercises coreExercises);
	
	/**
	* 添加
	* @param coreExercises
	* @return
	*/
	public boolean insertCoreExercises(CoreExercises coreExercises);

	/**
	* 修改
	* @param coreExercises
	* @return
	*/
	public boolean updateCoreExercises(CoreExercises coreExercises);

	/**
	* 删除
	* @param coreExercises
	* @return
	*/
	public boolean deleteCoreExercises(CoreExercises coreExercises);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreExercises
	* @return
	*/
	public CoreExercises getCoreExercises(CoreExercises coreExercises);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreExercises> findCoreExercisesList(String crecsCourse,String crecsClass);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreExercises> findCoreExercisesPage(String crecsCourse,String crecsClass, int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}