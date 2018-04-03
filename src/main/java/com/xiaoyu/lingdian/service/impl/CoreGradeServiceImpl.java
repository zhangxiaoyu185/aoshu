package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreGradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreGrade;

/**
* 班级表
*/
@Service("coreGradeService")
public class CoreGradeServiceImpl implements CoreGradeService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreGrade(CoreGrade coreGrade) {
		myBatisDAO.insert(coreGrade);
		return true;
	}

	@Override
	public boolean updateCoreGrade(CoreGrade coreGrade) {
		myBatisDAO.update(coreGrade);
		return true;
	}

	@Override
	public boolean deleteCoreGrade(CoreGrade coreGrade) {
		myBatisDAO.delete(coreGrade);
		return true;
	}

	private static final String DELETEBATCH_COREGRADE_BY_IDS="deleteBatchCoreGradeByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREGRADE_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreGrade getCoreGrade(CoreGrade coreGrade) {
		return (CoreGrade) myBatisDAO.findForObject(coreGrade);
	}

	private static final String FIND_COREGRADE_FOR_LISTS="findCoreGradeForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreGrade> findCoreGradeList() {
		return myBatisDAO.findForList(FIND_COREGRADE_FOR_LISTS, null);
	}

	private static final String FIND_COREGRADE_FOR_PAGES="findCoreGradeForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreGrade> findCoreGradePage(String crgaeName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgaeName", crgaeName);
		return myBatisDAO.findForPage(FIND_COREGRADE_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}