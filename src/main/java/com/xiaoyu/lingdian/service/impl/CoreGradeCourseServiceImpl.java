package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreGradeCourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreGradeCourse;

/**
* 班级课程表
*/
@Service("coreGradeCourseService")
public class CoreGradeCourseServiceImpl implements CoreGradeCourseService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreGradeCourse(CoreGradeCourse coreGradeCourse) {
		myBatisDAO.insert(coreGradeCourse);
		return true;
	}

	@Override
	public boolean updateCoreGradeCourse(CoreGradeCourse coreGradeCourse) {
		myBatisDAO.update(coreGradeCourse);
		return true;
	}

	@Override
	public boolean deleteCoreGradeCourse(CoreGradeCourse coreGradeCourse) {
		myBatisDAO.delete(coreGradeCourse);
		return true;
	}

	private static final String DELETEBATCH_COREGRADECOURSE_BY_IDS="deleteBatchCoreGradeCourseByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREGRADECOURSE_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreGradeCourse getCoreGradeCourse(CoreGradeCourse coreGradeCourse) {
		return (CoreGradeCourse) myBatisDAO.findForObject(coreGradeCourse);
	}

	private static final String FIND_COREGRADECOURSE_FOR_PAGES="findCoreGradeCourseForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreGradeCourse> findCoreGradeCoursePage(String crgceGrade, String crcreName, String crcreClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgceGrade", crgceGrade);
		hashMap.put("crcreName", crcreName);
		hashMap.put("crcreClass", crcreClass);
		return myBatisDAO.findForPage(FIND_COREGRADECOURSE_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public CoreGradeCourse getCoreGradeCourseByGradeCourse(String crgceGrade, String crgceCourse) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgceGrade", crgceGrade);
		hashMap.put("crgceCourse", crgceCourse);
		return (CoreGradeCourse) myBatisDAO.findForObject("getCoreGradeCourseByGradeCourse", hashMap);
	}

	@Override
	public int getMaxOrdByGradeCourseClass(String crgceGrade, String crcreClass) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgceGrade", crgceGrade);
		hashMap.put("crcreClass", crcreClass);
		Integer max = (Integer) myBatisDAO.findForObject("getMaxOrdByGradeCourseClass", hashMap);
		if (null == max) {
			return 0;
		} else {
			return max;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreGradeCourse> findCoreGradeCourseList(String crgceGrade,
			String crcreClass) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgceGrade", crgceGrade);
		hashMap.put("crcreClass", crcreClass);
		return myBatisDAO.findForList(FIND_COREGRADECOURSE_FOR_PAGES, hashMap);
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}