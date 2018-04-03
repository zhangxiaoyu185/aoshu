package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreCategory;

/**
* 类别表
*/
@Service("coreCategoryService")
public class CoreCategoryServiceImpl implements CoreCategoryService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreCategory(CoreCategory coreCategory) {
		myBatisDAO.insert(coreCategory);
		return true;
	}

	@Override
	public boolean updateCoreCategory(CoreCategory coreCategory) {
		myBatisDAO.update(coreCategory);
		return true;
	}

	@Override
	public boolean deleteCoreCategory(CoreCategory coreCategory) {
		myBatisDAO.delete(coreCategory);
		return true;
	}

	private static final String DELETEBATCH_CORECATEGORY_BY_IDS="deleteBatchCoreCategoryByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_CORECATEGORY_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreCategory getCoreCategory(CoreCategory coreCategory) {
		return (CoreCategory) myBatisDAO.findForObject(coreCategory);
	}

	private static final String FIND_CORECATEGORY_FOR_LISTS="findCoreCategoryForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreCategory> findCoreCategoryList() {
		return myBatisDAO.findForList(FIND_CORECATEGORY_FOR_LISTS, null);
	}

	private static final String FIND_CORECATEGORY_FOR_PAGES="findCoreCategoryForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreCategory> findCoreCategoryPage(String crceyName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crceyName", crceyName);
		return myBatisDAO.findForPage(FIND_CORECATEGORY_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}