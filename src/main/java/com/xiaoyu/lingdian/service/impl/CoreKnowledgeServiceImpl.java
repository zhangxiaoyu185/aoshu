package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreKnowledge;

/**
* 知识点表
*/
@Service("coreKnowledgeService")
public class CoreKnowledgeServiceImpl implements CoreKnowledgeService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreKnowledge(CoreKnowledge coreKnowledge) {
		myBatisDAO.insert(coreKnowledge);
		return true;
	}

	@Override
	public boolean updateCoreKnowledge(CoreKnowledge coreKnowledge) {
		myBatisDAO.update(coreKnowledge);
		return true;
	}

	@Override
	public boolean deleteCoreKnowledge(CoreKnowledge coreKnowledge) {
		myBatisDAO.delete(coreKnowledge);
		return true;
	}

	private static final String DELETEBATCH_COREKNOWLEDGE_BY_IDS="deleteBatchCoreKnowledgeByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREKNOWLEDGE_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreKnowledge getCoreKnowledge(CoreKnowledge coreKnowledge) {
		return (CoreKnowledge) myBatisDAO.findForObject(coreKnowledge);
	}

	private static final String FIND_COREKNOWLEDGE_FOR_LISTS="findCoreKnowledgeForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreKnowledge> findCoreKnowledgeList() {
		return myBatisDAO.findForList(FIND_COREKNOWLEDGE_FOR_LISTS, null);
	}
	
	private static final String FIND_COREKNOWLEDGE_FOR_LISTS_BYS="findCoreKnowledgeForListsBys";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreKnowledge> findCoreKnowledgeListBys(String crkleCategory) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crkleCategory", crkleCategory);
		return myBatisDAO.findForList(FIND_COREKNOWLEDGE_FOR_LISTS_BYS, hashMap);
	}

	private static final String FIND_COREKNOWLEDGE_FOR_PAGES="findCoreKnowledgeForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreKnowledge> findCoreKnowledgePage(String crkleName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crkleName", crkleName);
		return myBatisDAO.findForPage(FIND_COREKNOWLEDGE_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}
	
	private static final String FIND_COREKNOWLEDGE_TOP="findCoreknowledgeTop";
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreKnowledge> findCoreKnowledgeTop(String crkleName,
			int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crkleName", crkleName);
		return myBatisDAO.findForPage(FIND_COREKNOWLEDGE_TOP, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}