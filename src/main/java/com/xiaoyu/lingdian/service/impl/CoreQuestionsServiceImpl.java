package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreQuestions;

/**
* 题库表
*/
@Service("coreQuestionsService")
public class CoreQuestionsServiceImpl implements CoreQuestionsService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreQuestions(CoreQuestions coreQuestions) {
		myBatisDAO.insert(coreQuestions);
		return true;
	}

	@Override
	public boolean updateCoreQuestions(CoreQuestions coreQuestions) {
		myBatisDAO.update(coreQuestions);
		return true;
	}

	@Override
	public boolean deleteCoreQuestions(CoreQuestions coreQuestions) {
		myBatisDAO.delete(coreQuestions);
		return true;
	}

	private static final String DELETEBATCH_COREQUESTIONS_BY_IDS="deleteBatchCoreQuestionsByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREQUESTIONS_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreQuestions getCoreQuestions(CoreQuestions coreQuestions) {
		return (CoreQuestions) myBatisDAO.findForObject(coreQuestions);
	}

	private static final String FIND_COREQUESTIONS_FOR_LISTS="findCoreQuestionsForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreQuestions> findCoreQuestionsList() {
		return myBatisDAO.findForList(FIND_COREQUESTIONS_FOR_LISTS, null);
	}

	private static final String FIND_COREQUESTIONS_FOR_PAGES="findCoreQuestionsForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreQuestions> findCoreQuestionsPage(String crqtsClass, String crqtsCode, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crqtsClass", crqtsClass);
		hashMap.put("crqtsCode", crqtsCode);
		return myBatisDAO.findForPage(FIND_COREQUESTIONS_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	private static final String GET_COREQUESTIONS_BY_CODE="getCoreQuestionsByCode";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoreQuestions> getCoreQuestionsByCode(String crqtsCode) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crqtsCode", crqtsCode);
		return myBatisDAO.findForList(GET_COREQUESTIONS_BY_CODE, hashMap);
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}