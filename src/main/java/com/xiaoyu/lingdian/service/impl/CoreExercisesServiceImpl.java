package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreExercises;

/**
* 经典练习表
*/
@Service("coreExercisesService")
public class CoreExercisesServiceImpl implements CoreExercisesService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	private static final String GET_COREEXERCISES_BY_COURSE="getCoreExercisesByCourse";
	@Override
	public CoreExercises getCoreExercisesByCourse(CoreExercises coreExercises) {
		return (CoreExercises) myBatisDAO.findForObject(GET_COREEXERCISES_BY_COURSE, coreExercises);
	}
	
	@Override
	public boolean insertCoreExercises(CoreExercises coreExercises) {
		myBatisDAO.insert(coreExercises);
		return true;
	}

	@Override
	public boolean updateCoreExercises(CoreExercises coreExercises) {
		myBatisDAO.update(coreExercises);
		return true;
	}

	@Override
	public boolean deleteCoreExercises(CoreExercises coreExercises) {
		myBatisDAO.delete(coreExercises);
		return true;
	}

	private static final String DELETEBATCH_COREEXERCISES_BY_IDS="deleteBatchCoreExercisesByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_COREEXERCISES_BY_IDS,hashMap);
		return true;
	}

	@Override
	public CoreExercises getCoreExercises(CoreExercises coreExercises) {
		return (CoreExercises) myBatisDAO.findForObject(coreExercises);
	}

	private static final String FIND_COREEXERCISES_FOR_LISTS="findCoreExercisesForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreExercises> findCoreExercisesList(String crecsCourse,String crecsClass) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crecsCourse", crecsCourse);
		hashMap.put("crecsClass", crecsClass);
		return myBatisDAO.findForList(FIND_COREEXERCISES_FOR_LISTS, hashMap);
	}

	private static final String FIND_COREEXERCISES_FOR_PAGES="findCoreExercisesForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreExercises> findCoreExercisesPage(String crecsCourse,String crecsClass, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crecsCourse", crecsCourse);
		hashMap.put("crecsClass", crecsClass);
		return myBatisDAO.findForPage(FIND_COREEXERCISES_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public boolean updateCoreExercisesByCourse(CoreExercises coreExercises) {
		myBatisDAO.update("updateCoreExercisesByCourse", coreExercises);
		return true;
	}

	@Override
	public boolean deleteCoreExercisesByCourse(CoreExercises coreExercises) {
		myBatisDAO.delete("deleteCoreExercisesByCourse", coreExercises);
		return true;
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}