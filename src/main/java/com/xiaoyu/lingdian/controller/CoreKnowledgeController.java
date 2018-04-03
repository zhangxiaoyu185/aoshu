package com.xiaoyu.lingdian.controller;

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
import com.xiaoyu.lingdian.entity.CoreKnowledge;
import com.xiaoyu.lingdian.service.CoreKnowledgeService;
import com.xiaoyu.lingdian.vo.CoreKnowledgeVO;

@Controller
@RequestMapping(value="/coreKnowledge")
public class CoreKnowledgeController extends BaseController {

	/**
	* 知识点表
	*/
	@Autowired
	private CoreKnowledgeService coreKnowledgeService;
	
	/**
	* 根据类型获取知识点列表<List>
	* @param crkleCategory 所属类别
	* @return
	*/
	@RequestMapping(value="/find/all/bys", method=RequestMethod.POST)
	public void findCoreKnowledgeListBys (String crkleCategory, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin findCoreKnowledgeListByCategory");

		List<CoreKnowledge> lists = coreKnowledgeService.findCoreKnowledgeListBys(crkleCategory);
		List<CoreKnowledgeVO> vos = new ArrayList<CoreKnowledgeVO>();
		CoreKnowledgeVO vo;
		CoreKnowledge fatherCoreKnowledge;
		for (CoreKnowledge coreKnowledge : lists) {
			vo = new CoreKnowledgeVO();
			vo.convertPOToVO(coreKnowledge);
			if(!StringUtil.isEmpty(vo.getCrkleTop())){
				fatherCoreKnowledge = new CoreKnowledge();
				fatherCoreKnowledge.setCrkleUuid(vo.getCrkleTop());
				fatherCoreKnowledge=coreKnowledgeService.getCoreKnowledge(fatherCoreKnowledge);
				if(fatherCoreKnowledge!=null){
					vo.setCrkleFatherName(fatherCoreKnowledge.getCrkleName());
				}
			}
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreKnowledgeController]:end findCoreKnowledgeListByCategory");

	}
	
	/**
	* 获取大知识点集合<Page>	
	* @return
	*/
	@RequestMapping(value="/find/page/top", method=RequestMethod.POST)
	public void findCoreKnowledgeTopPage (String crkleName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin CoreKnowledgeController");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreKnowledge> page = coreKnowledgeService.findCoreKnowledgeTop(crkleName, pageNum, pageSize);
		Page<CoreKnowledgeVO> pageVO = new Page<CoreKnowledgeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreKnowledgeVO> vos = new ArrayList<CoreKnowledgeVO>();
		CoreKnowledgeVO vo;
		for (CoreKnowledge coreKnowledge : page.getResult()) {
			vo = new CoreKnowledgeVO();

			vo.convertPOToVO(coreKnowledge);

			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreKnowledgeController]:end CoreKnowledgeController");

	}
	
	/**
	* 添加
	*
	* @param crkleName 知识点名称
	* @param crkleTop 所属大知识点
	* @param crkleCategory 所属类别
	* @return
	*/
	@RequestMapping(value="/add/coreKnowledge", method=RequestMethod.POST)
	public void addCoreKnowledge (String crkleName, String crkleTop, String crkleCategory, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin addCoreKnowledge");
		CoreKnowledge coreKnowledge = new CoreKnowledge();
		String uuid = RandomUtil.generateString(16);
		coreKnowledge.setCrkleUuid(uuid);
		coreKnowledge.setCrkleName(crkleName);
		coreKnowledge.setCrkleTop(crkleTop);
		coreKnowledge.setCrkleCategory(crkleCategory);
		
		coreKnowledgeService.insertCoreKnowledge(coreKnowledge);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreKnowledgeController]:end addCoreKnowledge");

	}

	/**
	* 修改
	*
	* @param crkleUuid 标识UUID
	* @param crkleName 知识点名称
	* @param crkleTop 所属大知识点
	* @param crkleCategory 所属类别
	* @return
	*/
	@RequestMapping(value="/update/coreKnowledge", method=RequestMethod.POST)
	public void updateCoreKnowledge (String crkleUuid, String crkleName, String crkleTop, String crkleCategory, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin updateCoreKnowledge");
		if (StringUtil.isEmpty(crkleUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreKnowledge coreKnowledge = new CoreKnowledge();
		coreKnowledge.setCrkleUuid(crkleUuid);
		coreKnowledge.setCrkleName(crkleName);
		coreKnowledge.setCrkleTop(crkleTop);
		coreKnowledge.setCrkleCategory(crkleCategory);
		
		coreKnowledgeService.updateCoreKnowledge(coreKnowledge);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreKnowledgeController]:end updateCoreKnowledge");

	}

	/**
	* 删除
	*
	* @param crkleUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreKnowledge (String crkleUuid, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin deleteCoreKnowledge");
		if (StringUtil.isEmpty(crkleUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreKnowledge coreKnowledge = new CoreKnowledge();
		coreKnowledge.setCrkleUuid(crkleUuid);

		coreKnowledgeService.deleteCoreKnowledge(coreKnowledge);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreKnowledgeController]:end deleteCoreKnowledge");

	}

	/**
	* 批量删除
	*
	* @param crkleUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreKnowledge (String crkleUuids, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin deleteBatchCoreKnowledge");
		if (StringUtil.isEmpty(crkleUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crkleUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreKnowledgeService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreKnowledgeController]:end deleteBatchCoreKnowledge");

	}

	/**
	* 获取单个
	*
	* @param crkleUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreKnowledge (String crkleUuid, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin viewsCoreKnowledge");
		if (StringUtil.isEmpty(crkleUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreKnowledge coreKnowledge = new CoreKnowledge();
		coreKnowledge.setCrkleUuid(crkleUuid);

		coreKnowledge = coreKnowledgeService.getCoreKnowledge(coreKnowledge);

		CoreKnowledgeVO coreKnowledgeVO = new CoreKnowledgeVO();
		coreKnowledgeVO.convertPOToVO(coreKnowledge);
		if(!StringUtil.isEmpty(coreKnowledgeVO.getCrkleTop())){
			CoreKnowledge fatherCoreKnowledge = new CoreKnowledge();
			fatherCoreKnowledge.setCrkleUuid(coreKnowledgeVO.getCrkleTop());
			fatherCoreKnowledge=coreKnowledgeService.getCoreKnowledge(fatherCoreKnowledge);
			if(fatherCoreKnowledge!=null){
				coreKnowledgeVO.setCrkleFatherName(fatherCoreKnowledge.getCrkleName());
			}
		}		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreKnowledgeVO), response);
		logger.info("[CoreKnowledgeController]:end viewsCoreKnowledge");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreKnowledgeList (HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin findCoreKnowledgeList");
		List<CoreKnowledge> lists = coreKnowledgeService.findCoreKnowledgeList();
		List<CoreKnowledgeVO> vos = new ArrayList<CoreKnowledgeVO>();
		CoreKnowledgeVO vo;
		CoreKnowledge fatherCoreKnowledge;
		for (CoreKnowledge coreKnowledge : lists) {
			vo = new CoreKnowledgeVO();			vo.convertPOToVO(coreKnowledge);
			if(!StringUtil.isEmpty(vo.getCrkleTop())){
				fatherCoreKnowledge = new CoreKnowledge();
				fatherCoreKnowledge.setCrkleUuid(vo.getCrkleTop());
				fatherCoreKnowledge=coreKnowledgeService.getCoreKnowledge(fatherCoreKnowledge);
				if(fatherCoreKnowledge!=null){
					vo.setCrkleFatherName(fatherCoreKnowledge.getCrkleName());
				}
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreKnowledgeController]:end findCoreKnowledgeList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreKnowledgePage (String crkleName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreKnowledgeController]:begin findCoreKnowledgePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreKnowledge> page = coreKnowledgeService.findCoreKnowledgePage(crkleName, pageNum, pageSize);
		Page<CoreKnowledgeVO> pageVO = new Page<CoreKnowledgeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreKnowledgeVO> vos = new ArrayList<CoreKnowledgeVO>();
		CoreKnowledgeVO vo;
		CoreKnowledge fatherCoreKnowledge;
		for (CoreKnowledge coreKnowledge : page.getResult()) {
			vo = new CoreKnowledgeVO();
			vo.convertPOToVO(coreKnowledge);
			if(!StringUtil.isEmpty(vo.getCrkleTop())){
				fatherCoreKnowledge = new CoreKnowledge();
				fatherCoreKnowledge.setCrkleUuid(vo.getCrkleTop());
				fatherCoreKnowledge=coreKnowledgeService.getCoreKnowledge(fatherCoreKnowledge);
				if(fatherCoreKnowledge!=null){
					vo.setCrkleFatherName(fatherCoreKnowledge.getCrkleName());
				}
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreKnowledgeController]:end findCoreKnowledgePage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}