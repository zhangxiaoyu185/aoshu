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
import com.xiaoyu.lingdian.entity.CoreGradePapers;
import com.xiaoyu.lingdian.entity.CorePapersAnswer;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreGradePapersService;
import com.xiaoyu.lingdian.service.CorePapersAnswerService;
import com.xiaoyu.lingdian.vo.CoreGradePapersVO;

@Controller
@RequestMapping(value="/coreGradePapers")
public class CoreGradePapersController extends BaseController {

	/**
	* 班级模拟试卷表
	*/
	@Autowired
	private CoreGradePapersService coreGradePapersService;
	
	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 试卷回答表
	*/
	@Autowired
	private CorePapersAnswerService corePapersAnswerService;
	
	/**
	* 添加
	*
	* @param crgpsGrade 班级UUID
	* @param crgpsPapers 试卷UUID
	* @param crpesClass 所属年级
	* @return
	*/
	@RequestMapping(value="/add/coreGradePapers", method=RequestMethod.POST)
	public void addCoreGradePapers (String crgpsGrade, String crgpsPapers, String crpesClass, HttpServletResponse response) {
		logger.info("[CoreGradePapersController]:begin addCoreGradePapers");

		//判断该班级下是否已经拥有该模拟试卷权限
		if (coreGradePapersService.getCoreGradePapersByGradePapers(crgpsGrade, crgpsPapers) != null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该班级下已拥有这份试卷权限!"),response);
			return;
		}
				CoreGradePapers coreGradePapers = new CoreGradePapers();
		String uuid = RandomUtil.generateString(16);
		coreGradePapers.setCrgpsUuid(uuid);
		coreGradePapers.setCrgpsGrade(crgpsGrade);
		coreGradePapers.setCrgpsPapers(crgpsPapers);
		coreGradePapers.setCrgpsGqsj("0");
		//查找数据库中该班级年级下最大的排序号
		int max = coreGradePapersService.getMaxOrdByGradePapersClass(crgpsGrade, crpesClass);
		coreGradePapers.setCrgpsOrd(max + 1);

		coreGradePapersService.insertCoreGradePapers(coreGradePapers);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreGradePapersController]:end addCoreGradePapers");

	}

	/**
	* 修改
	*
	* @param crgpsUuid 标识UUID
	* @param crgpsOrd 排序号
	* @param crgpsGqsj 过期时间
	* @return
	*/
	@RequestMapping(value="/update/coreGradePapers", method=RequestMethod.POST)
	public void updateCoreGradePapers (String crgpsUuid, Integer crgpsOrd, String crgpsGqsj, HttpServletResponse response) {
		logger.info("[CoreGradePapersController]:begin updateCoreGradePapers");
		if (StringUtil.isEmpty(crgpsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGradePapers coreGradePapers = new CoreGradePapers();
		coreGradePapers.setCrgpsUuid(crgpsUuid);
		coreGradePapers.setCrgpsOrd(crgpsOrd);
		coreGradePapers.setCrgpsGqsj(crgpsGqsj);
		
		coreGradePapersService.updateCoreGradePapers(coreGradePapers);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreGradePapersController]:end updateCoreGradePapers");

	}


	/**
	* 删除
	*
	* @param crgpsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreGradePapers (String crgpsUuid, HttpServletResponse response) {
		logger.info("[CoreGradePapersController]:begin deleteCoreGradePapers");
		if (StringUtil.isEmpty(crgpsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGradePapers coreGradePapers = new CoreGradePapers();
		coreGradePapers.setCrgpsUuid(crgpsUuid);

		coreGradePapersService.deleteCoreGradePapers(coreGradePapers);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreGradePapersController]:end deleteCoreGradePapers");

	}

	/**
	* 批量删除
	*
	* @param crgpsUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreGradePapers (String crgpsUuids, HttpServletResponse response) {
		logger.info("[CoreGradePapersController]:begin deleteBatchCoreGradePapers");
		if (StringUtil.isEmpty(crgpsUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crgpsUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreGradePapersService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreGradePapersController]:end deleteBatchCoreGradePapers");

	}

	/**
	* 获取单个
	*
	* @param crgpsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreGradePapers (String crgpsUuid, HttpServletResponse response) {
		logger.info("[CoreGradePapersController]:begin viewsCoreGradePapers");
		if (StringUtil.isEmpty(crgpsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGradePapers coreGradePapers = new CoreGradePapers();
		coreGradePapers.setCrgpsUuid(crgpsUuid);

		coreGradePapers = coreGradePapersService.getCoreGradePapers(coreGradePapers);

		CoreGradePapersVO coreGradePapersVO = new CoreGradePapersVO();
		coreGradePapersVO.convertPOToVO(coreGradePapers);
		//获取所属年级名称
		if(!"".equals(coreGradePapers.getCrpesClass())){
			CoreClass coreClass=new CoreClass();
			coreClass.setCrcasUuid(coreGradePapers.getCrpesClass());
			coreClass=coreClassService.getCoreClass(coreClass);
			coreGradePapersVO.setCrpesClassName(coreClass.getCrcasName());
		}
						writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreGradePapersVO), response);
		logger.info("[CoreGradePapersController]:end viewsCoreGradePapers");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crgpsGrade 所属班级
	* @param userUuid 用户UUID（前端）
	* @param crpesClass 所属年级
	* @param crpesName 试卷名称（后端）
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreGradePapersPage (String crgpsGrade, String userUuid, String crpesClass, String crpesName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreGradePapersController]:begin findCoreGradePapersPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		
		if (StringUtil.isEmpty(crgpsGrade)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属班级不能为空!"), response);
			return;
		}
		
		Page<CoreGradePapers> page = coreGradePapersService.findCoreGradePapersPage(crgpsGrade, crpesClass, crpesName, pageNum, pageSize);
		Page<CoreGradePapersVO> pageVO = new Page<CoreGradePapersVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreGradePapersVO> vos = new ArrayList<CoreGradePapersVO>();
		CoreGradePapersVO vo;
		for (CoreGradePapers coreGradePapers : page.getResult()) {
			vo = new CoreGradePapersVO();			vo.convertPOToVO(coreGradePapers);
			//获取所属年级名称
			if(!"".equals(coreGradePapers.getCrpesClass())){
				CoreClass coreClass=new CoreClass();
				coreClass.setCrcasUuid(coreGradePapers.getCrpesClass());
				coreClass=coreClassService.getCoreClass(coreClass);
				vo.setCrpesClassName(coreClass.getCrcasName());
				if (!StringUtil.isEmpty(userUuid)) {
					//查询是否测验过
					List<CorePapersAnswer> answerList = corePapersAnswerService.findCorePapersAnswerList(coreGradePapers.getCrpesUuid(), userUuid);
					if (null != answerList && answerList.size() > 0) {
						vo.setIsTest("Y");
						vo.setAnswerDate(DateUtil.formatTimesTampDate(answerList.get(0).getCrpsaUdate()));
						vo.setAnswerUuid(answerList.get(0).getCrpsaUuid());
						vo.setScore(String.valueOf(answerList.get(0).getCrpsaScore()==null?0:answerList.get(0).getCrpsaScore()));
						vo.setTime(answerList.get(0).getCrpsaTime());
					} else {
						vo.setIsTest("N");
					}
				} else {
					vo.setIsTest("N");
				}		
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreGradePapersController]:end findCoreGradePapersPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}