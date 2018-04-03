package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CorePapersAnswerOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CorePapersAnswerOther;

/**
* 临时试卷回答表
*/
@Service("corePapersAnswerOtherService")
public class CorePapersAnswerOtherServiceImpl implements CorePapersAnswerOtherService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther) {
		myBatisDAO.insert(corePapersAnswerOther);
		return true;
	}

	@Override
	public boolean updateCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther) {
		myBatisDAO.update(corePapersAnswerOther);
		return true;
	}

	@Override
	public boolean deleteCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther) {
		myBatisDAO.delete(corePapersAnswerOther);
		return true;
	}

	private static final String DELETEBATCH_CorePapersAnswerOther_BY_IDS="deleteBatchCorePapersAnswerOtherByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_CorePapersAnswerOther_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CorePapersAnswerOther getCorePapersAnswerOther(CorePapersAnswerOther corePapersAnswerOther) {
		return (CorePapersAnswerOther) myBatisDAO.findForObject(corePapersAnswerOther);
	}

	private static final String FIND_CorePapersAnswerOther_FOR_LISTS="findCorePapersAnswerOtherForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CorePapersAnswerOther> findCorePapersAnswerOtherList(String crpsaPapersUuid, String crpsaUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crpsaPapersUuid", crpsaPapersUuid);
		hashMap.put("crpsaUser", crpsaUser);
		return myBatisDAO.findForList(FIND_CorePapersAnswerOther_FOR_LISTS, hashMap);
	}

	private static final String FIND_CorePapersAnswerOther_FOR_PAGES="findCorePapersAnswerOtherForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CorePapersAnswerOther> findCorePapersAnswerOtherPage(String crpsaUserCode, String crpsaPapersName, String crpsaClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crpsaUserCode", crpsaUserCode);
		hashMap.put("crpsaPapersName", crpsaPapersName);
		hashMap.put("crpsaClass", crpsaClass);
		return myBatisDAO.findForPage(FIND_CorePapersAnswerOther_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CorePapersAnswerOther> findCorePapersAnswerOtherForListsByFilters(
			List<String> papersList, List<String> userList) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("papersList", papersList);
		hashMap.put("userList", userList);
		return myBatisDAO.findForList("findCorePapersAnswerOtherForListsByFilters", hashMap);
	}

	//<=================定制内容开始==============
	//==================定制内容结束==============>

}