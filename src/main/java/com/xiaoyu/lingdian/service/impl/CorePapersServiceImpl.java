package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CorePapersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CorePapers;

/**
* 模拟试卷表
*/
@Service("corePapersService")
public class CorePapersServiceImpl implements CorePapersService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCorePapers(CorePapers corePapers) {
		myBatisDAO.insert(corePapers);
		return true;
	}

	@Override
	public boolean updateCorePapers(CorePapers corePapers) {
		myBatisDAO.update(corePapers);
		return true;
	}

	@Override
	public boolean deleteCorePapers(CorePapers corePapers) {
		myBatisDAO.delete(corePapers);
		return true;
	}

	private static final String DELETEBATCH_COREPAPERS_BY_IDS="deleteBatchCorePapersByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREPAPERS_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CorePapers getCorePapers(CorePapers corePapers) {
		return (CorePapers) myBatisDAO.findForObject(corePapers);
	}

	private static final String FIND_COREPAPERS_FOR_PAGES="findCorePapersForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CorePapers> findCorePapersPage(String crpesClass, String crpesName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crpesClass", crpesClass);
		hashMap.put("crpesName", crpesName);
		return myBatisDAO.findForPage(FIND_COREPAPERS_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CorePapers> findCorePapersPageWTJ(String crgpsGrade,
			String crpesClass, String crpesName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgpsGrade", crgpsGrade);
		hashMap.put("crpesClass", crpesClass);
		hashMap.put("crpesName", crpesName);
		return myBatisDAO.findForPage("findCorePapersForPagesWTJ", new PageRequest(pageNum, pageSize, hashMap));
	}

}