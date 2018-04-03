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
import com.xiaoyu.lingdian.entity.CoreCategory;
import com.xiaoyu.lingdian.service.CoreCategoryService;
import com.xiaoyu.lingdian.vo.CoreCategoryVO;

@Controller
@RequestMapping(value="/coreCategory")
public class CoreCategoryController extends BaseController {

	/**
	* 类别表
	*/
	@Autowired
	private CoreCategoryService coreCategoryService;
	
	/**
	* 添加
	*
	* @param crceyName 类别名称
	* @return
	*/
	@RequestMapping(value="/add/coreCategory", method=RequestMethod.POST)
	public void addCoreCategory (String crceyName, HttpServletResponse response) {
		logger.info("[CoreCategoryController]:begin addCoreCategory");
		CoreCategory coreCategory = new CoreCategory();
		String uuid = RandomUtil.generateString(16);
		coreCategory.setCrceyUuid(uuid);
		coreCategory.setCrceyName(crceyName);

		coreCategoryService.insertCoreCategory(coreCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreCategoryController]:end addCoreCategory");

	}

	/**
	* 修改
	*
	* @param crceyUuid 标识UUID
	* @param crceyName 类别名称
	* @return
	*/
	@RequestMapping(value="/update/coreCategory", method=RequestMethod.POST)
	public void updateCoreCategory (String crceyUuid, String crceyName, HttpServletResponse response) {
		logger.info("[CoreCategoryController]:begin updateCoreCategory");
		if (StringUtil.isEmpty(crceyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreCategory coreCategory = new CoreCategory();
		coreCategory.setCrceyUuid(crceyUuid);
		coreCategory.setCrceyName(crceyName);

		coreCategoryService.updateCoreCategory(coreCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreCategoryController]:end updateCoreCategory");

	}

	/**
	* 删除
	*
	* @param crceyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreCategory (String crceyUuid, HttpServletResponse response) {
		logger.info("[CoreCategoryController]:begin deleteCoreCategory");
		if (StringUtil.isEmpty(crceyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreCategory coreCategory = new CoreCategory();
		coreCategory.setCrceyUuid(crceyUuid);

		coreCategoryService.deleteCoreCategory(coreCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreCategoryController]:end deleteCoreCategory");

	}

	/**
	* 批量删除
	*
	* @param crceyUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreCategory (String crceyUuids, HttpServletResponse response) {
		logger.info("[CoreCategoryController]:begin deleteBatchCoreCategory");
		if (StringUtil.isEmpty(crceyUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crceyUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreCategoryService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreCategoryController]:end deleteBatchCoreCategory");

	}

	/**
	* 获取单个
	*
	* @param crceyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreCategory (String crceyUuid, HttpServletResponse response) {
		logger.info("[CoreCategoryController]:begin viewsCoreCategory");
		if (StringUtil.isEmpty(crceyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreCategory coreCategory = new CoreCategory();
		coreCategory.setCrceyUuid(crceyUuid);

		coreCategory = coreCategoryService.getCoreCategory(coreCategory);

		CoreCategoryVO coreCategoryVO = new CoreCategoryVO();
		coreCategoryVO.convertPOToVO(coreCategory);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreCategoryVO), response);
		logger.info("[CoreCategoryController]:end viewsCoreCategory");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreCategoryList (HttpServletResponse response) {
		logger.info("[CoreCategoryController]:begin findCoreCategoryList");
		List<CoreCategory> lists = coreCategoryService.findCoreCategoryList();
		List<CoreCategoryVO> vos = new ArrayList<CoreCategoryVO>();
		CoreCategoryVO vo;
		for (CoreCategory coreCategory : lists) {
			vo = new CoreCategoryVO();
			vo.convertPOToVO(coreCategory);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreCategoryController]:end findCoreCategoryList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crceyName 类别名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreCategoryPage (Integer pageNum, Integer pageSize, String crceyName, HttpServletResponse response) {
		logger.info("[CoreCategoryController]:begin findCoreCategoryPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreCategory> page = coreCategoryService.findCoreCategoryPage(crceyName, pageNum, pageSize);
		Page<CoreCategoryVO> pageVO = new Page<CoreCategoryVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreCategoryVO> vos = new ArrayList<CoreCategoryVO>();
		CoreCategoryVO vo;
		for (CoreCategory coreCategory : page.getResult()) {
			vo = new CoreCategoryVO();
			vo.convertPOToVO(coreCategory);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreCategoryController]:end findCoreCategoryPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}