package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户表
*/
@Service("coreUserService")
public class CoreUserServiceImpl implements CoreUserService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreUser(CoreUser coreUser) {
		myBatisDAO.insert(coreUser);
		return true;
	}

	@Override
	public boolean updateCoreUser(CoreUser coreUser) {
		myBatisDAO.update(coreUser);
		return true;
	}

	@Override
	public boolean deleteCoreUser(CoreUser coreUser) {
		myBatisDAO.delete(coreUser);
		return true;
	}

	private static final String DELETEBATCH_COREUSER_BY_IDS="deleteBatchCoreUserByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREUSER_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreUser getCoreUser(CoreUser coreUser) {
		return (CoreUser) myBatisDAO.findForObject(coreUser);
	}

	private static final String FIND_COREUSER_FOR_LISTS="findCoreUserForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreUser> findCoreUserList(String crusrGrade) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrGrade", crusrGrade);
		return myBatisDAO.findForList(FIND_COREUSER_FOR_LISTS, hashMap);
	}

	private static final String FIND_COREUSER_FOR_PAGES="findCoreUserForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreUser> findCoreUserPage(String crusrName, String crusrGrade, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrName", crusrName);
		hashMap.put("crusrGrade", crusrGrade);
		return myBatisDAO.findForPage(FIND_COREUSER_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	private static final String GET_COREUSER_BY_NAME="getCoreUserByName";

	@Override
	public CoreUser getCoreUserByName(CoreUser coreUser) {
		return (CoreUser) myBatisDAO.findForObject(GET_COREUSER_BY_NAME,coreUser);
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}