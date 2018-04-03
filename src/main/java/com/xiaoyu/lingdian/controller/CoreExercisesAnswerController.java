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
import com.xiaoyu.lingdian.entity.CoreExercises;
import com.xiaoyu.lingdian.entity.CoreExercisesAnswer;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreExercisesAnswerService;
import com.xiaoyu.lingdian.service.CoreExercisesService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.vo.ContentList;
import com.xiaoyu.lingdian.vo.CoreExercisesAnswerVO;
import com.xiaoyu.lingdian.vo.Problem;

@Controller
@RequestMapping(value="/coreExercisesAnswer")
public class CoreExercisesAnswerController extends BaseController {

	/**
	* 练习回答表
	*/
	@Autowired
	private CoreExercisesAnswerService coreExercisesAnswerService;
	
	/**
	* 练习表
	*/
	@Autowired
	private CoreExercisesService coreExercisesService;
	
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
	* 经典练习特殊判题
	* 
	* @param cresaExercUuid 练习UUID
	* @param cresaCourseName 课程名称
	* @param cresaUser 用户UUID
	* @param cresaClass 所属年级
	* @param cresaClassName 所属年级名称
	* @return
	*/
	@RequestMapping(value="/add/coreExercisesAnswer/other", method=RequestMethod.POST)
	public void addCoreExercisesAnswerOther (String cresaExercUuid, String cresaCourseName, String cresaUser, String cresaClass, String cresaClassName, HttpServletResponse response) {
		logger.info("[CoreExercisesAnswerController]:begin addCoreExercisesAnswerOther");
		CoreExercises coreExercises = new CoreExercises();
		coreExercises.setCrecsUuid(cresaExercUuid);
		coreExercises = coreExercisesService.getCoreExercises(coreExercises);
		if (coreExercises == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该经典练习不存在!"),response);
			return;
		}	
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(coreExercises.getCrecsContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "解析题目串失败!"),response);
			return;
		}
		for (Problem problem : problemList) {
			problem.setResult(problem.getCrqtsAnswer());
			problem.setJudge(0);
		}
	
		//根据用户和试卷UUID查询是否是重做
		String uuid = "";
		List<CoreExercisesAnswer> answerList = coreExercisesAnswerService.findCoreExercisesAnswerList(cresaExercUuid, cresaUser);
		CoreExercisesAnswer coreExercisesAnswer = new CoreExercisesAnswer();
		contentList.setProblem(problemList);
		if (null != answerList && answerList.size() > 0) { //更新
			uuid = answerList.get(0).getCresaUuid();
			coreExercisesAnswer.setCresaUuid(uuid);
			coreExercisesAnswer.setCresaExercUuid(cresaExercUuid);
			coreExercisesAnswer.setCresaCourseName(cresaCourseName);
			coreExercisesAnswer.setCresaUser(cresaUser);
			coreExercisesAnswer.setCresaContent(JSON.toJSONString(contentList));
			coreExercisesAnswer.setCresaClass(cresaClass);
			coreExercisesAnswer.setCresaState(0);
			coreExercisesAnswer.setCresaUdate(new Date());
			
			coreExercisesAnswerService.updateCoreExercisesAnswer(coreExercisesAnswer);
		} else { //添加
			uuid = RandomUtil.generateString(16);
			coreExercisesAnswer.setCresaUuid(uuid);
			coreExercisesAnswer.setCresaExercUuid(cresaExercUuid);
			coreExercisesAnswer.setCresaCourseName(cresaCourseName);
			coreExercisesAnswer.setCresaUser(cresaUser);
			coreExercisesAnswer.setCresaContent(JSON.toJSONString(contentList));
			coreExercisesAnswer.setCresaClass(cresaClass);
			coreExercisesAnswer.setCresaState(0);
			coreExercisesAnswer.setCresaCdate(new Date());
			coreExercisesAnswer.setCresaUdate(new Date());
			
			coreExercisesAnswerService.insertCoreExercisesAnswer(coreExercisesAnswer);
		}		

		//经典练习不需要返回数据
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "操作成功!"),response);
		logger.info("[CoreExercisesAnswerController]:end addCoreExercisesAnswerOther");

	}

	/**
	* 删除
	*
	* @param cresaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreExercisesAnswer (String cresaUuid, HttpServletResponse response) {
		logger.info("[CoreExercisesAnswerController]:begin deleteCoreExercisesAnswer");
		if (StringUtil.isEmpty(cresaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreExercisesAnswer coreExercisesAnswer = new CoreExercisesAnswer();
		coreExercisesAnswer.setCresaUuid(cresaUuid);

		coreExercisesAnswerService.deleteCoreExercisesAnswer(coreExercisesAnswer);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreExercisesAnswerController]:end deleteCoreExercisesAnswer");

	}

	/**
	* 批量删除
	*
	* @param cresaUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreExercisesAnswer (String cresaUuids, HttpServletResponse response) {
		logger.info("[CoreExercisesAnswerController]:begin deleteBatchCoreExercisesAnswer");
		if (StringUtil.isEmpty(cresaUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=cresaUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreExercisesAnswerService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreExercisesAnswerController]:end deleteBatchCoreExercisesAnswer");

	}

	/**
	* 获取单个
	*
	* @param cresaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreExercisesAnswer (String cresaUuid, HttpServletResponse response) {
		logger.info("[CoreExercisesAnswerController]:begin viewsCoreExercisesAnswer");
		if (StringUtil.isEmpty(cresaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreExercisesAnswer coreExercisesAnswer = new CoreExercisesAnswer();
		coreExercisesAnswer.setCresaUuid(cresaUuid);

		coreExercisesAnswer = coreExercisesAnswerService.getCoreExercisesAnswer(coreExercisesAnswer);

		CoreExercisesAnswerVO coreExercisesAnswerVO = new CoreExercisesAnswerVO();
		coreExercisesAnswerVO.convertPOToVO(coreExercisesAnswer);
		// 获取所属年级名称
		if (!"".equals(coreExercisesAnswer.getCresaClass())) {
			CoreClass coreClass = new CoreClass();
			coreClass.setCrcasUuid(coreExercisesAnswer.getCresaClass());
			coreClass = coreClassService.getCoreClass(coreClass);
			coreExercisesAnswerVO.setCresaClassName(coreClass.getCrcasName());
		}

		if (StringUtil.isEmpty(coreExercisesAnswerVO.getCresaContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", coreExercisesAnswerVO), response);
			return;
		}
				
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(coreExercisesAnswerVO.getCresaContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", coreExercisesAnswerVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		coreExercisesAnswerVO.setCresaContent(JSON.toJSONString(contentList));
						writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreExercisesAnswerVO), response);
		logger.info("[CoreExercisesAnswerController]:end viewsCoreExercisesAnswer");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreExercisesAnswerList (String cresaExercUuid, String cresaUser, HttpServletResponse response) {
		logger.info("[CoreExercisesAnswerController]:begin findCoreExercisesAnswerList");
		List<CoreExercisesAnswer> lists = coreExercisesAnswerService.findCoreExercisesAnswerList(cresaExercUuid, cresaUser);
		List<CoreExercisesAnswerVO> vos = new ArrayList<CoreExercisesAnswerVO>();
		CoreExercisesAnswerVO vo;
		for (CoreExercisesAnswer coreExercisesAnswer : lists) {
			vo = new CoreExercisesAnswerVO();
			vo.convertPOToVO(coreExercisesAnswer);
			//获取所属年级名称
			if(!"".equals(coreExercisesAnswer.getCresaClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreExercisesAnswer.getCresaClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCresaClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreExercisesAnswerController]:end findCoreExercisesAnswerList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreExercisesAnswerPage (String cresaUserCode, String cresaCourseName, String cresaClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreExercisesAnswerController]:begin findCoreExercisesAnswerPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreExercisesAnswer> page = coreExercisesAnswerService.findCoreExercisesAnswerPage(cresaUserCode, cresaCourseName, cresaClass, pageNum, pageSize);
		Page<CoreExercisesAnswerVO> pageVO = new Page<CoreExercisesAnswerVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreExercisesAnswerVO> vos = new ArrayList<CoreExercisesAnswerVO>();
		CoreExercisesAnswerVO vo;
		for (CoreExercisesAnswer coreExercisesAnswer : page.getResult()) {
			vo = new CoreExercisesAnswerVO();
			vo.convertPOToVO(coreExercisesAnswer);
			//获取所属年级名称
			if(!"".equals(coreExercisesAnswer.getCresaClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreExercisesAnswer.getCresaClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCresaClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreExercisesAnswerController]:end findCoreExercisesAnswerPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}