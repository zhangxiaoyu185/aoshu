package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CorePapersAnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CorePapersAnswer;

/**
* 试卷回答表
*/
@Service("corePapersAnswerService")
public class CorePapersAnswerServiceImpl implements CorePapersAnswerService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCorePapersAnswer(CorePapersAnswer corePapersAnswer) {
		myBatisDAO.insert(corePapersAnswer);
		return true;
	}

	@Override
	public boolean updateCorePapersAnswer(CorePapersAnswer corePapersAnswer) {
		myBatisDAO.update(corePapersAnswer);
		return true;
	}

	@Override
	public boolean deleteCorePapersAnswer(CorePapersAnswer corePapersAnswer) {
		myBatisDAO.delete(corePapersAnswer);
		return true;
	}

	private static final String DELETEBATCH_COREPAPERSANSWER_BY_IDS="deleteBatchCorePapersAnswerByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREPAPERSANSWER_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CorePapersAnswer getCorePapersAnswer(CorePapersAnswer corePapersAnswer) {
		return (CorePapersAnswer) myBatisDAO.findForObject(corePapersAnswer);
	}

	private static final String FIND_COREPAPERSANSWER_FOR_LISTS="findCorePapersAnswerForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CorePapersAnswer> findCorePapersAnswerList(String crpsaPapersUuid, String crpsaUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crpsaPapersUuid", crpsaPapersUuid);
		hashMap.put("crpsaUser", crpsaUser);
		return myBatisDAO.findForList(FIND_COREPAPERSANSWER_FOR_LISTS, hashMap);
	}

	private static final String FIND_COREPAPERSANSWER_FOR_PAGES="findCorePapersAnswerForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CorePapersAnswer> findCorePapersAnswerPage(String crpsaUserCode, String crpsaPapersName, String crpsaClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crpsaUserCode", crpsaUserCode);
		hashMap.put("crpsaPapersName", crpsaPapersName);
		hashMap.put("crpsaClass", crpsaClass);
		return myBatisDAO.findForPage(FIND_COREPAPERSANSWER_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CorePapersAnswer> findCorePapersAnswerForListsByFilters(
			List<String> papersList, List<String> userList) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("papersList", papersList);
		hashMap.put("userList", userList);
		return myBatisDAO.findForList("findCorePapersAnswerForListsByFilters", hashMap);
	}

	//<=================定制内容开始==============
	//==================定制内容结束==============>

}