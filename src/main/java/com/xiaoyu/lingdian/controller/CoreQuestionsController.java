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
import com.xiaoyu.lingdian.entity.CoreAttachment;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.service.CoreAttachmentService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.vo.CoreQuestionsVO;

@Controller
@RequestMapping(value="/coreQuestions")
public class CoreQuestionsController extends BaseController {

	/**
	* 题库表
	*/
	@Autowired
	private CoreQuestionsService coreQuestionsService;
	
	@Autowired
	private CoreAttachmentService coreAttachmentService;
	
	/**
	* 自动生成数学实验室
	*
	* @param beforeCode 开始题目编号
	* @param afterCode 最后题目编号
	* @param strDir 图片目录
	* @param strDirMovie 视频目录
	* @return
	*/
	@RequestMapping(value="/sxsy", method=RequestMethod.POST)
	public void sxsyQuestions (String beforeCode, String afterCode, String strDir, String strDirMovie, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin sxsyQuestions");
		int beforeInt = 0;
		int afterInt = 0;
		try {
			beforeInt = Integer.valueOf(beforeCode);
			afterInt = Integer.valueOf(afterCode);
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "不是数字!"), response);
			return;
		}
		
		for (; beforeInt <= afterInt; beforeInt++) {
			CoreQuestions coreQuestions = new CoreQuestions();
			String uuid = RandomUtil.generateString(16);
			coreQuestions.setCrqtsUuid(uuid);
			if (beforeInt < 10) {
				coreQuestions.setCrqtsCode("0000" + beforeInt);
			} else if (beforeInt < 100) {
				coreQuestions.setCrqtsCode("000" + beforeInt);
			} else if (beforeInt < 1000) {
				coreQuestions.setCrqtsCode("00" + beforeInt);
			} else if (beforeInt < 10000) {
				coreQuestions.setCrqtsCode("0" + beforeInt);
			} else if (beforeInt < 100000) {
				coreQuestions.setCrqtsCode(beforeInt + "");
			}
			
			List<CoreQuestions> list = coreQuestionsService.getCoreQuestionsByCode(coreQuestions.getCrqtsCode());
			if (null != list && list.size() > 0) {
				continue;
			}
			
			coreQuestions.setCrqtsCategory("6"); //无
			coreQuestions.setCrqtsLevel(0); //无
			coreQuestions.setCrqtsClass("7"); //无
			coreQuestions.setCrqtsKnowledge("061");	//无		
			coreQuestions.setCrqtsQuesType(1); //图片		
			coreQuestions.setCrqtsAnalysisType(1); //图片
			coreQuestions.setCrqtsDir(strDir);
			coreQuestions.setCrqtsDirMovie(strDirMovie);
			coreQuestions.setCrqtsCdate(new Date());
			coreQuestions.setCrqtsUdate(new Date());
			
			//生成默认类型为图片
			String uuidQues = RandomUtil.generateString(16);
			CoreAttachment coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(uuidQues);
			coreAttachment.setCratmCdate(new Date());
			coreAttachment.setCratmFileName(coreQuestions.getCrqtsCode()+".png");
			coreAttachment.setCratmStatus(1);
			coreAttachment.setCratmDir("question/" + strDir);
			coreAttachment.setCratmExtension(".png");
			coreAttachment.setCratmType(1);
			coreAttachmentService.insertCoreAttachment(coreAttachment);
			
			coreQuestions.setCrqtsQuesUrl(uuidQues);
			
			String uuidAnalysis = RandomUtil.generateString(16);
			coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(uuidAnalysis);
			coreAttachment.setCratmCdate(new Date());
			coreAttachment.setCratmFileName(coreQuestions.getCrqtsCode()+".png");
			coreAttachment.setCratmStatus(1);
			coreAttachment.setCratmDir("analysis/" + strDir);
			coreAttachment.setCratmExtension(".png");
			coreAttachment.setCratmType(1);
			coreAttachmentService.insertCoreAttachment(coreAttachment);
			
			coreQuestions.setCrqtsAnalysisUrl(uuidAnalysis);
			
			coreQuestionsService.insertCoreQuestions(coreQuestions);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreQuestionsController]:end sxsyQuestions");

	}
	
	/**
	* 自动生成聪聪题库
	*
	* @param beforeCode 开始题目编号
	* @param afterCode 最后题目编号
	* @param strDir 图片目录
	* @param strDirMovie 视频目录
	* @param strClass 年级
	* @return
	*/
	@RequestMapping(value="/congcong", method=RequestMethod.POST)
	public void congcongQuestions (String beforeCode, String afterCode, String strDir, String strDirMovie, String strClass, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin congcongQuestions");

		if (beforeCode.length() != 6 || afterCode.length() != 6) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "字符串长度请为6!"), response);
			return;
		}	
		if (!beforeCode.substring(0, 1).equals(afterCode.substring(0, 1))) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "前后字符串第一个字母请一致!"), response);
			return;
		}	
		String beforeStr = beforeCode.substring(4, 6);
		int beforeInt = 0;
		String afterStr = afterCode.substring(4, 6);
		int afterInt = 0;
		if (beforeStr.startsWith("0")) {
			beforeInt = Integer.valueOf(beforeStr.substring(1, 2));
		} else {
			beforeInt = Integer.valueOf(beforeStr);
		}
		if (afterStr.startsWith("0")) {
			afterInt = Integer.valueOf(afterStr.substring(1, 2));
		} else {
			afterInt = Integer.valueOf(afterStr);
		}
		for (; beforeInt <= afterInt; beforeInt++) {
			CoreQuestions coreQuestions = new CoreQuestions();
			String uuid = RandomUtil.generateString(16);
			coreQuestions.setCrqtsUuid(uuid);
			if (beforeInt < 10) {
				coreQuestions.setCrqtsCode(beforeCode.substring(0, 4) + "0" + beforeInt);
			} else {
				coreQuestions.setCrqtsCode(beforeCode.substring(0, 4) + beforeInt);
			}
			
			List<CoreQuestions> list = coreQuestionsService.getCoreQuestionsByCode(coreQuestions.getCrqtsCode());
			if (null != list && list.size() > 0) {
				continue;
			}
			
			coreQuestions.setCrqtsCategory("6"); //无
			coreQuestions.setCrqtsLevel(0); //无
			coreQuestions.setCrqtsClass(strClass); //选择值
			coreQuestions.setCrqtsKnowledge("061");	//无		
			coreQuestions.setCrqtsQuesType(1); //图片		
			coreQuestions.setCrqtsAnalysisType(1); //图片			
			coreQuestions.setCrqtsDir(strDir);
			coreQuestions.setCrqtsDirMovie(strDirMovie);
			coreQuestions.setCrqtsCdate(new Date());
			coreQuestions.setCrqtsUdate(new Date());
			
			//生成默认类型为图片
			String uuidQues = RandomUtil.generateString(16);
			CoreAttachment coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(uuidQues);
			coreAttachment.setCratmCdate(new Date());
			coreAttachment.setCratmFileName(coreQuestions.getCrqtsCode()+".png");
			coreAttachment.setCratmStatus(1);
			coreAttachment.setCratmDir("question/" + strDir);
			coreAttachment.setCratmExtension(".png");
			coreAttachment.setCratmType(1);
			coreAttachmentService.insertCoreAttachment(coreAttachment);
			
			coreQuestions.setCrqtsQuesUrl(uuidQues);
			
			String uuidAnalysis = RandomUtil.generateString(16);
			coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(uuidAnalysis);
			coreAttachment.setCratmCdate(new Date());
			coreAttachment.setCratmFileName(coreQuestions.getCrqtsCode()+".png");
			coreAttachment.setCratmStatus(1);
			coreAttachment.setCratmDir("analysis/" + strDir);
			coreAttachment.setCratmExtension(".png");
			coreAttachment.setCratmType(1);
			coreAttachmentService.insertCoreAttachment(coreAttachment);
			
			coreQuestions.setCrqtsAnalysisUrl(uuidAnalysis);
			
			coreQuestionsService.insertCoreQuestions(coreQuestions);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreQuestionsController]:end congcongQuestions");

	}
	
	/**
	* 添加
	*
	* @param crqtsCode 题目编号
	* @param crqtsCategory 类别
	* @param crqtsLevel 难易程度
	* @param crqtsClass 年级
	* @param crqtsKnowledge 知识点
	* @param crqtsDir 图片目录
	* @param crqtsDirMovie 视频目录
	* @param crqtsQuesType 题目类型（0文字1图片）
	* @param crqtsQuesFont 题目文字
	* @param crqtsAnalysisType 解析类型（0文字1图片）
	* @param crqtsAnalysisFont 解析文字
	* @param crqtsProblem 问题（特定的文字格式）
	* @param crqtsAnswer 答案（特定格式文字编辑）
	* @param crqtsColor 背景色
	* @param crqtsMovie 视频路径
	* @param crqtsRemarks 备注
	* @return
	*/
	@RequestMapping(value="/add/coreQuestions", method=RequestMethod.POST)
	public void addCoreQuestions (String crqtsCode, String crqtsCategory, Integer crqtsLevel, String crqtsClass, String crqtsKnowledge, String crqtsDir, String crqtsDirMovie, Integer crqtsQuesType,  String crqtsQuesFont, Integer crqtsAnalysisType, String crqtsAnalysisFont, String crqtsProblem, String crqtsAnswer, String crqtsColor, String crqtsMovie, String crqtsRemarks, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin addCoreQuestions");
		if (StringUtil.isEmpty(crqtsCode)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[题目编号]不能为空!"), response);
			return;
		}
		
		List<CoreQuestions> list = coreQuestionsService.getCoreQuestionsByCode(crqtsCode);
		if (null != list && list.size() > 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "题目编号["+crqtsCode+"]已存在!"), response);
			return;
		}
				CoreQuestions coreQuestions = new CoreQuestions();
		String uuid = RandomUtil.generateString(16);
		coreQuestions.setCrqtsUuid(uuid);
		coreQuestions.setCrqtsCode(crqtsCode);
		coreQuestions.setCrqtsCategory(crqtsCategory);
		coreQuestions.setCrqtsLevel(crqtsLevel);
		coreQuestions.setCrqtsClass(crqtsClass);
		coreQuestions.setCrqtsKnowledge(crqtsKnowledge);
		coreQuestions.setCrqtsDir(crqtsDir);
		coreQuestions.setCrqtsDirMovie(crqtsDirMovie);
		coreQuestions.setCrqtsQuesType(crqtsQuesType);
		coreQuestions.setCrqtsQuesFont(crqtsQuesFont);
		coreQuestions.setCrqtsAnalysisType(crqtsAnalysisType);		
		coreQuestions.setCrqtsAnalysisFont(crqtsAnalysisFont);
		coreQuestions.setCrqtsProblem(crqtsProblem);
		coreQuestions.setCrqtsAnswer(crqtsAnswer);
		coreQuestions.setCrqtsCdate(new Date());
		coreQuestions.setCrqtsColor(crqtsColor);
		coreQuestions.setCrqtsUdate(new Date());
		coreQuestions.setCrqtsMovie(crqtsMovie);
		coreQuestions.setCrqtsRemarks(crqtsRemarks);
		
		if (crqtsQuesType == 1) {
			String uuidQues = RandomUtil.generateString(16);
			CoreAttachment coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(uuidQues);
			coreAttachment.setCratmCdate(new Date());
			coreAttachment.setCratmFileName(crqtsCode+".png");
			coreAttachment.setCratmStatus(1);
			coreAttachment.setCratmDir("question/" + crqtsDir);
			coreAttachment.setCratmExtension(".png");
			coreAttachment.setCratmType(1);
			coreAttachmentService.insertCoreAttachment(coreAttachment);
			
			coreQuestions.setCrqtsQuesUrl(uuidQues);
		}
		
		if (crqtsAnalysisType == 1) {
			String uuidAnalysis = RandomUtil.generateString(16);
			CoreAttachment coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(uuidAnalysis);
			coreAttachment.setCratmCdate(new Date());
			coreAttachment.setCratmFileName(crqtsCode+".png");
			coreAttachment.setCratmStatus(1);
			coreAttachment.setCratmDir("analysis/" + crqtsDir);
			coreAttachment.setCratmExtension(".png");
			coreAttachment.setCratmType(1);
			coreAttachmentService.insertCoreAttachment(coreAttachment);
			
			coreQuestions.setCrqtsAnalysisUrl(uuidAnalysis);
		}
		
		coreQuestionsService.insertCoreQuestions(coreQuestions);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreQuestionsController]:end addCoreQuestions");

	}

	/**
	* 修改
	*
	* @param crqtsUuid 标识UUID
	* @param crqtsCode 题目编号
	* @param crqtsCategory 类别
	* @param crqtsLevel 难易程度
	* @param crqtsClass 年级
	* @param crqtsKnowledge 知识点
	* @param crqtsDir 图片目录
	* @param crqtsDirMovie 视频目录
	* @param crqtsQuesType 题目类型（0文字1图片）
	* @param crqtsQuesUrl 题目图片URL
	* @param crqtsQuesFont 题目文字
	* @param crqtsAnalysisType 解析类型（0文字1图片）
	* @param crqtsAnalysisUrl 解析图片URL
	* @param crqtsAnalysisFont 解析文字
	* @param crqtsProblem 问题（特定的文字格式）
	* @param crqtsAnswer 答案（特定格式文字编辑）
	* @param crqtsColor 背景色
	* @param crqtsMovie 视频路径
	* @param crqtsRemarks 备注
	* @return
	*/
	@RequestMapping(value="/update/coreQuestions", method=RequestMethod.POST)
	public void updateCoreQuestions (String crqtsUuid, String crqtsCode, String crqtsCategory, Integer crqtsLevel, String crqtsClass, String crqtsKnowledge, String crqtsDir, String crqtsDirMovie, Integer crqtsQuesType, String crqtsQuesUrl, String crqtsQuesFont, Integer crqtsAnalysisType, String crqtsAnalysisUrl, String crqtsAnalysisFont, String crqtsProblem, String crqtsAnswer, String crqtsColor, String crqtsMovie, String crqtsRemarks, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin updateCoreQuestions");
		if (StringUtil.isEmpty(crqtsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreQuestions coreQuestions = new CoreQuestions();
		coreQuestions.setCrqtsUuid(crqtsUuid);
		coreQuestions.setCrqtsCategory(crqtsCategory);
		coreQuestions.setCrqtsLevel(crqtsLevel);
		coreQuestions.setCrqtsClass(crqtsClass);
		coreQuestions.setCrqtsKnowledge(crqtsKnowledge);
		coreQuestions.setCrqtsDir(crqtsDir);
		coreQuestions.setCrqtsDirMovie(crqtsDirMovie);
		coreQuestions.setCrqtsQuesType(crqtsQuesType);
		coreQuestions.setCrqtsQuesFont(crqtsQuesFont);
		coreQuestions.setCrqtsAnalysisType(crqtsAnalysisType);		
		coreQuestions.setCrqtsAnalysisFont(crqtsAnalysisFont);
		coreQuestions.setCrqtsProblem(crqtsProblem);
		coreQuestions.setCrqtsAnswer(crqtsAnswer);
		coreQuestions.setCrqtsColor(crqtsColor);
		coreQuestions.setCrqtsUdate(new Date());
		coreQuestions.setCrqtsMovie(crqtsMovie);
		coreQuestions.setCrqtsRemarks(crqtsRemarks);
		
		//从图片改为图片只需要手动替换，从文字改为图片需要新添加图片附件
		if (crqtsQuesType == 1) {
			if (null == crqtsQuesUrl || ("").equals(crqtsQuesUrl)) { //从文字改为图片
				String uuidQues = RandomUtil.generateString(16);
				CoreAttachment coreAttachment = new CoreAttachment();
				coreAttachment.setCratmUuid(uuidQues);
				coreAttachment.setCratmCdate(new Date());
				coreAttachment.setCratmFileName(crqtsCode+".png");
				coreAttachment.setCratmStatus(1);
				coreAttachment.setCratmDir("question/" + crqtsDir);
				coreAttachment.setCratmExtension(".png");
				coreAttachment.setCratmType(1);
				coreAttachmentService.insertCoreAttachment(coreAttachment);
				
				coreQuestions.setCrqtsQuesUrl(uuidQues);
			} else {
				CoreAttachment coreAttachment = new CoreAttachment();
				coreAttachment.setCratmUuid(crqtsQuesUrl);				
				coreAttachment.setCratmDir("question/" + crqtsDir);
				coreAttachmentService.updateCoreAttachment(coreAttachment);
			}			
		}
		
		if (crqtsAnalysisType == 1) {
			if (null == crqtsAnalysisUrl || ("").equals(crqtsAnalysisUrl)) { //从文字改为图片
				String uuidAnalysis = RandomUtil.generateString(16);
				CoreAttachment coreAttachment = new CoreAttachment();
				coreAttachment.setCratmUuid(uuidAnalysis);
				coreAttachment.setCratmCdate(new Date());
				coreAttachment.setCratmFileName(crqtsCode+".png");
				coreAttachment.setCratmStatus(1);
				coreAttachment.setCratmDir("analysis/" + crqtsDir);
				coreAttachment.setCratmExtension(".png");
				coreAttachment.setCratmType(1);
				coreAttachmentService.insertCoreAttachment(coreAttachment);
				
				coreQuestions.setCrqtsAnalysisUrl(uuidAnalysis);
			} else {
				CoreAttachment coreAttachment = new CoreAttachment();
				coreAttachment.setCratmUuid(crqtsQuesUrl);				
				coreAttachment.setCratmDir("question/" + crqtsDir);
				coreAttachmentService.updateCoreAttachment(coreAttachment);
			}
		}
		
		coreQuestionsService.updateCoreQuestions(coreQuestions);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreQuestionsController]:end updateCoreQuestions");

	}

	/**
	* 删除
	*
	* @param crqtsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreQuestions (String crqtsUuid, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin deleteCoreQuestions");
		if (StringUtil.isEmpty(crqtsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreQuestions coreQuestions = new CoreQuestions();
		coreQuestions.setCrqtsUuid(crqtsUuid);

		coreQuestionsService.deleteCoreQuestions(coreQuestions);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreQuestionsController]:end deleteCoreQuestions");

	}

	/**
	* 批量删除
	*
	* @param crqtsUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreQuestions (String crqtsUuids, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin deleteBatchCoreQuestions");
		if (StringUtil.isEmpty(crqtsUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crqtsUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreQuestionsService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreQuestionsController]:end deleteBatchCoreQuestions");

	}

	/**
	* 获取单个
	*
	* @param crqtsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreQuestions (String crqtsUuid, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin viewsCoreQuestions");
		if (StringUtil.isEmpty(crqtsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreQuestions coreQuestions = new CoreQuestions();
		coreQuestions.setCrqtsUuid(crqtsUuid);

		coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);

		CoreQuestionsVO coreQuestionsVO = new CoreQuestionsVO();
		coreQuestionsVO.convertPOToVO(coreQuestions);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreQuestionsVO), response);
		logger.info("[CoreQuestionsController]:end viewsCoreQuestions");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreQuestionsList (HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin findCoreQuestionsList");
		List<CoreQuestions> lists = coreQuestionsService.findCoreQuestionsList();
		List<CoreQuestionsVO> vos = new ArrayList<CoreQuestionsVO>();
		CoreQuestionsVO vo;
		for (CoreQuestions coreQuestions : lists) {
			vo = new CoreQuestionsVO();
			vo.convertPOToVO(coreQuestions);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreQuestionsController]:end findCoreQuestionsList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crqtsCode 题目编号
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreQuestionsPage (String crqtsCode, String crqtsClass, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreQuestionsController]:begin findCoreQuestionsPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreQuestions> page = coreQuestionsService.findCoreQuestionsPage(crqtsClass, crqtsCode, pageNum, pageSize);
		Page<CoreQuestionsVO> pageVO = new Page<CoreQuestionsVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreQuestionsVO> vos = new ArrayList<CoreQuestionsVO>();
		CoreQuestionsVO vo;
		for (CoreQuestions coreQuestions : page.getResult()) {
			vo = new CoreQuestionsVO();
			vo.convertPOToVO(coreQuestions);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreQuestionsController]:end findCoreQuestionsPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}