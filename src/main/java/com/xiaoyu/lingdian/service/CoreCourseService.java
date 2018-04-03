package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreCourse;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 课程表
*/
public interface CoreCourseService {

	/**
	* 查询
	* @param coreCourse
	* @return
	*/
	public CoreCourse getCoreCourseByName(CoreCourse coreCourse);
	
	/**
	* 添加
	* @param coreCourse
	* @return
	*/
	public boolean insertCoreCourse(CoreCourse coreCourse);

	/**
	* 修改
	* @param coreCourse
	* @return
	*/
	public boolean updateCoreCourse(CoreCourse coreCourse);

	/**
	* 删除
	* @param coreCourse
	* @return
	*/
	public boolean deleteCoreCourse(CoreCourse coreCourse);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreCourse
	* @return
	*/
	public CoreCourse getCoreCourse(CoreCourse coreCourse);

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreCourse> findCoreCoursePage(String crcreName, String crcreClass, int pageNum, int pageSize);

	/**
	* 查询所有未添加Page
	* @return Page
	*/
	public Page<CoreCourse> findCoreCoursePageWTJ(String crgceGrade, String crcreName, String crcreClass, int pageNum, int pageSize);
//<=================定制内容开始==============
//==================定制内容结束==============>

}