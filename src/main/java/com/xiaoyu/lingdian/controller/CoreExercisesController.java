package com.xiaoyu.lingdian.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreClass;
import com.xiaoyu.lingdian.entity.CoreCourse;
import com.xiaoyu.lingdian.entity.CoreExercises;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreCourseService;
import com.xiaoyu.lingdian.service.CoreExercisesService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.vo.ContentList;
import com.xiaoyu.lingdian.vo.CoreExercisesVO;
import com.xiaoyu.lingdian.vo.Problem;

@Controller
@RequestMapping(value="/coreExercises")
public class CoreExercisesController extends BaseController {

	/**
	* 经典练习表
	*/
	@Autowired
	private CoreExercisesService coreExercisesService;
	
	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 课程表
	*/
	@Autowired
	private CoreCourseService coreCourseService;
	
	/**
	* 题目表
	*/
	@Autowired
	private CoreQuestionsService coreQuestionsService;
	
	/**
	* 修改题目
	*
	* @param crecsUuid 标识UUID
	* @param crecsContent 内容
	* @return
	*/
	@RequestMapping(value="/update/coreExercises/by/ques", method=RequestMethod.POST)
	public void updateCoreExercisesByQues (String crecsUuid, String crecsContent, HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin updateCoreExercisesByQues");

		if (StringUtil.isEmpty(crecsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreExercises coreExercises = new CoreExercises();
		coreExercises.setCrecsUuid(crecsUuid);
		coreExercises.setCrecsContent(crecsContent);
		coreExercises.setCrecsUdate(new Date());

		coreExercisesService.updateCoreExercises(coreExercises);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreExercisesController]:end updateCoreExercisesByQues");

	}
	
	/**
	* 添加
	*
	* @param crecsCourse 所属课程
	* @param crecsClass 所属年级
	* @return
	*/
	@RequestMapping(value="/add/coreExercises", method=RequestMethod.POST)
	public void addCoreExercises (String crecsCourse, String crecsClass, HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin addCoreExercises");
		
		CoreExercises exercises = new CoreExercises();
		exercises.setCrecsCourse(crecsCourse);
		exercises.setCrecsClass(crecsClass);
		exercises = coreExercisesService.getCoreExercisesByCourse(exercises);
		if(null!=exercises){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "本年级本课程的练习已添加,请查证!"), response);
			return;		}
		
		CoreCourse coreCourse = new CoreCourse();
		coreCourse.setCrcreUuid(crecsCourse);
		coreCourse = coreCourseService.getCoreCourse(coreCourse);
		if (coreCourse == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该课程不存在!"), response);
			return;
		}
		CoreExercises coreExercises = new CoreExercises();
		String uuid = RandomUtil.generateString(16);
		coreExercises.setCrecsUuid(uuid);
		coreExercises.setCrecsCourse(crecsCourse);
		coreExercises.setCrecsCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
		coreExercises.setCrecsClass(crecsClass);
		coreExercises.setCrecsCdate(new Date());
		coreExercises.setCrecsUdate(new Date());

		coreExercisesService.insertCoreExercises(coreExercises);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreExercisesController]:end addCoreExercises");

	}

	/**
	* 修改
	*
	* @param crecsUuid 标识UUID
	* @param crecsCourse 所属课程
	* @param crecsClass 所属年级
	* @return
	*/
	@RequestMapping(value="/update/coreExercises", method=RequestMethod.POST)
	public void updateCoreExercises (String crecsUuid, String crecsCourse, String crecsClass, HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin updateCoreExercises");
		if (StringUtil.isEmpty(crecsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreCourse coreCourse = new CoreCourse();
		coreCourse.setCrcreUuid(crecsCourse);
		coreCourse = coreCourseService.getCoreCourse(coreCourse);
		if (coreCourse == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该课程不存在!"), response);
			return;
		}
				CoreExercises coreExercises = new CoreExercises();
		coreExercises.setCrecsUuid(crecsUuid);
		coreExercises.setCrecsCourse(crecsCourse);
		coreExercises.setCrecsCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
		coreExercises.setCrecsClass(crecsClass);
		coreExercises.setCrecsUdate(new Date());

		coreExercisesService.updateCoreExercises(coreExercises);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreExercisesController]:end updateCoreExercises");

	}

	/**
	* 删除
	*
	* @param crecsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreExercises (String crecsUuid, HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin deleteCoreExercises");
		if (StringUtil.isEmpty(crecsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreExercises coreExercises = new CoreExercises();
		coreExercises.setCrecsUuid(crecsUuid);

		coreExercisesService.deleteCoreExercises(coreExercises);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreExercisesController]:end deleteCoreExercises");

	}

	/**
	* 批量删除
	*
	* @param crecsUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreExercises (String crecsUuids, HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin deleteBatchCoreExercises");
		if (StringUtil.isEmpty(crecsUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crecsUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreExercisesService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreExercisesController]:end deleteBatchCoreExercises");

	}

	/**
	* 获取单个
	*
	* @param crecsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreExercises (String crecsUuid, HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin viewsCoreExercises");
		if (StringUtil.isEmpty(crecsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreExercises coreExercises = new CoreExercises();
		coreExercises.setCrecsUuid(crecsUuid);

		coreExercises = coreExercisesService.getCoreExercises(coreExercises);

		CoreExercisesVO coreExercisesVO = new CoreExercisesVO();
		coreExercisesVO.convertPOToVO(coreExercises);
		// 获取所属年级名称
		if (!"".equals(coreExercises.getCrecsClass())) {
			CoreClass coreClass = new CoreClass();
			coreClass.setCrcasUuid(coreExercises.getCrecsClass());
			coreClass = coreClassService.getCoreClass(coreClass);
			coreExercisesVO.setCrecsClassName(coreClass.getCrcasName());
		}

		if (StringUtil.isEmpty(coreExercisesVO.getCrecsContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", coreExercisesVO), response);
			return;
		}
		
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(coreExercisesVO.getCrecsContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", coreExercisesVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		coreExercisesVO.setCrecsContent(JSON.toJSONString(contentList));		
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreExercisesVO), response);
		logger.info("[CoreExercisesController]:end viewsCoreExercises");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreExercisesList (HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin findCoreExercisesList");
		List<CoreExercises> lists = coreExercisesService.findCoreExercisesList(null, null);
		List<CoreExercisesVO> vos = new ArrayList<CoreExercisesVO>();
		CoreExercisesVO vo;
		for (CoreExercises coreExercises : lists) {
			vo = new CoreExercisesVO();
			vo.convertPOToVO(coreExercises);
			//获取所属年级名称
			if(!"".equals(coreExercises.getCrecsClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreExercises.getCrecsClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrecsClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreExercisesController]:end findCoreExercisesList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreExercisesPage (String crecsCourse,String crecsClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreExercisesController]:begin findCoreExercisesPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreExercises> page = coreExercisesService.findCoreExercisesPage(crecsCourse, crecsClass, pageNum, pageSize);
		Page<CoreExercisesVO> pageVO = new Page<CoreExercisesVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreExercisesVO> vos = new ArrayList<CoreExercisesVO>();
		CoreExercisesVO vo;
		for (CoreExercises coreExercises : page.getResult()) {
			vo = new CoreExercisesVO();
			vo.convertPOToVO(coreExercises);
			//获取所属年级名称
			if(!"".equals(coreExercises.getCrecsClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreExercises.getCrecsClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrecsClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreExercisesController]:end findCoreExercisesPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}