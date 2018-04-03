package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreWorkAnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreWorkAnswer;

/**
* 训练回答表
*/
@Service("coreWorkAnswerService")
public class CoreWorkAnswerServiceImpl implements CoreWorkAnswerService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer) {
		myBatisDAO.insert(coreWorkAnswer);
		return true;
	}

	@Override
	public boolean updateCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer) {
		myBatisDAO.update(coreWorkAnswer);
		return true;
	}

	@Override
	public boolean deleteCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer) {
		myBatisDAO.delete(coreWorkAnswer);
		return true;
	}

	private static final String DELETEBATCH_COREWORKANSWER_BY_IDS="deleteBatchCoreWorkAnswerByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREWORKANSWER_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreWorkAnswer getCoreWorkAnswer(CoreWorkAnswer coreWorkAnswer) {
		return (CoreWorkAnswer) myBatisDAO.findForObject(coreWorkAnswer);
	}

	private static final String FIND_COREWORKANSWER_FOR_LISTS="findCoreWorkAnswerForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreWorkAnswer> findCoreWorkAnswerList(String crwkaWorkUuid, String crwkaUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crwkaWorkUuid", crwkaWorkUuid);
		hashMap.put("crwkaUser", crwkaUser);
		return myBatisDAO.findForList(FIND_COREWORKANSWER_FOR_LISTS, hashMap);
	}

	private static final String FIND_COREWORKANSWER_FOR_PAGES="findCoreWorkAnswerForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreWorkAnswer> findCoreWorkAnswerPage(String crwkaUserCode, String crwkaCourseName, String crwkaClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crwkaUserCode", crwkaUserCode);
		hashMap.put("crwkaCourseName", crwkaCourseName);
		hashMap.put("crwkaClass", crwkaClass);
		return myBatisDAO.findForPage(FIND_COREWORKANSWER_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreWorkAnswer> findCoreWorkAnswerForListsByFilters(
			List<String> workList, List<String> userList) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("workList", workList);
		hashMap.put("userList", userList);
		return myBatisDAO.findForList("findCoreWorkAnswerForListsByFilters", hashMap);
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}