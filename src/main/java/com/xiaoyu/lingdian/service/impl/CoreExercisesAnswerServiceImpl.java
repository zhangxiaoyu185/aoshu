package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreExercisesAnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreExercisesAnswer;

/**
* 练习回答表
*/
@Service("coreExercisesAnswerService")
public class CoreExercisesAnswerServiceImpl implements CoreExercisesAnswerService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer) {
		myBatisDAO.insert(coreExercisesAnswer);
		return true;
	}

	@Override
	public boolean updateCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer) {
		myBatisDAO.update(coreExercisesAnswer);
		return true;
	}

	@Override
	public boolean deleteCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer) {
		myBatisDAO.delete(coreExercisesAnswer);
		return true;
	}

	private static final String DELETEBATCH_COREEXERCISESANSWER_BY_IDS="deleteBatchCoreExercisesAnswerByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREEXERCISESANSWER_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreExercisesAnswer getCoreExercisesAnswer(CoreExercisesAnswer coreExercisesAnswer) {
		return (CoreExercisesAnswer) myBatisDAO.findForObject(coreExercisesAnswer);
	}

	private static final String FIND_COREEXERCISESANSWER_FOR_LISTS="findCoreExercisesAnswerForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreExercisesAnswer> findCoreExercisesAnswerList(String cresaExercUuid, String cresaUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("cresaExercUuid", cresaExercUuid);
		hashMap.put("cresaUser", cresaUser);
		return myBatisDAO.findForList(FIND_COREEXERCISESANSWER_FOR_LISTS, hashMap);
	}

	private static final String FIND_COREEXERCISESANSWER_FOR_PAGES="findCoreExercisesAnswerForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreExercisesAnswer> findCoreExercisesAnswerPage(String cresaUserCode, String cresaCourseName, String cresaClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("cresaUserCode", cresaUserCode);
		hashMap.put("cresaCourseName", cresaCourseName);
		hashMap.put("cresaClass", cresaClass);
		return myBatisDAO.findForPage(FIND_COREEXERCISESANSWER_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}