package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreDirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreDir;

/**
* 图片目录表
*/
@Service("coreDirService")
public class CoreDirServiceImpl implements CoreDirService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreDir(CoreDir coreDir) {
		myBatisDAO.insert(coreDir);
		return true;
	}

	@Override
	public boolean updateCoreDir(CoreDir coreDir) {
		myBatisDAO.update(coreDir);
		return true;
	}

	@Override
	public boolean deleteCoreDir(CoreDir coreDir) {
		myBatisDAO.delete(coreDir);
		return true;
	}

	private static final String DELETEBATCH_COREDIR_BY_IDS="deleteBatchCoreDirByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREDIR_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreDir getCoreDir(CoreDir coreDir) {
		return (CoreDir) myBatisDAO.findForObject(coreDir);
	}

	private static final String FIND_COREDIR_FOR_LISTS="findCoreDirForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreDir> findCoreDirList() {
		return myBatisDAO.findForList(FIND_COREDIR_FOR_LISTS, null);
	}

	private static final String FIND_COREDIR_FOR_PAGES="findCoreDirForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreDir> findCoreDirPage(String crdirName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crdirName", crdirName);
		return myBatisDAO.findForPage(FIND_COREDIR_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	private static final String GET_COREDIR_BY_NAME="getCoreDirByName";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoreDir> getCoreDirByName(String crdirName) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crdirName", crdirName);
		return myBatisDAO.findForList(GET_COREDIR_BY_NAME, hashMap);
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}