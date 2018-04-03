package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreCourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreCourse;

/**
* 课程表
*/
@Service("coreCourseService")
public class CoreCourseServiceImpl implements CoreCourseService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	private static final String GET_CORECOURSE_BY_NAME="getCoreCourseByName";
	@Override
	public CoreCourse getCoreCourseByName(CoreCourse coreCourse) {
		return (CoreCourse) myBatisDAO.findForObject(GET_CORECOURSE_BY_NAME, coreCourse);
	}
	
	@Override
	public boolean insertCoreCourse(CoreCourse coreCourse) {
		myBatisDAO.insert(coreCourse);
		return true;
	}

	@Override
	public boolean updateCoreCourse(CoreCourse coreCourse) {
		myBatisDAO.update(coreCourse);
		return true;
	}

	@Override
	public boolean deleteCoreCourse(CoreCourse coreCourse) {
		myBatisDAO.delete(coreCourse);
		return true;
	}

	private static final String DELETEBATCH_CORECOURSE_BY_IDS="deleteBatchCoreCourseByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_CORECOURSE_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreCourse getCoreCourse(CoreCourse coreCourse) {
		return (CoreCourse) myBatisDAO.findForObject(coreCourse);
	}

	private static final String FIND_CORECOURSE_FOR_PAGES="findCoreCourseForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreCourse> findCoreCoursePage(String crcreName, String crcreClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crcreName", crcreName);
		hashMap.put("crcreClass", crcreClass);
		return myBatisDAO.findForPage(FIND_CORECOURSE_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreCourse> findCoreCoursePageWTJ(String crgceGrade,
			String crcreName, String crcreClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgceGrade", crgceGrade);
		hashMap.put("crcreName", crcreName);
		hashMap.put("crcreClass", crcreClass);
		return myBatisDAO.findForPage("findCoreCourseForPagesWTJ", new PageRequest(pageNum, pageSize, hashMap));
	}

}