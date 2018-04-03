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
import com.xiaoyu.lingdian.entity.CoreClass;
import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.service.CoreClassService;
import com.xiaoyu.lingdian.service.CoreGradeService;
import com.xiaoyu.lingdian.vo.CoreClassVO;

@Controller
@RequestMapping(value="/coreClass")
public class CoreClassController extends BaseController {

	/**
	* 年级表
	*/
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreGradeService coreGradeService;
	
	/**
	* 添加
	*
	* @param crcasName 年级名称
	* @return
	*/
	@RequestMapping(value="/add/coreClass", method=RequestMethod.POST)
	public void addCoreClass (String crcasName, HttpServletResponse response) {
		logger.info("[CoreClassController]:begin addCoreClass");
		CoreClass coreClass = new CoreClass();
		String uuid = RandomUtil.generateString(16);
		coreClass.setCrcasUuid(uuid);
		coreClass.setCrcasName(crcasName);

		coreClassService.insertCoreClass(coreClass);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreClassController]:end addCoreClass");

	}

	/**
	* 修改
	*
	* @param crcasUuid 标识UUID
	* @param crcasName 年级名称
	* @return
	*/
	@RequestMapping(value="/update/coreClass", method=RequestMethod.POST)
	public void updateCoreClass (String crcasUuid, String crcasName, HttpServletResponse response) {
		logger.info("[CoreClassController]:begin updateCoreClass");
		if (StringUtil.isEmpty(crcasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreClass coreClass = new CoreClass();
		coreClass.setCrcasUuid(crcasUuid);
		coreClass.setCrcasName(crcasName);

		coreClassService.updateCoreClass(coreClass);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreClassController]:end updateCoreClass");

	}

	/**
	* 删除
	*
	* @param crcasUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreClass (String crcasUuid, HttpServletResponse response) {
		logger.info("[CoreClassController]:begin deleteCoreClass");
		if (StringUtil.isEmpty(crcasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
//		if (("7").equals(crcasUuid)) {
//			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "无为默认，不能删除！"), response);
//			return;
//		}		CoreClass coreClass = new CoreClass();
		coreClass.setCrcasUuid(crcasUuid);

		coreClassService.deleteCoreClass(coreClass);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreClassController]:end deleteCoreClass");

	}

	/**
	* 批量删除
	*
	* @param crcasUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreClass (String crcasUuids, HttpServletResponse response) {
		logger.info("[CoreClassController]:begin deleteBatchCoreClass");
		if (StringUtil.isEmpty(crcasUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crcasUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
//			if (("7").equals(uuids[i])) {
//				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "无为默认，不能删除！"), response);
//				return;
//			}
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreClassService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreClassController]:end deleteBatchCoreClass");

	}

	/**
	* 获取单个
	*
	* @param crcasUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreClass (String crcasUuid, HttpServletResponse response) {
		logger.info("[CoreClassController]:begin viewsCoreClass");
		if (StringUtil.isEmpty(crcasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreClass coreClass = new CoreClass();
		coreClass.setCrcasUuid(crcasUuid);

		coreClass = coreClassService.getCoreClass(coreClass);

		CoreClassVO coreClassVO = new CoreClassVO();
		coreClassVO.convertPOToVO(coreClass);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreClassVO), response);
		logger.info("[CoreClassController]:end viewsCoreClass");

	}

	/**
	* 获取列表,包括无<List>(后台题目和生成题目中使用)
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreClassList (HttpServletResponse response) {
		logger.info("[CoreClassController]:begin findCoreClassList");
		List<CoreClass> lists = coreClassService.findCoreClassList();
		List<CoreClassVO> vos = new ArrayList<CoreClassVO>();
		CoreClassVO vo;
		for (CoreClass coreClass : lists) {
			vo = new CoreClassVO();
			vo.convertPOToVO(coreClass);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreClassController]:end findCoreClassList");

	}

	/**
	* 前端获取班级的年级权限列表<List>,班级里的年级权限不存在无
	* @param crgaeUuid
	* 
	* @return
	*/
	@RequestMapping(value="/find/all/nothing/grade", method=RequestMethod.POST)
	public void findCoreClassForListsNothingByGrade (String crgaeUuid, HttpServletResponse response) {
		logger.info("[CoreClassController]:begin findCoreClassForListsNothingByGrade");
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgaeUuid(crgaeUuid);
		coreGrade = coreGradeService.getCoreGrade(coreGrade);
		if (coreGrade == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该用户所在班级不存在"), response);
			return;
		}
		String crusrClasss = coreGrade.getCrgaeClasss();
		String[] classs=crusrClasss.split(",");
		List<CoreClassVO> vos = new ArrayList<CoreClassVO>();
		CoreClassVO vo;
		CoreClass coreClass;
		for (int i = 0; i < classs.length; i++) {
			coreClass = new CoreClass();
			coreClass.setCrcasUuid(classs[i]);
			coreClass = coreClassService.getCoreClass(coreClass);
			if (coreClass != null) {
				vo = new CoreClassVO();
				vo.convertPOToVO(coreClass);
				vos.add(vo);
			}
		}		

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreClassController]:end findCoreClassForListsNothingByGrade");

	}
	
	/**
	* 获取列表<List>,不存在无，后台一般下拉框中用
	* 
	* @return
	*/
	@RequestMapping(value="/find/all/nothing", method=RequestMethod.POST)
	public void findCoreClassForListsNothing (HttpServletResponse response) {
		logger.info("[CoreClassController]:begin findCoreClassForListsNothing");

		List<CoreClass> lists = coreClassService.findCoreClassForListsNothing();
		List<CoreClassVO> vos = new ArrayList<CoreClassVO>();
		CoreClassVO vo;
		for (CoreClass coreClass : lists) {
			vo = new CoreClassVO();

			vo.convertPOToVO(coreClass);

			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreClassController]:end findCoreClassForListsNothing");

	}
	
	/**
	* 获取列表<Page>，不包括无，后台列表中用
	* 
	* @param crcasName 年级名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreClassPage (String crcasName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreClassController]:begin findCoreClassPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreClass> page = coreClassService.findCoreClassPage(crcasName ,pageNum, pageSize);
		Page<CoreClassVO> pageVO = new Page<CoreClassVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreClassVO> vos = new ArrayList<CoreClassVO>();
		CoreClassVO vo;
		for (CoreClass coreClass : page.getResult()) {
			vo = new CoreClassVO();
			vo.convertPOToVO(coreClass);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreClassController]:end findCoreClassPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}