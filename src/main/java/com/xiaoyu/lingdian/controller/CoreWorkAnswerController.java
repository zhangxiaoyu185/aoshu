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
import com.xiaoyu.lingdian.entity.CoreError;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.entity.CoreWorkAnswer;
import com.xiaoyu.lingdian.entity.CoreWorkAnswerOther;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreErrorService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.service.CoreWorkAnswerOtherService;
import com.xiaoyu.lingdian.service.CoreWorkAnswerService;
import com.xiaoyu.lingdian.vo.ContentList;
import com.xiaoyu.lingdian.vo.CoreWorkAnswerOtherVO;
import com.xiaoyu.lingdian.vo.CoreWorkAnswerVO;
import com.xiaoyu.lingdian.vo.Problem;
import com.xiaoyu.lingdian.vo.WorkAnswer;

@Controller
@RequestMapping(value="/coreWorkAnswer")
public class CoreWorkAnswerController extends BaseController {

	/**
	* 训练回答表
	*/
	@Autowired
	private CoreWorkAnswerService coreWorkAnswerService;
	
	/**
	* 临时训练回答表
	*/
	@Autowired
	private CoreWorkAnswerOtherService coreWorkAnswerOtherService;
	
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
	* 错题集表
	*/
	@Autowired
	private CoreErrorService coreErrorService;
	
	/**
	* 判题
	*
	* @param crwkaWorkUuid 训练UUID
	* @param crwkaCourseName 课程名称
	* @param crwkaUser 用户UUID
	* @param crwkaContent 回答内容
	* @param crwkaClass 所属年级
	* @param crwkaClassName 所属年级名称
	* @param crwkaTime 时间
	* @return
	*/
	@RequestMapping(value="/add/coreWorkAnswer", method=RequestMethod.POST)
	public void addCoreWorkAnswer (String crwkaWorkUuid, String crwkaCourseName, String crwkaUser, String crwkaContent, String crwkaClass, String crwkaTime, String crwkaClassName, HttpServletResponse response) {
		logger.info("[CoreWorkAnswerController]:begin addCoreWorkAnswer");

		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(crwkaContent, ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "解析题目串失败!"),response);
			return;
		}
		
		double successCount = 0; //题目成功数
		int totalCount = 0; //题目总数
		CoreError coreError = null;
		for (Problem problem : problemList) {
			totalCount++;
			String strAnswer = problem.getCrqtsAnswer()==null?"":problem.getCrqtsAnswer();
			String strResult = problem.getResult()==null?"":problem.getResult();
			if (strAnswer.equalsIgnoreCase(strResult)) {
				successCount = successCount + 1;
				problem.setJudge(0);
			} else {
				int spaceCount = 0; //题目空格数量
				int resultCount = 0; //回答的长度
				double spaceSuccessCount = 0; //正确题目空格数量
				if (!StringUtil.isEmpty(strAnswer)) {
					if (!StringUtil.isEmpty(strResult)) {
						String[] answerList  = strAnswer.split(",");
						String[] resultList  = strResult.split(",");
						spaceCount = answerList.length;
						resultCount = resultList.length;
						if (spaceCount != 0) {
							for (int i = 0; i < answerList.length; i++) {
								if (resultCount > i) {
									if (null != resultList[i] && answerList[i].equalsIgnoreCase(resultList[i])) {
										spaceSuccessCount = spaceSuccessCount + 1;
									}
								}
							}
							successCount = successCount + spaceSuccessCount / (spaceCount * 1.0);
						}
					}
				} else {
					successCount = successCount + 1;
				}

				problem.setJudge(1);
				//添加至错题集
				coreError = new CoreError();
				coreError.convertErrorPO(problem);
				coreError.setCrerrUser(crwkaUser);
				coreError.setCrerrFrom(crwkaWorkUuid);
				coreError.setCrerrFromName(crwkaCourseName+"强化训练");
				coreError.setCrerrFromType(3); //强化训练
				coreError.setCrerrJudge(1);
				coreError.setCrqtsQuesClass(crwkaClass);
				coreError.setCrerrUdate(new Date());
				List<CoreError> lists = coreErrorService.findCoreErrorList(crwkaUser, crwkaWorkUuid, coreError.getCrerrQues());
				if (lists != null && lists.size() > 0 && lists.get(0) != null) { //已存在更新
					coreError.setCrerrUuid(lists.get(0).getCrerrUuid());
					coreErrorService.updateCoreError(coreError);
				} else { //不存在添加
					String crerrUuid = RandomUtil.generateString(16);
					coreError.setCrerrUuid(crerrUuid);
					coreError.setCrerrCdate(new Date());			
					coreErrorService.insertCoreError(coreError);
				}
			}
		}
		//计算分数：总分*成功率
		int score = 0;
		int calScore = 100;
		if (totalCount != 0) {
			score = (int)(calScore * successCount / (totalCount * 1.0));
		}
		//根据用户和试卷UUID查询是否是重做
		List<CoreWorkAnswer> answerList = coreWorkAnswerService.findCoreWorkAnswerList(crwkaWorkUuid, crwkaUser);
		CoreWorkAnswer coreWorkAnswer = new CoreWorkAnswer();
		contentList.setProblem(problemList);
		if (null != answerList && answerList.size() > 0) { //更新
			String uuid = answerList.get(0).getCrwkaUuid();
			coreWorkAnswer.setCrwkaUuid(uuid);
			coreWorkAnswer.setCrwkaWorkUuid(crwkaWorkUuid);
			coreWorkAnswer.setCrwkaCourseName(crwkaCourseName);
			coreWorkAnswer.setCrwkaUser(crwkaUser);
			coreWorkAnswer.setCrwkaClass(crwkaClass);
			coreWorkAnswer.setCrwkaUdate(new Date());			
			if (answerList.get(0).getCrwkaScore() <= score) { //本次的分数高,记录
				coreWorkAnswer.setCrwkaContent(JSON.toJSONString(contentList));
				coreWorkAnswer.setCrwkaScore(score);
				coreWorkAnswer.setCrwkaTime(crwkaTime);
				if (score != calScore) {
					coreWorkAnswer.setCrwkaState(1);
				} else {
					coreWorkAnswer.setCrwkaState(0);
				}
			}
			coreWorkAnswerService.updateCoreWorkAnswer(coreWorkAnswer);
			coreWorkAnswer.setCrwkaContent(JSON.toJSONString(contentList));
			coreWorkAnswer.setCrwkaScore(score);
			coreWorkAnswer.setCrwkaTime(crwkaTime);
			if (score != calScore) {
				coreWorkAnswer.setCrwkaState(1);
			} else {
				coreWorkAnswer.setCrwkaState(0);
			}
		} else { //添加
			String uuid = RandomUtil.generateString(16);
			coreWorkAnswer.setCrwkaUuid(uuid);
			coreWorkAnswer.setCrwkaWorkUuid(crwkaWorkUuid);
			coreWorkAnswer.setCrwkaCourseName(crwkaCourseName);
			coreWorkAnswer.setCrwkaUser(crwkaUser);
			coreWorkAnswer.setCrwkaContent(JSON.toJSONString(contentList));
			coreWorkAnswer.setCrwkaClass(crwkaClass);
			coreWorkAnswer.setCrwkaScore(score);
			coreWorkAnswer.setCrwkaTime(crwkaTime);
			if (score != calScore) {
				coreWorkAnswer.setCrwkaState(1);
			} else {
				coreWorkAnswer.setCrwkaState(0);
			}
			coreWorkAnswer.setCrwkaCdate(new Date());
			coreWorkAnswer.setCrwkaUdate(new Date());
			
			coreWorkAnswerService.insertCoreWorkAnswer(coreWorkAnswer);
		}
		
		//临时答题表------------------start
		//根据用户和试卷UUID查询是否是重做
		String uuidOther = "";
		List<CoreWorkAnswerOther> answerOtherList = coreWorkAnswerOtherService.findCoreWorkAnswerOtherList(crwkaWorkUuid, crwkaUser);
		CoreWorkAnswerOther coreWorkAnswerOther = new CoreWorkAnswerOther();
		if (null != answerOtherList && answerOtherList.size() > 0) { //更新
			uuidOther = answerList.get(0).getCrwkaUuid();
			coreWorkAnswerOther.setCrwkaUuid(uuidOther);
			coreWorkAnswerOther.setCrwkaWorkUuid(crwkaWorkUuid);
			coreWorkAnswerOther.setCrwkaCourseName(crwkaCourseName);
			coreWorkAnswerOther.setCrwkaUser(crwkaUser);
			coreWorkAnswerOther.setCrwkaClass(crwkaClass);
			coreWorkAnswerOther.setCrwkaUdate(new Date());			
			coreWorkAnswerOther.setCrwkaContent(JSON.toJSONString(contentList));
			coreWorkAnswerOther.setCrwkaScore(score);
			coreWorkAnswerOther.setCrwkaTime(crwkaTime);
			if (score != calScore) {
				coreWorkAnswerOther.setCrwkaState(1);
			} else {
				coreWorkAnswerOther.setCrwkaState(0);
			}

			coreWorkAnswerOtherService.updateCoreWorkAnswerOther(coreWorkAnswerOther);
		} else { //添加
			uuidOther = RandomUtil.generateString(16);
			coreWorkAnswerOther.setCrwkaUuid(uuidOther);
			coreWorkAnswerOther.setCrwkaWorkUuid(crwkaWorkUuid);
			coreWorkAnswerOther.setCrwkaCourseName(crwkaCourseName);
			coreWorkAnswerOther.setCrwkaUser(crwkaUser);
			coreWorkAnswerOther.setCrwkaContent(JSON.toJSONString(contentList));
			coreWorkAnswerOther.setCrwkaClass(crwkaClass);
			coreWorkAnswerOther.setCrwkaScore(score);
			coreWorkAnswerOther.setCrwkaTime(crwkaTime);
			if (score != calScore) {
				coreWorkAnswerOther.setCrwkaState(1);
			} else {
				coreWorkAnswerOther.setCrwkaState(0);
			}
			coreWorkAnswerOther.setCrwkaCdate(new Date());
			coreWorkAnswerOther.setCrwkaUdate(new Date());
			
			coreWorkAnswerOtherService.insertCoreWorkAnswerOther(coreWorkAnswerOther);
		}
				WorkAnswer workAnswer = new WorkAnswer();
		workAnswer.convertPOToVO(coreWorkAnswer);
		workAnswer.setCrwkaUuid(uuidOther);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "操作成功!", workAnswer),response);
		logger.info("[CoreWorkAnswerController]:end addCoreWorkAnswer");

	}

	/**
	* 删除
	*
	* @param crwkaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreWorkAnswer (String crwkaUuid, HttpServletResponse response) {
		logger.info("[CoreWorkAnswerController]:begin deleteCoreWorkAnswer");
		if (StringUtil.isEmpty(crwkaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreWorkAnswer coreWorkAnswer = new CoreWorkAnswer();
		coreWorkAnswer.setCrwkaUuid(crwkaUuid);

		coreWorkAnswerService.deleteCoreWorkAnswer(coreWorkAnswer);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreWorkAnswerController]:end deleteCoreWorkAnswer");

	}

	/**
	* 批量删除
	*
	* @param crwkaUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreWorkAnswer (String crwkaUuids, HttpServletResponse response) {
		logger.info("[CoreWorkAnswerController]:begin deleteBatchCoreWorkAnswer");
		if (StringUtil.isEmpty(crwkaUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crwkaUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreWorkAnswerService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreWorkAnswerController]:end deleteBatchCoreWorkAnswer");

	}

	/**
	* 获取单个
	*
	* @param crwkaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreWorkAnswer (String crwkaUuid, HttpServletResponse response) {
		logger.info("[CoreWorkAnswerController]:begin viewsCoreWorkAnswer");
		if (StringUtil.isEmpty(crwkaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreWorkAnswer coreWorkAnswer = new CoreWorkAnswer();
		coreWorkAnswer.setCrwkaUuid(crwkaUuid);

		coreWorkAnswer = coreWorkAnswerService.getCoreWorkAnswer(coreWorkAnswer);

		CoreWorkAnswerVO coreWorkAnswerVO = new CoreWorkAnswerVO();
		coreWorkAnswerVO.convertPOToVO(coreWorkAnswer);
		//获取所属年级名称
		if (!"".equals(coreWorkAnswer.getCrwkaClass())) {
			CoreClass coreClass = new CoreClass();
			coreClass.setCrcasUuid(coreWorkAnswer.getCrwkaClass());
			coreClass = coreClassService.getCoreClass(coreClass);
			coreWorkAnswerVO.setCrwkaClassName(coreClass.getCrcasName());
		}

		if (StringUtil.isEmpty(coreWorkAnswerVO.getCrwkaContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", coreWorkAnswerVO), response);
			return;
		}
				
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(coreWorkAnswerVO.getCrwkaContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", coreWorkAnswerVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		coreWorkAnswerVO.setCrwkaContent(JSON.toJSONString(contentList));		
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreWorkAnswerVO), response);
		logger.info("[CoreWorkAnswerController]:end viewsCoreWorkAnswer");

	}

	/**
	* 获取单个(临时)
	*
	* @param crwkaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views/other", method=RequestMethod.POST)
	public void viewsCoreWorkAnswerOther (String crwkaUuid, HttpServletResponse response) {
		logger.info("[CoreWorkAnswerController]:begin viewsCoreWorkAnswerOther");

		if (StringUtil.isEmpty(crwkaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreWorkAnswerOther coreWorkAnswerOther = new CoreWorkAnswerOther();
		coreWorkAnswerOther.setCrwkaUuid(crwkaUuid);

		coreWorkAnswerOther = coreWorkAnswerOtherService.getCoreWorkAnswerOther(coreWorkAnswerOther);

		CoreWorkAnswerOtherVO coreWorkAnswerOtherVO = new CoreWorkAnswerOtherVO();
		coreWorkAnswerOtherVO.convertPOToVO(coreWorkAnswerOther);
		//获取所属年级名称
		if (!"".equals(coreWorkAnswerOther.getCrwkaClass())) {
			CoreClass coreClass = new CoreClass();
			coreClass.setCrcasUuid(coreWorkAnswerOther.getCrwkaClass());
			coreClass = coreClassService.getCoreClass(coreClass);
			coreWorkAnswerOtherVO.setCrwkaClassName(coreClass.getCrcasName());
		}

		if (StringUtil.isEmpty(coreWorkAnswerOtherVO.getCrwkaContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", coreWorkAnswerOtherVO), response);
			return;
		}
				
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(coreWorkAnswerOtherVO.getCrwkaContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", coreWorkAnswerOtherVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		coreWorkAnswerOtherVO.setCrwkaContent(JSON.toJSONString(contentList));		
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreWorkAnswerOtherVO), response);
		logger.info("[CoreWorkAnswerController]:end viewsCoreWorkAnswerOther");

	}
	
	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreWorkAnswerList (String crwkaWorkUuid, String crwkaUser, HttpServletResponse response) {
		logger.info("[CoreWorkAnswerController]:begin findCoreWorkAnswerList");
		List<CoreWorkAnswer> lists = coreWorkAnswerService.findCoreWorkAnswerList(crwkaWorkUuid, crwkaUser);
		List<CoreWorkAnswerVO> vos = new ArrayList<CoreWorkAnswerVO>();
		CoreWorkAnswerVO vo;
		for (CoreWorkAnswer coreWorkAnswer : lists) {
			vo = new CoreWorkAnswerVO();
			vo.convertPOToVO(coreWorkAnswer);
			//获取所属年级名称
			if(!"".equals(coreWorkAnswer.getCrwkaClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreWorkAnswer.getCrwkaClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrwkaClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreWorkAnswerController]:end findCoreWorkAnswerList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreWorkAnswerPage (String crwkaUserCode, String crwkaCourseName, String crwkaClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreWorkAnswerController]:begin findCoreWorkAnswerPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreWorkAnswer> page = coreWorkAnswerService.findCoreWorkAnswerPage(crwkaUserCode, crwkaCourseName, crwkaClass, pageNum, pageSize);
		Page<CoreWorkAnswerVO> pageVO = new Page<CoreWorkAnswerVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreWorkAnswerVO> vos = new ArrayList<CoreWorkAnswerVO>();
		CoreWorkAnswerVO vo;
		for (CoreWorkAnswer coreWorkAnswer : page.getResult()) {
			vo = new CoreWorkAnswerVO();
			vo.convertPOToVO(coreWorkAnswer);
			//获取所属年级名称
			if(!"".equals(coreWorkAnswer.getCrwkaClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreWorkAnswer.getCrwkaClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrwkaClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreWorkAnswerController]:end findCoreWorkAnswerPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}