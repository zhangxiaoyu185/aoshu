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
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.entity.CoreWork;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreCourseService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.service.CoreWorkService;
import com.xiaoyu.lingdian.vo.ContentList;
import com.xiaoyu.lingdian.vo.CoreWorkVO;
import com.xiaoyu.lingdian.vo.Problem;

@Controller
@RequestMapping(value="/coreWork")
public class CoreWorkController extends BaseController {

	/**
	* 强化训练表
	*/
	@Autowired
	private CoreWorkService coreWorkService;
	
	/**
	* 题目表
	*/
	@Autowired
	private CoreQuestionsService coreQuestionsService;
	
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
	* 修改题目
	*
	* @param crwokUuid 标识UUID
	* @param crwokContent 内容
	* @return
	*/
	@RequestMapping(value="/update/coreWork/by/ques", method=RequestMethod.POST)
	public void updateCoreWorkByQues (String crwokUuid, String crwokContent, HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin updateCoreWorkByQues");

		if (StringUtil.isEmpty(crwokUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreWork coreWork = new CoreWork();
		coreWork.setCrwokUuid(crwokUuid);
		coreWork.setCrwokContent(crwokContent);
		coreWork.setCrwokUdate(new Date());

		coreWorkService.updateCoreWork(coreWork);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreWorkController]:end updateCoreWorkByQues");

	}
	
	/**
	* 添加
	*
	* @param crwokCourse 所属课程
	* @param crwokClass 所属年级
	* @return
	*/
	@RequestMapping(value="/add/coreWork", method=RequestMethod.POST)
	public void addCoreWork (String crwokCourse, String crwokClass, HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin addCoreWork");

		CoreWork work = new CoreWork();
		work.setCrwokCourse(crwokCourse);
		work.setCrwokClass(crwokClass);
		work = coreWorkService.getCoreWorkByCourse(work);
		if(null!=work){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "本年级本课程的训练已添加,请查证!"), response);
			return;
		}
		CoreCourse coreCourse = new CoreCourse();
		coreCourse.setCrcreUuid(crwokCourse);
		coreCourse = coreCourseService.getCoreCourse(coreCourse);
		if (coreCourse == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该课程不存在!"), response);
			return;
		}		CoreWork coreWork = new CoreWork();
		String uuid = RandomUtil.generateString(16);
		coreWork.setCrwokUuid(uuid);
		coreWork.setCrwokCourse(crwokCourse);
		coreWork.setCrwokCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
		coreWork.setCrwokClass(crwokClass);
		coreWork.setCrwokCdate(new Date());
		coreWork.setCrwokUdate(new Date());

		coreWorkService.insertCoreWork(coreWork);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreWorkController]:end addCoreWork");

	}

	/**
	* 修改
	*
	* @param crwokUuid 标识UUID
	* @param crwokCourse 所属课程
	* @param crwokClass 所属年级
	* @return
	*/
	@RequestMapping(value="/update/coreWork", method=RequestMethod.POST)
	public void updateCoreWork (String crwokUuid, String crwokCourse, String crwokClass, HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin updateCoreWork");
		if (StringUtil.isEmpty(crwokUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreCourse coreCourse = new CoreCourse();
		coreCourse.setCrcreUuid(crwokCourse);
		coreCourse = coreCourseService.getCoreCourse(coreCourse);
		if (coreCourse == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该课程不存在!"), response);
			return;
		}		CoreWork coreWork = new CoreWork();
		coreWork.setCrwokUuid(crwokUuid);
		coreWork.setCrwokCourse(crwokCourse);
		coreWork.setCrwokCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
		coreWork.setCrwokClass(crwokClass);
		coreWork.setCrwokUdate(new Date());

		coreWorkService.updateCoreWork(coreWork);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreWorkController]:end updateCoreWork");

	}

	/**
	* 删除
	*
	* @param crwokUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreWork (String crwokUuid, HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin deleteCoreWork");
		if (StringUtil.isEmpty(crwokUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreWork coreWork = new CoreWork();
		coreWork.setCrwokUuid(crwokUuid);

		coreWorkService.deleteCoreWork(coreWork);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreWorkController]:end deleteCoreWork");

	}

	/**
	* 批量删除
	*
	* @param crwokUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreWork (String crwokUuids, HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin deleteBatchCoreWork");
		if (StringUtil.isEmpty(crwokUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crwokUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreWorkService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreWorkController]:end deleteBatchCoreWork");

	}

	/**
	* 获取单个
	*
	* @param crwokUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreWork (String crwokUuid, HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin viewsCoreWork");
		if (StringUtil.isEmpty(crwokUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreWork coreWork = new CoreWork();
		coreWork.setCrwokUuid(crwokUuid);

		coreWork = coreWorkService.getCoreWork(coreWork);

		CoreWorkVO coreWorkVO = new CoreWorkVO();
		coreWorkVO.convertPOToVO(coreWork);
		//获取所属年级名称
		if (!"".equals(coreWork.getCrwokClass())) {
			CoreClass coreClass = new CoreClass();
			coreClass.setCrcasUuid(coreWork.getCrwokClass());
			coreClass = coreClassService.getCoreClass(coreClass);
			coreWorkVO.setCrwokClassName(coreClass.getCrcasName());
		}

		if (StringUtil.isEmpty(coreWorkVO.getCrwokContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", coreWorkVO), response);
			return;
		}
				
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(coreWorkVO.getCrwokContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", coreWorkVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		coreWorkVO.setCrwokContent(JSON.toJSONString(contentList));
						writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreWorkVO), response);
		logger.info("[CoreWorkController]:end viewsCoreWork");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreWorkList (HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin findCoreWorkList");
		List<CoreWork> lists = coreWorkService.findCoreWorkList(null, null);
		List<CoreWorkVO> vos = new ArrayList<CoreWorkVO>();
		CoreWorkVO vo;
		for (CoreWork coreWork : lists) {
			vo = new CoreWorkVO();
			vo.convertPOToVO(coreWork);
			//获取所属年级名称
			if(!"".equals(coreWork.getCrwokClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreWork.getCrwokClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrwokClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreWorkController]:end findCoreWorkList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreWorkPage (String crwokCourse,String crwokClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreWorkController]:begin findCoreWorkPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreWork> page = coreWorkService.findCoreWorkPage(crwokCourse, crwokClass, pageNum, pageSize);
		Page<CoreWorkVO> pageVO = new Page<CoreWorkVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreWorkVO> vos = new ArrayList<CoreWorkVO>();
		CoreWorkVO vo;
		for (CoreWork coreWork : page.getResult()) {
			vo = new CoreWorkVO();
			vo.convertPOToVO(coreWork);
			//获取所属年级名称
			if(!"".equals(coreWork.getCrwokClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreWork.getCrwokClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrwokClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreWorkController]:end findCoreWorkPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}