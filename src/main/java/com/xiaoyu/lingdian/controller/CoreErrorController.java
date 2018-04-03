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
import com.xiaoyu.lingdian.entity.CoreError;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreErrorService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.vo.CoreErrorVO;

@Controller
@RequestMapping(value="/coreError")
public class CoreErrorController extends BaseController {

	/**
	* 错题表
	*/
	@Autowired
	private CoreErrorService coreErrorService;
	
	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 题目表
	*/
	@Autowired
	private CoreQuestionsService coreQuestionsService;
	
	/**
	* 获取列表<Page>
	* @param crerrUser 用户uuid
	* @param crqtsQuesClass	问题所属年级
	* @param crerrJudge 0对-》已订正;1错-未订正
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by", method=RequestMethod.POST)
	public void findCoreErrorPageBy (String crerrUser, String crqtsQuesClass, Integer crerrJudge, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin findCoreErrorPageBy");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreError> page = coreErrorService.findCoreErrorPageBy(crerrUser, crqtsQuesClass, crerrJudge, pageNum, pageSize);
		Page<CoreErrorVO> pageVO = new Page<CoreErrorVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreErrorVO> vos = new ArrayList<CoreErrorVO>();
		CoreErrorVO vo;
		for (CoreError coreError : page.getResult()) {
			vo = new CoreErrorVO();

			vo.convertPOToVO(coreError);
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(vo.getCrerrQues());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			vo.convertVO(coreQuestions);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreErrorController]:end findCoreErrorPageBy");

	}

	
	/**
	* 添加（专为经典练习添加）
	*
	* @param crerrUser 用户UUID
	* @param crerrFrom 来源UUID
	* @param crerrFromName 来源名称
	* @param crerrFromType 来源类型（1模拟试卷、2经典练习、3强化训练）
	* @param crerrQues 题目UUID
	* @param crqtsQuesCode 题目编号
	* @param crqtsQuesCategory 类别
	* @param crqtsQuesLevel 难易程度
	* @param crqtsQuesClass 年级
	* @param crqtsQuesKnowledge 知识点
	* @param crqtsQuesDir 图片目录
	* @param crqtsQuesType 题目类型（0文字1图片）
	* @param crqtsQuesUrl 题目图片UUID
	* @param crqtsQuesFont 题目文字
	* @param crqtsAnalysisType 解析类型（0文字1图片）
	* @param crqtsAnalysisUrl 解析图片UUID
	* @param crqtsAnalysisFont 解析文字
	* @param crqtsQuesProblem 问题（特定的文字格式）
	* @param crqtsQuesAnswer 答案（特定格式文字编辑）
	* @param crqtsQuesColor 背景色
	* @param crerrResult 回答结果
	* @param crerrJudge 判断（0对-》已订正;1错-未订正）
	* @return
	*/
	@RequestMapping(value="/add/coreError", method=RequestMethod.POST)
	public void addCoreError (String crerrUser, String crerrFrom, String crerrFromName, Integer crerrFromType, String crerrQues, String crqtsQuesCode, String crqtsQuesCategory, Integer crqtsQuesLevel, String crqtsQuesClass, String crqtsQuesKnowledge, String crqtsQuesDir, Integer crqtsQuesType, String crqtsQuesUrl, String crqtsQuesFont, Integer crqtsAnalysisType, String crqtsAnalysisUrl, String crqtsAnalysisFont, String crqtsQuesProblem, String crqtsQuesAnswer, String crqtsQuesColor, String crerrResult, Integer crerrJudge, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin addCoreError");

		CoreError coreError = new CoreError();
		coreError.setCrerrUser(crerrUser);
		coreError.setCrerrFrom(crerrFrom);
		coreError.setCrerrFromName(crerrFromName);
		coreError.setCrerrFromType(crerrFromType);
		coreError.setCrerrQues(crerrQues);
		coreError.setCrqtsQuesCode(crqtsQuesCode);
		coreError.setCrqtsQuesCategory(crqtsQuesCategory);
		coreError.setCrqtsQuesLevel(crqtsQuesLevel);
		coreError.setCrqtsQuesClass(crqtsQuesClass);
		coreError.setCrqtsQuesKnowledge(crqtsQuesKnowledge);
		coreError.setCrqtsQuesDir(crqtsQuesDir);
		coreError.setCrqtsQuesType(crqtsQuesType);
		coreError.setCrqtsQuesUrl(crqtsQuesUrl);
		coreError.setCrqtsQuesFont(crqtsQuesFont);
		coreError.setCrqtsAnalysisType(crqtsAnalysisType);
		coreError.setCrqtsAnalysisUrl(crqtsAnalysisUrl);
		coreError.setCrqtsAnalysisFont(crqtsAnalysisFont);
		coreError.setCrqtsQuesProblem(crqtsQuesProblem);
		coreError.setCrqtsQuesAnswer(crqtsQuesAnswer);
		coreError.setCrqtsQuesColor(crqtsQuesColor);
		coreError.setCrerrResult(crerrResult);
		coreError.setCrerrJudge(crerrJudge);		
		coreError.setCrerrUdate(new Date());
		
		List<CoreError> lists = coreErrorService.findCoreErrorList(crerrUser, crerrFrom, crerrQues);
		if (lists != null && lists.size() > 0 && lists.get(0) != null) { //已存在更新
			coreError.setCrerrUuid(lists.get(0).getCrerrUuid());
			coreErrorService.updateCoreError(coreError);
		} else { //不存在添加
			String uuid = RandomUtil.generateString(16);
			coreError.setCrerrUuid(uuid);
			coreError.setCrerrCdate(new Date());			
			coreErrorService.insertCoreError(coreError);
		}		

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "操作成功!"),response);
		logger.info("[CoreErrorController]:end addCoreError");

	}
	
	/**
	* 修改
	*
	* @param crerrUuid 标识UUID
	* @param crerrResult 回答结果
	* @param crerrJudge 判断（0对-》已订正;1错-未订正）
	* @return
	*/
	@RequestMapping(value="/update/coreError", method=RequestMethod.POST)
	public void updateCoreError (String crerrUuid, String crerrResult, Integer crerrJudge, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin updateCoreError");

		if (StringUtil.isEmpty(crerrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreError coreError = new CoreError();
		coreError.setCrerrUuid(crerrUuid);
		coreError.setCrerrResult(crerrResult);
		coreError.setCrerrJudge(crerrJudge);
		coreError.setCrerrUdate(new Date());
		
		coreErrorService.updateCoreError(coreError);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreErrorController]:end updateCoreError");

	}

	/**
	* 删除
	*
	* @param crerrUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreError (String crerrUuid, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin deleteCoreError");

		if (StringUtil.isEmpty(crerrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreError coreError = new CoreError();
		coreError.setCrerrUuid(crerrUuid);

		coreErrorService.deleteCoreError(coreError);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreErrorController]:end deleteCoreError");

	}

	/**
	* 批量删除
	*
	* @param crerrUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreError (String crerrUuids, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin deleteBatchCoreError");

		if (StringUtil.isEmpty(crerrUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}

		String[] uuids=crerrUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreErrorService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreErrorController]:end deleteBatchCoreError");

	}

	/**
	* 获取单个
	*
	* @param crerrUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreError (String crerrUuid, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin viewsCoreError");

		if (StringUtil.isEmpty(crerrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreError coreError = new CoreError();
		coreError.setCrerrUuid(crerrUuid);

		coreError = coreErrorService.getCoreError(coreError);

		CoreErrorVO coreErrorVO = new CoreErrorVO();
		coreErrorVO.convertPOToVO(coreError);
		if (!StringUtil.isEmpty(coreError.getCrqtsQuesClass())) {
			CoreClass coreClass=new CoreClass();
			coreClass.setCrcasUuid(coreError.getCrqtsQuesClass());
			coreClass=coreClassService.getCoreClass(coreClass);
			coreErrorVO.setCrqtsQuesClassName(coreClass.getCrcasName());			
		}
		CoreQuestions coreQuestions = new CoreQuestions();
		coreQuestions.setCrqtsUuid(coreErrorVO.getCrerrQues());
		coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
		coreErrorVO.convertVO(coreQuestions);
		coreError.convertVO(coreQuestions);
		//更新错题集
		coreErrorService.updateCoreError(coreError);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreErrorVO), response);
		logger.info("[CoreErrorController]:end viewsCoreError");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreErrorList (String crerrUser, String crerrFrom, String crerrQues, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin findCoreErrorList");

		List<CoreError> lists = coreErrorService.findCoreErrorList(crerrUser, crerrFrom, crerrQues);
		List<CoreErrorVO> vos = new ArrayList<CoreErrorVO>();
		CoreErrorVO vo;
		for (CoreError coreError : lists) {
			vo = new CoreErrorVO();

			vo.convertPOToVO(coreError);
			if (!StringUtil.isEmpty(coreError.getCrqtsQuesClass())) {
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreError.getCrqtsQuesClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrqtsQuesClassName(coreClass.getCrcasName());
				CoreQuestions coreQuestions = new CoreQuestions();
				coreQuestions.setCrqtsUuid(vo.getCrerrQues());
				coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
				vo.convertVO(coreQuestions);
			}
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreErrorController]:end findCoreErrorList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crerrFromName 课程或试卷
	* @param crqtsQuesClass 所属年级
	* @param crusrGrade 所属班级
	* @param crqtsQuesCode 题目编号
	* @param crusrCode 用户名
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreErrorPage (String crerrFromName, String crqtsQuesClass, String crusrGrade, String crqtsQuesCode, String crusrCode, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreErrorController]:begin findCoreErrorPage");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreError> page = coreErrorService.findCoreErrorPage(crerrFromName, crqtsQuesClass, crusrGrade, crqtsQuesCode, crusrCode, pageNum, pageSize);
		Page<CoreErrorVO> pageVO = new Page<CoreErrorVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreErrorVO> vos = new ArrayList<CoreErrorVO>();
		CoreErrorVO vo;
		for (CoreError coreError : page.getResult()) {
			vo = new CoreErrorVO();

			vo.convertPOToVO(coreError);
			if (!StringUtil.isEmpty(coreError.getCrqtsQuesClass())) {
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreError.getCrqtsQuesClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrqtsQuesClassName(coreClass.getCrcasName());
				CoreQuestions coreQuestions = new CoreQuestions();
				coreQuestions.setCrqtsUuid(vo.getCrerrQues());
				coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
				vo.convertVO(coreQuestions);
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreErrorController]:end findCoreErrorPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}