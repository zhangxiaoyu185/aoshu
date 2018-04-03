package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreWorkAnswerOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreWorkAnswerOther;

/**
* 临时训练回答表
*/
@Service("coreWorkAnswerOtherService")
public class CoreWorkAnswerOtherServiceImpl implements CoreWorkAnswerOtherService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther) {
		myBatisDAO.insert(coreWorkAnswerOther);
		return true;
	}

	@Override
	public boolean updateCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther) {
		myBatisDAO.update(coreWorkAnswerOther);
		return true;
	}

	@Override
	public boolean deleteCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther) {
		myBatisDAO.delete(coreWorkAnswerOther);
		return true;
	}

	private static final String DELETEBATCH_CoreWorkAnswerOther_BY_IDS="deleteBatchCoreWorkAnswerOtherByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_CoreWorkAnswerOther_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreWorkAnswerOther getCoreWorkAnswerOther(CoreWorkAnswerOther coreWorkAnswerOther) {
		return (CoreWorkAnswerOther) myBatisDAO.findForObject(coreWorkAnswerOther);
	}

	private static final String FIND_CoreWorkAnswerOther_FOR_LISTS="findCoreWorkAnswerOtherForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreWorkAnswerOther> findCoreWorkAnswerOtherList(String crwkaWorkUuid, String crwkaUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crwkaWorkUuid", crwkaWorkUuid);
		hashMap.put("crwkaUser", crwkaUser);
		return myBatisDAO.findForList(FIND_CoreWorkAnswerOther_FOR_LISTS, hashMap);
	}

	private static final String FIND_CoreWorkAnswerOther_FOR_PAGES="findCoreWorkAnswerOtherForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreWorkAnswerOther> findCoreWorkAnswerOtherPage(String crwkaUserCode, String crwkaCourseName, String crwkaClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crwkaUserCode", crwkaUserCode);
		hashMap.put("crwkaCourseName", crwkaCourseName);
		hashMap.put("crwkaClass", crwkaClass);
		return myBatisDAO.findForPage(FIND_CoreWorkAnswerOther_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreWorkAnswerOther> findCoreWorkAnswerOtherForListsByFilters(
			List<String> workList, List<String> userList) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("workList", workList);
		hashMap.put("userList", userList);
		return myBatisDAO.findForList("findCoreWorkAnswerOtherForListsByFilters", hashMap);
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}