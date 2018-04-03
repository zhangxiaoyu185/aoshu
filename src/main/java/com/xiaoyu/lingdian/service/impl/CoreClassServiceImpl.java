package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreClass;

/**
* 年级表
*/
@Service("coreClassService")
public class CoreClassServiceImpl implements CoreClassService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreClass(CoreClass coreClass) {
		myBatisDAO.insert(coreClass);
		return true;
	}

	@Override
	public boolean updateCoreClass(CoreClass coreClass) {
		myBatisDAO.update(coreClass);
		return true;
	}

	@Override
	public boolean deleteCoreClass(CoreClass coreClass) {
		myBatisDAO.delete(coreClass);
		return true;
	}

	private static final String DELETEBATCH_CORECLASS_BY_IDS="deleteBatchCoreClassByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_CORECLASS_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreClass getCoreClass(CoreClass coreClass) {
		return (CoreClass) myBatisDAO.findForObject(coreClass);
	}

	private static final String FIND_CORECLASS_FOR_LISTS="findCoreClassForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreClass> findCoreClassList() {
		return myBatisDAO.findForList(FIND_CORECLASS_FOR_LISTS, null);
	}

	private static final String FIND_CORECLASS_FOR_PAGES="findCoreClassForPages";
	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreClass> findCoreClassPage(String crcasName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crcasName", crcasName);
		return myBatisDAO.findForPage(FIND_CORECLASS_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}
	
	private static final String FIND_CORECLASS_BY_IDS = "findCoreClassByIds";
	@SuppressWarnings("unchecked")
	@Override
	public List<CoreClass> findCoreClassByIds(List<String> ids) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", ids);
		return myBatisDAO.findForList(FIND_CORECLASS_BY_IDS, hashMap);
	}

	private static final String FIND_CORECLASS_FOR_LISTS_NOTHING = "findCoreClassForListsNothing";
	@SuppressWarnings("unchecked")
	@Override
	public List<CoreClass> findCoreClassForListsNothing() {
		return myBatisDAO.findForList(FIND_CORECLASS_FOR_LISTS_NOTHING, null);
	}

}