package com.xiaoyu.lingdian.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
import com.xiaoyu.lingdian.entity.CoreWork;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreCourseService;
import com.xiaoyu.lingdian.service.CoreExercisesService;
import com.xiaoyu.lingdian.service.CoreWorkService;
import com.xiaoyu.lingdian.vo.CoreCourseVO;

@Controller
@RequestMapping(value="/coreCourse")
public class CoreCourseController extends BaseController {

	/**
	* 课程表
	*/
	@Autowired
	private CoreCourseService coreCourseService;
	
	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 强化训练表
	*/
	@Autowired
	private CoreWorkService coreWorkService;
	
	/**
	* 经典练习表
	*/
	@Autowired
	private CoreExercisesService coreExercisesService;
	
	/**
	* 添加
	*
	* @param crcreName 课程名
	* @param crcreContent 课程内容
	* @param crcreClass 所属年级
	* @return
	*/
	@RequestMapping(value="/add/coreCourse", method=RequestMethod.POST)
	public void addCoreCourse (String crcreName, String crcreContent, String crcreClass, HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin addCoreCourse");

		if (StringUtil.isEmpty(crcreName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[课程名称]不能为空!"), response);
			return;
		}
		if (StringUtil.isEmpty(crcreClass)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[所属年级]不能为空!"), response);
			return;
		}
		
		CoreCourse course = new CoreCourse();
		course.setCrcreName(crcreName);
		course.setCrcreClass(crcreClass);
		course = coreCourseService.getCoreCourseByName(course);
		if(null!=course){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "本年级的课程已添加,请查证!"), response);
			return;
		}
				CoreCourse coreCourse = new CoreCourse();
		String uuid = RandomUtil.generateString(16);
		coreCourse.setCrcreUuid(uuid);
		coreCourse.setCrcreName(crcreName);
		coreCourse.setCrcreContent(crcreContent);
		coreCourse.setCrcreClass(crcreClass);
		
		coreCourseService.insertCoreCourse(coreCourse);

		//添加强化训练
		CoreWork work = new CoreWork();
		work.setCrwokCourse(uuid);
		work.setCrwokClass(crcreClass);
		work = coreWorkService.getCoreWorkByCourse(work);
		if(null == work){
			CoreWork coreWork = new CoreWork();
			String workUuid = RandomUtil.generateString(16);
			coreWork.setCrwokUuid(workUuid);
			coreWork.setCrwokCourse(uuid);
			coreWork.setCrwokCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
			coreWork.setCrwokClass(crcreClass);
			coreWork.setCrwokCdate(new Date());
			coreWork.setCrwokUdate(new Date());

			coreWorkService.insertCoreWork(coreWork);
		}
		
		//添加经典联系
		CoreExercises exercises = new CoreExercises();
		exercises.setCrecsCourse(uuid);
		exercises.setCrecsClass(crcreClass);
		exercises = coreExercisesService.getCoreExercisesByCourse(exercises);
		if(null == exercises){
			CoreExercises coreExercises = new CoreExercises();
			String exercUuid = RandomUtil.generateString(16);
			coreExercises.setCrecsUuid(exercUuid);
			coreExercises.setCrecsCourse(uuid);
			coreExercises.setCrecsCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
			coreExercises.setCrecsClass(crcreClass);
			coreExercises.setCrecsCdate(new Date());
			coreExercises.setCrecsUdate(new Date());

			coreExercisesService.insertCoreExercises(coreExercises);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreCourseController]:end addCoreCourse");

	}

	/**
	* 修改课程
	*
	* @param crcreUuid 标识UUID
	* @param crcreName 课程名
	* @param crcreContent 课程内容
	* @param crcreClass 所属年级
	* @return
	*/
	@RequestMapping(value="/update/coreCourse", method=RequestMethod.POST)
	public void updateCoreCourse (String crcreUuid, String crcreName, String crcreContent, String crcreClass, HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin updateCoreCourse");
		if (StringUtil.isEmpty(crcreUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		if (StringUtil.isEmpty(crcreName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[课程名称]不能为空!"), response);
			return;
		}
		if (StringUtil.isEmpty(crcreClass)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[所属年级]不能为空!"), response);
			return;
		}
				CoreCourse coreCourse = new CoreCourse();
		coreCourse.setCrcreUuid(crcreUuid);
		coreCourse.setCrcreName(crcreName);
		coreCourse.setCrcreContent(crcreContent);
		coreCourse.setCrcreClass(crcreClass);
		coreCourseService.updateCoreCourse(coreCourse);

		//修改强化训练
		CoreWork coreWork = new CoreWork();
		coreWork.setCrwokCourse(crcreUuid);
		coreWork.setCrwokCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
		coreWork.setCrwokClass(crcreClass);
		coreWork.setCrwokUdate(new Date());

		coreWorkService.updateCoreWorkByCourse(coreWork);
		
		//修改经典练习
		CoreExercises coreExercises = new CoreExercises();
		coreExercises.setCrecsCourse(crcreUuid);
		coreExercises.setCrecsCourseName(coreCourse.getCrcreName() + coreCourse.getCrcreContent());
		coreExercises.setCrecsClass(crcreClass);
		coreExercises.setCrecsUdate(new Date());

		coreExercisesService.updateCoreExercisesByCourse(coreExercises);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreCourseController]:end updateCoreCourse");

	}
	
	/**
	* 删除
	*
	* @param crcreUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreCourse (String crcreUuid, HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin deleteCoreCourse");
		if (StringUtil.isEmpty(crcreUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreCourse coreCourse = new CoreCourse();
		coreCourse.setCrcreUuid(crcreUuid);

		coreCourseService.deleteCoreCourse(coreCourse);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreCourseController]:end deleteCoreCourse");

	}

	/**
	* 批量删除
	*
	* @param crcreUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreCourse (String crcreUuids, HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin deleteBatchCoreCourse");
		if (StringUtil.isEmpty(crcreUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crcreUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreCourseService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreCourseController]:end deleteBatchCoreCourse");

	}

	/**
	* 获取单个
	*
	* @param crcreUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreCourse (String crcreUuid, HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin viewsCoreCourse");
		if (StringUtil.isEmpty(crcreUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreCourse coreCourse = new CoreCourse();
		coreCourse.setCrcreUuid(crcreUuid);

		coreCourse = coreCourseService.getCoreCourse(coreCourse);

		CoreCourseVO coreCourseVO = new CoreCourseVO();
		coreCourseVO.convertPOToVO(coreCourse);
		if (!StringUtil.isEmpty(coreCourse.getCrcreClass())) {
			CoreClass coreClass=new CoreClass();
			coreClass.setCrcasUuid(coreCourse.getCrcreClass());
			coreClass=coreClassService.getCoreClass(coreClass);
			coreCourseVO.setCrcreClassName(coreClass.getCrcasName());
		}
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreCourseVO), response);
		logger.info("[CoreCourseController]:end viewsCoreCourse");

	}

	/**
	* 课程列表<List>,下拉框
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreCourseList (HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin findCoreCourseList");

		Page<CoreCourse> page = coreCourseService.findCoreCoursePage(null, null, 1, 1000);
		List<CoreCourseVO> vos = new ArrayList<CoreCourseVO>();
		CoreCourseVO vo;
		for (CoreCourse coreCourse : page.getResult()) {
			vo = new CoreCourseVO();
			vo.convertPOToVO(coreCourse);
			if (!StringUtil.isEmpty(coreCourse.getCrcreClass())) {
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreCourse.getCrcreClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrcreClassName(coreClass.getCrcasName());
			}
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "下拉框列表获取成功!", vos),response);
		logger.info("[CoreCourseController]:end findCoreCourseList");

	}
	
	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreCoursePage (String crcreName, String crcreClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin findCoreCoursePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreCourse> page = coreCourseService.findCoreCoursePage(crcreName, crcreClass, pageNum, pageSize);
		Page<CoreCourseVO> pageVO = new Page<CoreCourseVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreCourseVO> vos = new ArrayList<CoreCourseVO>();
		CoreCourseVO vo;
		for (CoreCourse coreCourse : page.getResult()) {
			vo = new CoreCourseVO();
			vo.convertPOToVO(coreCourse);
			if (!StringUtil.isEmpty(coreCourse.getCrcreClass())) {
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreCourse.getCrcreClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrcreClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreCourseController]:end findCoreCoursePage");

	}
	
	/**
	* 获取未添加列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd/wtj", method=RequestMethod.POST)
	public void findCoreCoursePageWTJ (String crgceGrade, String crcreName, String crcreClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreCourseController]:begin findCoreCoursePageWTJ");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 15;
		}
		Page<CoreCourse> page = coreCourseService.findCoreCoursePageWTJ(crgceGrade, crcreName, crcreClass, pageNum, pageSize);
		Page<CoreCourseVO> pageVO = new Page<CoreCourseVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreCourseVO> vos = new ArrayList<CoreCourseVO>();
		CoreCourseVO vo;
		for (CoreCourse coreCourse : page.getResult()) {
			vo = new CoreCourseVO();
			vo.convertPOToVO(coreCourse);
			if (!StringUtil.isEmpty(coreCourse.getCrcreClass())) {
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreCourse.getCrcreClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrcreClassName(coreClass.getCrcasName());
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreCourseController]:end findCoreCoursePageWTJ");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}