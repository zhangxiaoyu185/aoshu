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
import com.xiaoyu.lingdian.entity.CorePapersAnswer;
import com.xiaoyu.lingdian.entity.CorePapersAnswerOther;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreErrorService;
import com.xiaoyu.lingdian.service.CorePapersAnswerOtherService;
import com.xiaoyu.lingdian.service.CorePapersAnswerService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.vo.ContentList;
import com.xiaoyu.lingdian.vo.CorePapersAnswerOtherVO;
import com.xiaoyu.lingdian.vo.CorePapersAnswerVO;
import com.xiaoyu.lingdian.vo.PapersAnswer;
import com.xiaoyu.lingdian.vo.Problem;

@Controller
@RequestMapping(value="/corePapersAnswer")
public class CorePapersAnswerController extends BaseController {

	/**
	* 试卷回答表
	*/
	@Autowired
	private CorePapersAnswerService corePapersAnswerService;
	
	/**
	* 临时试卷回答表
	*/
	@Autowired
	private CorePapersAnswerOtherService corePapersAnswerOtherService;
	
	/**
	* 题目表
	*/
	@Autowired
	private CoreQuestionsService coreQuestionsService;
	
	/**
	* 错题集表
	*/
	@Autowired
	private CoreErrorService coreErrorService;
	
	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 判题
	*
	* @param crpsaPapersUuid 模拟试卷UUID
	* @param crpsaPapersName 模拟试卷名称
	* @param crpsaUser 用户UUID
	* @param crpsaContent 回答内容
	* @param crpsaClass 所属年级
	* @param crpsaClassName 所属年级名称
	* @param totalScore 试卷总分
	* @param crpsaTime 时间
	* @return
	*/
	@RequestMapping(value="/add/corePapersAnswer", method=RequestMethod.POST)
	public void addCorePapersAnswer (String crpsaPapersUuid, String crpsaPapersName, String crpsaUser, String crpsaContent, String crpsaClass, String crpsaClassName, Integer totalScore, String crpsaTime, HttpServletResponse response) {
		logger.info("[CorePapersAnswerController]:begin addCorePapersAnswer");
		
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(crpsaContent, ContentList.class);
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
			} else { //有错误
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
				coreError.setCrerrUser(crpsaUser);
				coreError.setCrerrFrom(crpsaPapersUuid);
				coreError.setCrerrFromName(crpsaPapersName);
				coreError.setCrerrFromType(1); //模拟试卷
				coreError.setCrerrJudge(1);
				coreError.setCrqtsQuesClass(crpsaClass);
				coreError.setCrerrUdate(new Date());
				List<CoreError> lists = coreErrorService.findCoreErrorList(crpsaUser, crpsaPapersUuid, coreError.getCrerrQues());
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
		int calScore = totalScore==null?0:totalScore;
		if (totalCount != 0) {
			score = (int)(calScore * successCount / (totalCount * 1.0));
		}
		//根据用户和试卷UUID查询是否是重做
		List<CorePapersAnswer> answerList = corePapersAnswerService.findCorePapersAnswerList(crpsaPapersUuid, crpsaUser);
		CorePapersAnswer corePapersAnswer = new CorePapersAnswer();
		contentList.setProblem(problemList);
		if (null != answerList && answerList.size() > 0) { //更新
			String uuid = answerList.get(0).getCrpsaUuid();
			corePapersAnswer.setCrpsaUuid(uuid);
			corePapersAnswer.setCrpsaPapersUuid(crpsaPapersUuid);
			corePapersAnswer.setCrpsaPapersName(crpsaPapersName);
			corePapersAnswer.setCrpsaUser(crpsaUser);		
			corePapersAnswer.setCrpsaClass(crpsaClass);		
			corePapersAnswer.setCrpsaUdate(new Date());
			if (answerList.get(0).getCrpsaScore() <= score) { //本次的分数高,记录分数和详情信息
				corePapersAnswer.setCrpsaContent(JSON.toJSONString(contentList));
				corePapersAnswer.setCrpsaScore(score);
				corePapersAnswer.setCrpsaTime(crpsaTime);
				if (score != calScore) {
					corePapersAnswer.setCrpsaState(1);
				} else {
					corePapersAnswer.setCrpsaState(0);
				}
			}
			corePapersAnswerService.updateCorePapersAnswer(corePapersAnswer);
			corePapersAnswer.setCrpsaContent(JSON.toJSONString(contentList));
			corePapersAnswer.setCrpsaScore(score);
			corePapersAnswer.setCrpsaTime(crpsaTime);
			if (score != calScore) {
				corePapersAnswer.setCrpsaState(1);
			} else {
				corePapersAnswer.setCrpsaState(0);
			}
		} else { //添加
			String uuid = RandomUtil.generateString(16);
			corePapersAnswer.setCrpsaUuid(uuid);
			corePapersAnswer.setCrpsaPapersUuid(crpsaPapersUuid);
			corePapersAnswer.setCrpsaPapersName(crpsaPapersName);
			corePapersAnswer.setCrpsaUser(crpsaUser);
			corePapersAnswer.setCrpsaContent(JSON.toJSONString(contentList));
			corePapersAnswer.setCrpsaClass(crpsaClass);
			corePapersAnswer.setCrpsaScore(score);
			corePapersAnswer.setCrpsaTime(crpsaTime);
			if (score != calScore) {
				corePapersAnswer.setCrpsaState(1);
			} else {
				corePapersAnswer.setCrpsaState(0);
			}
			corePapersAnswer.setCrpsaCdate(new Date());
			corePapersAnswer.setCrpsaUdate(new Date());
			
			corePapersAnswerService.insertCorePapersAnswer(corePapersAnswer);
		}
		
		//临时答题表------------------start
		//根据用户和试卷UUID查询是否是重做
		String uuidOther = "";
		List<CorePapersAnswerOther> answerOtherList = corePapersAnswerOtherService.findCorePapersAnswerOtherList(crpsaPapersUuid, crpsaUser);
		CorePapersAnswerOther corePapersAnswerOther = new CorePapersAnswerOther();
		if (null != answerOtherList && answerOtherList.size() > 0) { //更新
			uuidOther = answerOtherList.get(0).getCrpsaUuid();
			corePapersAnswerOther.setCrpsaUuid(uuidOther);
			corePapersAnswerOther.setCrpsaPapersUuid(crpsaPapersUuid);
			corePapersAnswerOther.setCrpsaPapersName(crpsaPapersName);
			corePapersAnswerOther.setCrpsaUser(crpsaUser);		
			corePapersAnswerOther.setCrpsaClass(crpsaClass);		
			corePapersAnswerOther.setCrpsaUdate(new Date());
			corePapersAnswerOther.setCrpsaContent(JSON.toJSONString(contentList));
			corePapersAnswerOther.setCrpsaScore(score);
			corePapersAnswerOther.setCrpsaTime(crpsaTime);
			if (score != calScore) {
				corePapersAnswerOther.setCrpsaState(1);
			} else {
				corePapersAnswerOther.setCrpsaState(0);
			}

			corePapersAnswerOtherService.updateCorePapersAnswerOther(corePapersAnswerOther);
		} else { //添加
			uuidOther = RandomUtil.generateString(16);
			corePapersAnswerOther.setCrpsaUuid(uuidOther);
			corePapersAnswerOther.setCrpsaPapersUuid(crpsaPapersUuid);
			corePapersAnswerOther.setCrpsaPapersName(crpsaPapersName);
			corePapersAnswerOther.setCrpsaUser(crpsaUser);
			corePapersAnswerOther.setCrpsaContent(JSON.toJSONString(contentList));
			corePapersAnswerOther.setCrpsaClass(crpsaClass);
			corePapersAnswerOther.setCrpsaScore(score);
			corePapersAnswerOther.setCrpsaTime(crpsaTime);
			if (score != calScore) {
				corePapersAnswerOther.setCrpsaState(1);
			} else {
				corePapersAnswerOther.setCrpsaState(0);
			}
			corePapersAnswerOther.setCrpsaCdate(new Date());
			corePapersAnswerOther.setCrpsaUdate(new Date());
			
			corePapersAnswerOtherService.insertCorePapersAnswerOther(corePapersAnswerOther);
		}
		//临时答题表------------------end

		PapersAnswer papersAnswer = new PapersAnswer();
		papersAnswer.convertPOToVO(corePapersAnswer);
		papersAnswer.setCrpsaUuid(uuidOther);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "操作成功!", papersAnswer),response);
		logger.info("[CorePapersAnswerController]:end addCorePapersAnswer");

	}

	/**
	* 删除
	*
	* @param crpsaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCorePapersAnswer (String crpsaUuid, HttpServletResponse response) {
		logger.info("[CorePapersAnswerController]:begin deleteCorePapersAnswer");
		if (StringUtil.isEmpty(crpsaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CorePapersAnswer corePapersAnswer = new CorePapersAnswer();
		corePapersAnswer.setCrpsaUuid(crpsaUuid);

		corePapersAnswerService.deleteCorePapersAnswer(corePapersAnswer);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CorePapersAnswerController]:end deleteCorePapersAnswer");

	}

	/**
	* 批量删除
	*
	* @param crpsaUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCorePapersAnswer (String crpsaUuids, HttpServletResponse response) {
		logger.info("[CorePapersAnswerController]:begin deleteBatchCorePapersAnswer");
		if (StringUtil.isEmpty(crpsaUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crpsaUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		corePapersAnswerService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CorePapersAnswerController]:end deleteBatchCorePapersAnswer");

	}

	/**
	* 获取单个
	*
	* @param crpsaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCorePapersAnswer (String crpsaUuid, HttpServletResponse response) {
		logger.info("[CorePapersAnswerController]:begin viewsCorePapersAnswer");
		if (StringUtil.isEmpty(crpsaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CorePapersAnswer corePapersAnswer = new CorePapersAnswer();
		corePapersAnswer.setCrpsaUuid(crpsaUuid);

		corePapersAnswer = corePapersAnswerService.getCorePapersAnswer(corePapersAnswer);

		CorePapersAnswerVO corePapersAnswerVO = new CorePapersAnswerVO();
		corePapersAnswerVO.convertPOToVO(corePapersAnswer);
		// 获取年级名称
		if (!StringUtil.isEmpty(corePapersAnswer.getCrpsaClass())) {
			CoreClass coreClass = new CoreClass();
			coreClass.setCrcasUuid(corePapersAnswer.getCrpsaClass());
			coreClass = coreClassService.getCoreClass(coreClass);
			corePapersAnswerVO.setCrpsaClassName(coreClass.getCrcasName());
		}

		if (StringUtil.isEmpty(corePapersAnswerVO.getCrpsaContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", corePapersAnswerVO), response);
			return;
		}
				
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(corePapersAnswerVO.getCrpsaContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", corePapersAnswerVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		corePapersAnswerVO.setCrpsaContent(JSON.toJSONString(contentList));		
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", corePapersAnswerVO), response);
		logger.info("[CorePapersAnswerController]:end viewsCorePapersAnswer");

	}

	/**
	* 获取单个(临时)
	*
	* @param crpsaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views/other", method=RequestMethod.POST)
	public void viewsCorePapersAnswerOther (String crpsaUuid, HttpServletResponse response) {
		logger.info("[CorePapersAnswerController]:begin viewsCorePapersAnswerOther");

		if (StringUtil.isEmpty(crpsaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CorePapersAnswerOther corePapersAnswerOther = new CorePapersAnswerOther();
		corePapersAnswerOther.setCrpsaUuid(crpsaUuid);

		corePapersAnswerOther = corePapersAnswerOtherService.getCorePapersAnswerOther(corePapersAnswerOther);

		CorePapersAnswerOtherVO corePapersAnswerOtherVO = new CorePapersAnswerOtherVO();
		corePapersAnswerOtherVO.convertPOToVO(corePapersAnswerOther);
		// 获取年级名称
		if (!StringUtil.isEmpty(corePapersAnswerOther.getCrpsaClass())) {
			CoreClass coreClass = new CoreClass();
			coreClass.setCrcasUuid(corePapersAnswerOther.getCrpsaClass());
			coreClass = coreClassService.getCoreClass(coreClass);
			corePapersAnswerOtherVO.setCrpsaClassName(coreClass.getCrcasName());
		}

		if (StringUtil.isEmpty(corePapersAnswerOtherVO.getCrpsaContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", corePapersAnswerOtherVO), response);
			return;
		}
				
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(corePapersAnswerOtherVO.getCrpsaContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", corePapersAnswerOtherVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		corePapersAnswerOtherVO.setCrpsaContent(JSON.toJSONString(contentList));		
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", corePapersAnswerOtherVO), response);
		logger.info("[CorePapersAnswerController]:end viewsCorePapersAnswerOther");

	}
	
	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCorePapersAnswerList (String crpsaPapersUuid, String crpsaUser, HttpServletResponse response) {
		logger.info("[CorePapersAnswerController]:begin findCorePapersAnswerList");
		List<CorePapersAnswer> lists = corePapersAnswerService.findCorePapersAnswerList(crpsaPapersUuid, crpsaUser);
		List<CorePapersAnswerVO> vos = new ArrayList<CorePapersAnswerVO>();
		CorePapersAnswerVO vo;
		for (CorePapersAnswer corePapersAnswer : lists) {
			vo = new CorePapersAnswerVO();
			vo.convertPOToVO(corePapersAnswer);
			//获取年级名称
			if(!StringUtil.isEmpty(corePapersAnswer.getCrpsaClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(corePapersAnswer.getCrpsaClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrpsaClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CorePapersAnswerController]:end findCorePapersAnswerList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCorePapersAnswerPage (String crpsaUserCode, String crpsaPapersName, String crpsaClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CorePapersAnswerController]:begin findCorePapersAnswerPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CorePapersAnswer> page = corePapersAnswerService.findCorePapersAnswerPage(crpsaUserCode, crpsaPapersName, crpsaClass, pageNum, pageSize);
		Page<CorePapersAnswerVO> pageVO = new Page<CorePapersAnswerVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CorePapersAnswerVO> vos = new ArrayList<CorePapersAnswerVO>();
		CorePapersAnswerVO vo;
		for (CorePapersAnswer corePapersAnswer : page.getResult()) {
			vo = new CorePapersAnswerVO();
			vo.convertPOToVO(corePapersAnswer);
			//获取年级名称
			if(!StringUtil.isEmpty(corePapersAnswer.getCrpsaClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(corePapersAnswer.getCrpsaClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrpsaClassName(coreClass.getCrcasName());
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CorePapersAnswerController]:end findCorePapersAnswerPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}