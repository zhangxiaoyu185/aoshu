package com.xiaoyu.lingdian.controller;

import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.DateUtil;
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
import com.xiaoyu.lingdian.entity.CoreExercises;
import com.xiaoyu.lingdian.entity.CoreExercisesAnswer;
import com.xiaoyu.lingdian.entity.CoreGradeCourse;
import com.xiaoyu.lingdian.entity.CoreWork;
import com.xiaoyu.lingdian.entity.CoreWorkAnswer;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreExercisesAnswerService;
import com.xiaoyu.lingdian.service.CoreExercisesService;
import com.xiaoyu.lingdian.service.CoreGradeCourseService;
import com.xiaoyu.lingdian.service.CoreWorkAnswerService;
import com.xiaoyu.lingdian.service.CoreWorkService;
import com.xiaoyu.lingdian.vo.CoreGradeCourseVO;

@Controller
@RequestMapping(value="/coreGradeCourse")
public class CoreGradeCourseController extends BaseController {

	/**
	* 班级课程表
	*/
	@Autowired
	private CoreGradeCourseService coreGradeCourseService;
	
	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 练习检测表
	*/
	@Autowired
	private CoreExercisesAnswerService coreExercisesAnswerService;
	
	/**
	* 练习表
	*/
	@Autowired
	private CoreExercisesService coreExercisesService;
	
	/**
	* 训练表
	*/
	@Autowired
	private CoreWorkService coreWorkService;
	
	/**
	* 强化训练表
	*/
	@Autowired
	private CoreWorkAnswerService coreWorkAnswerService;
	
	/**
	* 添加
	*
	* @param crgceGrade 班级UUID
	* @param crgceCourse 课程UUID
	* @param crcreClass 所属年级
	* @return
	*/
	@RequestMapping(value="/add/coreGradeCourse", method=RequestMethod.POST)
	public void addCoreGradeCourse (String crgceGrade, String crgceCourse, String crcreClass, HttpServletResponse response) {
		logger.info("[CoreGradeCourseController]:begin addCoreGradeCourse");

		//判断该班级下是否已经拥有该课程权限
		if (coreGradeCourseService.getCoreGradeCourseByGradeCourse(crgceGrade, crgceCourse) != null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该班级下已拥有该课程权限!"),response);
			return;
		}
				CoreGradeCourse coreGradeCourse = new CoreGradeCourse();
		String uuid = RandomUtil.generateString(16);
		coreGradeCourse.setCrgceUuid(uuid);
		coreGradeCourse.setCrgceGrade(crgceGrade);
		coreGradeCourse.setCrgceCourse(crgceCourse);
		coreGradeCourse.setCrgceGqsj("0");
		//查找数据库中该班级年级下最大的排序号
		int max = coreGradeCourseService.getMaxOrdByGradeCourseClass(crgceGrade, crcreClass);
		coreGradeCourse.setCrgceOrd(max + 1);

		coreGradeCourseService.insertCoreGradeCourse(coreGradeCourse);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreGradeCourseController]:end addCoreGradeCourse");

	}

	/**
	* 修改
	*
	* @param crgceUuid 标识UUID
	* @param crgceOrd 排序号
	* @param crgceGqsj 过期时间
	* @return
	*/
	@RequestMapping(value="/update/coreGradeCourse", method=RequestMethod.POST)
	public void updateCoreGradeCourse (String crgceUuid, Integer crgceOrd, String crgceGqsj, HttpServletResponse response) {
		logger.info("[CoreGradeCourseController]:begin updateCoreGradeCourse");
		if (StringUtil.isEmpty(crgceUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGradeCourse coreGradeCourse = new CoreGradeCourse();
		coreGradeCourse.setCrgceUuid(crgceUuid);
		coreGradeCourse.setCrgceOrd(crgceOrd);
		coreGradeCourse.setCrgceGqsj(crgceGqsj);
		
		coreGradeCourseService.updateCoreGradeCourse(coreGradeCourse);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreGradeCourseController]:end updateCoreGradeCourse");

	}

	/**
	* 删除
	*
	* @param crgceUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreGradeCourse (String crgceUuid, HttpServletResponse response) {
		logger.info("[CoreGradeCourseController]:begin deleteCoreGradeCourse");
		if (StringUtil.isEmpty(crgceUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGradeCourse coreGradeCourse = new CoreGradeCourse();
		coreGradeCourse.setCrgceUuid(crgceUuid);

		coreGradeCourseService.deleteCoreGradeCourse(coreGradeCourse);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreGradeCourseController]:end deleteCoreGradeCourse");

	}

	/**
	* 批量删除
	*
	* @param crgceUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreGradeCourse (String crgceUuids, HttpServletResponse response) {
		logger.info("[CoreGradeCourseController]:begin deleteBatchCoreGradeCourse");
		if (StringUtil.isEmpty(crgceUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crgceUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreGradeCourseService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreGradeCourseController]:end deleteBatchCoreGradeCourse");

	}

	/**
	* 获取单个
	*
	* @param crgceUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreGradeCourse (String crgceUuid, HttpServletResponse response) {
		logger.info("[CoreGradeCourseController]:begin viewsCoreGradeCourse");
		if (StringUtil.isEmpty(crgceUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGradeCourse coreGradeCourse = new CoreGradeCourse();
		coreGradeCourse.setCrgceUuid(crgceUuid);

		coreGradeCourse = coreGradeCourseService.getCoreGradeCourse(coreGradeCourse);

		CoreGradeCourseVO coreGradeCourseVO = new CoreGradeCourseVO();
		coreGradeCourseVO.convertPOToVO(coreGradeCourse);
		//获取所属年级名称
		if (!StringUtil.isEmpty(coreGradeCourse.getCrcreClass())) {
			CoreClass coreClass=new CoreClass();
			coreClass.setCrcasUuid(coreGradeCourse.getCrcreClass());
			coreClass=coreClassService.getCoreClass(coreClass);
			coreGradeCourseVO.setCrcreClassName(coreClass.getCrcasName());	
		}
						writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreGradeCourseVO), response);
		logger.info("[CoreGradeCourseController]:end viewsCoreGradeCourse");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crgceGrade 所属班级
	* @param crcreUser 用户UUID（前端）
	* @param crcreName 课程名
	* @param crcreClass 所属年级
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreGradeCoursePage (String crgceGrade, String crcreUser, String crcreName, String crcreClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreGradeCourseController]:begin findCoreGradeCoursePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		
		if (StringUtil.isEmpty(crgceGrade)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属班级不能为空!"), response);
			return;
		}
		
		Page<CoreGradeCourse> page = coreGradeCourseService.findCoreGradeCoursePage(crgceGrade, crcreName, crcreClass, pageNum, pageSize);
		Page<CoreGradeCourseVO> pageVO = new Page<CoreGradeCourseVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreGradeCourseVO> vos = new ArrayList<CoreGradeCourseVO>();
		CoreGradeCourseVO vo;
		for (CoreGradeCourse coreGradeCourse : page.getResult()) {
			vo = new CoreGradeCourseVO();
			vo.convertPOToVO(coreGradeCourse);
			if (!StringUtil.isEmpty(coreGradeCourse.getCrcreClass())) {
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreGradeCourse.getCrcreClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrcreClassName(coreClass.getCrcasName());	
			}
			vo.setIsWork("N");
			//查询对应经典联系UUID
			List<CoreExercises> exercisesList = coreExercisesService.findCoreExercisesList(vo.getCrcreUuid(), vo.getCrcreClass());
			if (exercisesList != null && exercisesList.size() > 0) {
				if (exercisesList.get(0) != null) {
					String crecsUuid = exercisesList.get(0).getCrecsUuid();
					if (!StringUtil.isEmpty(crecsUuid)) {
						vo.setCrecsUuid(crecsUuid);
						//查询强化训练是否可以点击
						List<CoreExercisesAnswer> answerList = coreExercisesAnswerService.findCoreExercisesAnswerList(crecsUuid, crcreUser);
						if (answerList != null && answerList.size() > 0) {
							if (answerList.get(0) != null) {
								Integer cresaState = answerList.get(0).getCresaState();
								if (null != cresaState && cresaState == 0) {
									vo.setIsWork("Y");
								}
							}
						}
					}
				}
			}
			//查询对应强化训练UUID
			List<CoreWork> workList = coreWorkService.findCoreWorkList(vo.getCrcreUuid(), vo.getCrcreClass());
			if (workList != null && workList.size() > 0) {
				if (workList.get(0) != null) {
					String crwokUuid = workList.get(0).getCrwokUuid();
					if (!StringUtil.isEmpty(crwokUuid)) {
						vo.setCrwokUuid(crwokUuid);
						//查询强化训练检测UUID
						List<CoreWorkAnswer> workAnswerList = coreWorkAnswerService.findCoreWorkAnswerList(crwokUuid, crcreUser);
						if (workAnswerList != null && workAnswerList.size() > 0) {
							if (workAnswerList.get(0) != null) {
								vo.setCrwokScore(workAnswerList.get(0).getCrwkaScore());
								vo.setAnswerDate(DateUtil.formatTimesTampDate(workAnswerList.get(0).getCrwkaUdate()));
							}
						}
					}
				}
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreGradeCourseController]:end findCoreGradeCoursePage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}