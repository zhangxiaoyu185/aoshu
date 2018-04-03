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
import com.xiaoyu.lingdian.entity.CorePapers;
import com.xiaoyu.lingdian.entity.CorePapersAnswer;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CorePapersAnswerService;
import com.xiaoyu.lingdian.service.CorePapersService;
import com.xiaoyu.lingdian.service.CoreQuestionsService;
import com.xiaoyu.lingdian.vo.ContentList;
import com.xiaoyu.lingdian.vo.CorePapersVO;
import com.xiaoyu.lingdian.vo.Problem;

@Controller
@RequestMapping(value="/corePapers")
public class CorePapersController extends BaseController {

	/**
	* 模拟试卷表
	*/
	@Autowired
	private CorePapersService corePapersService;
	
	/**
	* 题目表
	*/
	@Autowired
	private CoreQuestionsService coreQuestionsService;
	
	/**
	* 试卷回答表
	*/
	@Autowired
	private CorePapersAnswerService corePapersAnswerService;
	
	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 添加
	*
	* @param crpesName 试卷名
	* @param crpesScore 分数
	* @param crpesClass 所属年级
	* @return
	*/
	@RequestMapping(value="/add/corePapers", method=RequestMethod.POST)
	public void addCorePapers (String crpesName, Integer crpesScore, String crpesClass, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin addCorePapers");
		CorePapers corePapers = new CorePapers();
		String uuid = RandomUtil.generateString(16);
		corePapers.setCrpesUuid(uuid);
		corePapers.setCrpesName(crpesName);
		corePapers.setCrpesScore(crpesScore);
		corePapers.setCrpesClass(crpesClass);
		corePapers.setCrpesCdate(new Date());
		corePapers.setCrpesUdate(new Date());

		corePapersService.insertCorePapers(corePapers);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CorePapersController]:end addCorePapers");

	}

	/**
	* 修改试卷
	*
	* @param crpesUuid 标识UUID
	* @param crpesName 试卷名
	* @param crpesScore 分数
	* @param crpesClass 所属年级
	* @return
	*/
	@RequestMapping(value="/update/corePapers", method=RequestMethod.POST)
	public void updateCorePapers (String crpesUuid, String crpesName, Integer crpesScore, String crpesClass, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin updateCorePapers");

		if (StringUtil.isEmpty(crpesUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CorePapers corePapers = new CorePapers();
		corePapers.setCrpesUuid(crpesUuid);
		corePapers.setCrpesName(crpesName);
		corePapers.setCrpesScore(crpesScore);
		corePapers.setCrpesClass(crpesClass);
		corePapers.setCrpesUdate(new Date());

		corePapersService.updateCorePapers(corePapers);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CorePapersController]:end updateCorePapers");

	}
	
	/**
	* 修改题目
	*
	* @param crpesUuid 标识UUID
	* @param crpesContent 内容
	* @return
	*/
	@RequestMapping(value="/update/corePapers/by/ques", method=RequestMethod.POST)
	public void updateCorePapersByQues (String crpesUuid, String crpesContent, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin updateCorePapersByQues");
		if (StringUtil.isEmpty(crpesUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CorePapers corePapers = new CorePapers();
		corePapers.setCrpesUuid(crpesUuid);
		corePapers.setCrpesContent(crpesContent);
		corePapers.setCrpesUdate(new Date());

		corePapersService.updateCorePapers(corePapers);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CorePapersController]:end updateCorePapersByQues");

	}

	/**
	* 删除
	*
	* @param crpesUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCorePapers (String crpesUuid, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin deleteCorePapers");
		if (StringUtil.isEmpty(crpesUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		
		//根据试卷UUID查询是否有人测验过
		List<CorePapersAnswer> answerList = corePapersAnswerService.findCorePapersAnswerList(crpesUuid, null);
		if (null != answerList && answerList.size() > 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该试卷有人测验过,不能删除!"), response);
			return;
		}
				CorePapers corePapers = new CorePapers();
		corePapers.setCrpesUuid(crpesUuid);

		corePapersService.deleteCorePapers(corePapers);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CorePapersController]:end deleteCorePapers");

	}

	/**
	* 批量删除
	*
	* @param crpesUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCorePapers (String crpesUuids, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin deleteBatchCorePapers");
		if (StringUtil.isEmpty(crpesUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crpesUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			List<CorePapersAnswer> answerList = corePapersAnswerService.findCorePapersAnswerList(uuids[i], null);
			if (null == answerList || answerList.size() <= 0) {
				list.add(uuids[i]);
			}
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		corePapersService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CorePapersController]:end deleteBatchCorePapers");

	}

	/**
	* 获取单个
	*
	* @param crpesUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCorePapers (String crpesUuid, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin viewsCorePapers");
		if (StringUtil.isEmpty(crpesUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CorePapers corePapers = new CorePapers();
		corePapers.setCrpesUuid(crpesUuid);

		corePapers = corePapersService.getCorePapers(corePapers);

		CorePapersVO corePapersVO = new CorePapersVO();
		corePapersVO.convertPOToVO(corePapers);
		//获取所属年级名称
		if(!"".equals(corePapers.getCrpesClass())){
			CoreClass coreClass=new CoreClass();
			coreClass.setCrcasUuid(corePapers.getCrpesClass());
			coreClass=coreClassService.getCoreClass(coreClass);
			corePapersVO.setCrpesClassName(coreClass.getCrcasName());
		}
		
		if (StringUtil.isEmpty(corePapersVO.getCrpesContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "没有题目", corePapersVO),response);
			return;
		}
		
		ContentList contentList = null;
		List<Problem> problemList = new ArrayList<Problem>();
		try {
			contentList = JSON.parseObject(corePapersVO.getCrpesContent(), ContentList.class);
			problemList = contentList.getProblem();
		} catch (Exception e) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "题目格式有问题", corePapersVO),response);
			return;
		}
		for (Problem problem : problemList) {
			CoreQuestions coreQuestions = new CoreQuestions();
			coreQuestions.setCrqtsUuid(problem.getCrqtsUuid());
			coreQuestions = coreQuestionsService.getCoreQuestions(coreQuestions);
			problem.convertPOToVO(coreQuestions);
		}
		contentList.setProblem(problemList);
		corePapersVO.setCrpesContent(JSON.toJSONString(contentList));
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", corePapersVO), response);
		logger.info("[CorePapersController]:end viewsCorePapers");

	}

	/**
	* 获取列表<List>,下拉框
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCorePapersList (HttpServletResponse response) {
		logger.info("[CorePapersController]:begin findCorePapersList");
		Page<CorePapers> page = corePapersService.findCorePapersPage(null, null, 1, 1000);
		List<CorePapersVO> vos = new ArrayList<CorePapersVO>();
		CorePapersVO vo;
		for (CorePapers corePapers : page.getResult()) {
			vo = new CorePapersVO();
			vo.convertPOToVO(corePapers);
			//获取所属年级名称
			if(!"".equals(corePapers.getCrpesClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(corePapers.getCrpesClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrpesClassName(coreClass.getCrcasName());		
			}
			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CorePapersController]:end findCorePapersList");

	}
	
	/**
	* 获取列表<Page>
	* 
	* @param crpesClass 所属年级
	* @param crpesName 试卷名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCorePapersPage (String crpesClass, String crpesName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin findCorePapersPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CorePapers> page = corePapersService.findCorePapersPage(crpesClass, crpesName, pageNum, pageSize);
		Page<CorePapersVO> pageVO = new Page<CorePapersVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CorePapersVO> vos = new ArrayList<CorePapersVO>();
		CorePapersVO vo;
		for (CorePapers corePapers : page.getResult()) {
			vo = new CorePapersVO();
			vo.convertPOToVO(corePapers);
			//获取所属年级名称
			if(!"".equals(corePapers.getCrpesClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(corePapers.getCrpesClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrpesClassName(coreClass.getCrcasName());		
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CorePapersController]:end findCorePapersPage");

	}
	
	/**
	* 获取未添加列表<Page>
	* 
	* @param crgpsGrade 所属班级
	* @param crpesClass 所属年级
	* @param crpesName 试卷名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd/wtj", method=RequestMethod.POST)
	public void findCorePapersPageWTJ (String crgpsGrade, String crpesClass, String crpesName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CorePapersController]:begin findCorePapersPageWTJ");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 15;
		}
		Page<CorePapers> page = corePapersService.findCorePapersPageWTJ(crgpsGrade, crpesClass, crpesName, pageNum, pageSize);
		Page<CorePapersVO> pageVO = new Page<CorePapersVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CorePapersVO> vos = new ArrayList<CorePapersVO>();
		CorePapersVO vo;
		for (CorePapers corePapers : page.getResult()) {
			vo = new CorePapersVO();
			vo.convertPOToVO(corePapers);
			//获取所属年级名称
			if(!"".equals(corePapers.getCrpesClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(corePapers.getCrpesClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrpesClassName(coreClass.getCrcasName());		
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CorePapersController]:end findCorePapersPageWTJ");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}