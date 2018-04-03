package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreWorkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreWork;

/**
* 强化训练表
*/
@Service("coreWorkService")
public class CoreWorkServiceImpl implements CoreWorkService {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	private static final String GET_COREWORK_BY_COURSE="getCoreWorkByCourse";
	@Override
	public CoreWork getCoreWorkByCourse(CoreWork coreWork) {
		return (CoreWork) myBatisDAO.findForObject(GET_COREWORK_BY_COURSE,coreWork);
	}
	
	@Override
	public boolean insertCoreWork(CoreWork coreWork) {
		myBatisDAO.insert(coreWork);
		return true;
	}

	@Override
	public boolean updateCoreWork(CoreWork coreWork) {
		myBatisDAO.update(coreWork);
		return true;
	}

	@Override
	public boolean deleteCoreWork(CoreWork coreWork) {
		myBatisDAO.delete(coreWork);
		return true;
	}

	private static final String DELETEBATCH_COREWORK_BY_IDS="deleteBatchCoreWorkByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREWORK_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreWork getCoreWork(CoreWork coreWork) {
		return (CoreWork) myBatisDAO.findForObject(coreWork);
	}

	private static final String FIND_COREWORK_FOR_LISTS="findCoreWorkForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreWork> findCoreWorkList(String crwokCourse,String crwokClass) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crwokCourse", crwokCourse);
		hashMap.put("crwokClass", crwokClass);
		return myBatisDAO.findForList(FIND_COREWORK_FOR_LISTS, hashMap);
	}

	private static final String FIND_COREWORK_FOR_PAGES="findCoreWorkForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreWork> findCoreWorkPage(String crwokCourse,String crwokClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crwokCourse", crwokCourse);
		hashMap.put("crwokClass", crwokClass);
		return myBatisDAO.findForPage(FIND_COREWORK_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreWork> findCoreWorkForListByFilters(List<String> courseList) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("courseList", courseList);
		return myBatisDAO.findForList("findCoreWorkForListByFilters", hashMap);
	}

	@Override
	public boolean deleteCoreWorkByCourse(CoreWork coreWork) {
		myBatisDAO.delete("deleteCoreWorkByCourse", coreWork);
		return true;
	}

	@Override
	public boolean updateCoreWorkByCourse(CoreWork coreWork) {
		myBatisDAO.update("updateCoreWorkByCourse", coreWork);
		return true;
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}