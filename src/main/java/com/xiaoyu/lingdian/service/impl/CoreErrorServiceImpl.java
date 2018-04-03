package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreError;

/**
* 错题表
*/
@Service("coreErrorService")
public class CoreErrorServiceImpl implements CoreErrorService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	private static final String FIND_COREERROR_PAGE_BY="findCoreErrorPageBy";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreError> findCoreErrorPageBy(String crerrUser, String crqtsQuesClass, Integer crerrJudge, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crerrUser", crerrUser);
		hashMap.put("crqtsQuesClass", crqtsQuesClass);
		hashMap.put("crerrJudge", crerrJudge);
		return myBatisDAO.findForPage(FIND_COREERROR_PAGE_BY, new PageRequest(pageNum, pageSize, hashMap));
	}
	
	@Override
	public boolean insertCoreError(CoreError coreError) {
		myBatisDAO.insert(coreError);
		return true;
	}

	@Override
	public boolean updateCoreError(CoreError coreError) {
		myBatisDAO.update(coreError);
		return true;
	}

	@Override
	public boolean deleteCoreError(CoreError coreError) {
		myBatisDAO.delete(coreError);
		return true;
	}

	private static final String DELETEBATCH_COREERROR_BY_IDS="deleteBatchCoreErrorByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREERROR_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreError getCoreError(CoreError coreError) {
		return (CoreError) myBatisDAO.findForObject(coreError);
	}

	private static final String FIND_COREERROR_FOR_LISTS="findCoreErrorForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreError> findCoreErrorList(String crerrUser, String crerrFrom, String crerrQues) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crerrUser", crerrUser);
		hashMap.put("crerrFrom", crerrFrom);
		hashMap.put("crerrQues", crerrQues);
		return myBatisDAO.findForList(FIND_COREERROR_FOR_LISTS, hashMap);
	}

	private static final String FIND_COREERROR_FOR_PAGES="findCoreErrorForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreError> findCoreErrorPage(String crerrFromName, String crqtsQuesClass, String crusrGrade, String crqtsQuesCode, String crusrCode, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crerrFromName", crerrFromName);
		hashMap.put("crqtsQuesClass", crqtsQuesClass);
		hashMap.put("crusrGrade", crusrGrade);
		hashMap.put("crqtsQuesCode", crqtsQuesCode);
		hashMap.put("crusrCode", crusrCode);
		return myBatisDAO.findForPage(FIND_COREERROR_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}