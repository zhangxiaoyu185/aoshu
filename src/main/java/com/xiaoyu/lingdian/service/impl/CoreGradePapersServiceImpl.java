package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreGradePapersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreGradePapers;

/**
* 班级模拟试卷表
*/
@Service("coreGradePapersService")
public class CoreGradePapersServiceImpl implements CoreGradePapersService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreGradePapers(CoreGradePapers coreGradePapers) {
		myBatisDAO.insert(coreGradePapers);
		return true;
	}

	@Override
	public boolean updateCoreGradePapers(CoreGradePapers coreGradePapers) {
		myBatisDAO.update(coreGradePapers);
		return true;
	}

	@Override
	public boolean deleteCoreGradePapers(CoreGradePapers coreGradePapers) {
		myBatisDAO.delete(coreGradePapers);
		return true;
	}

	private static final String DELETEBATCH_COREGRADEPAPERS_BY_IDS="deleteBatchCoreGradePapersByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREGRADEPAPERS_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreGradePapers getCoreGradePapers(CoreGradePapers coreGradePapers) {
		return (CoreGradePapers) myBatisDAO.findForObject(coreGradePapers);
	}

	private static final String FIND_COREGRADEPAPERS_FOR_PAGES="findCoreGradePapersForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreGradePapers> findCoreGradePapersPage(String crgpsGrade, String crpesClass, String crpesName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgpsGrade", crgpsGrade);
		hashMap.put("crpesClass", crpesClass);
		hashMap.put("crpesName", crpesName);
		return myBatisDAO.findForPage(FIND_COREGRADEPAPERS_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public CoreGradePapers getCoreGradePapersByGradePapers(String crgpsGrade, String crgpsPapers) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgpsGrade", crgpsGrade);
		hashMap.put("crgpsPapers", crgpsPapers);
		return (CoreGradePapers) myBatisDAO.findForObject("getCoreGradePapersByGradePapers", hashMap);
	}

	@Override
	public int getMaxOrdByGradePapersClass(String crgpsGrade, String crpesClass) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgpsGrade", crgpsGrade);
		hashMap.put("crpesClass", crpesClass);
		Integer max = (Integer) myBatisDAO.findForObject("getMaxOrdByGradePapersClass", hashMap);
		if (null == max) {
			return 0;
		} else {
			return max;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreGradePapers> findCoreGradePapersList(String crgpsGrade,
			String crpesClass) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgpsGrade", crgpsGrade);
		hashMap.put("crpesClass", crpesClass);
		return myBatisDAO.findForList(FIND_COREGRADEPAPERS_FOR_PAGES, hashMap);
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}