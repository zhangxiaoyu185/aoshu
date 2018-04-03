package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreGradeCourse;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 班级课程表
*/
public interface CoreGradeCourseService {

	/**
	* 添加
	* @param coreGradeCourse
	* @return
	*/
	public boolean insertCoreGradeCourse(CoreGradeCourse coreGradeCourse);

	/**
	* 修改
	* @param coreGradeCourse
	* @return
	*/
	public boolean updateCoreGradeCourse(CoreGradeCourse coreGradeCourse);

	/**
	* 删除
	* @param coreGradeCourse
	* @return
	*/
	public boolean deleteCoreGradeCourse(CoreGradeCourse coreGradeCourse);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreGradeCourse
	* @return
	*/
	public CoreGradeCourse getCoreGradeCourse(CoreGradeCourse coreGradeCourse);

	/**
	* 查询班级年级下最大排序
	* 
	* @param crgceGrade
	* @param crcreClass
	* @return
	*/
	public int getMaxOrdByGradeCourseClass(String crgceGrade, String crcreClass);
	
	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreGradeCourse> findCoreGradeCoursePage(String crgceGrade, String crcreName, String crcreClass, int pageNum, int pageSize);

	/**
	* 查询
	* 
	* @param crgceGrade
	* @param crgceCourse
	* @return
	*/
	public CoreGradeCourse getCoreGradeCourseByGradeCourse(String crgceGrade, String crgceCourse);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreGradeCourse> findCoreGradeCourseList(String crgceGrade, String crcreClass);

//<=================定制内容开始==============
//==================定制内容结束==============>

}