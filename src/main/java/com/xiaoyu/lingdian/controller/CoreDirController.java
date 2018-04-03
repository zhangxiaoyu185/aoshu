package com.xiaoyu.lingdian.controller;

import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.FileUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.init.ConfigIni;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreDir;
import com.xiaoyu.lingdian.service.CoreDirService;
import com.xiaoyu.lingdian.vo.CoreDirVO;

@Controller
@RequestMapping(value="/coreDir")
public class CoreDirController extends BaseController {

	/**
	* 图片目录表
	*/
	@Autowired
	private CoreDirService coreDirService;
	
	/**
	* 添加
	*
	* @param crdirName 目录名称
	* @return
	*/
	@RequestMapping(value="/add/coreDir", method=RequestMethod.POST)
	public void addCoreDir (String crdirName, HttpServletResponse response) {
		logger.info("[CoreDirController]:begin addCoreDir");

		if (StringUtil.isEmpty(crdirName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[目录名称]不能为空!"), response);
			return;
		}
		
		List<CoreDir> list = coreDirService.getCoreDirByName(crdirName);
		if (null != list && list.size() > 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该目录已存在!"), response);
			return;
		}
				CoreDir coreDir = new CoreDir();
		String uuid = RandomUtil.generateString(16);
		coreDir.setCrdirUuid(uuid);
		coreDir.setCrdirName(crdirName);

		coreDirService.insertCoreDir(coreDir);

		//创建文件夹
		String questionPath = ConfigIni.getIniStrValue("ATTACHMENT_DIR", "path") + "question/" + crdirName;
		if (!FileUtil.isExist(questionPath)) {
			FileUtil.createFolder(questionPath);
		}
		String analysisPath = ConfigIni.getIniStrValue("ATTACHMENT_DIR", "path") + "analysis/" + crdirName;
		if (!FileUtil.isExist(analysisPath)) {
			FileUtil.createFolder(analysisPath);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreDirController]:end addCoreDir");

	}

	/**
	* 修改
	*
	* @param crdirUuid 标识UUID
	* @param crdirName 目录名称
	* @return
	*/
	@RequestMapping(value="/update/coreDir", method=RequestMethod.POST)
	public void updateCoreDir (String crdirUuid, String crdirName, HttpServletResponse response) {
		logger.info("[CoreDirController]:begin updateCoreDir");
		if (StringUtil.isEmpty(crdirUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreDir coreDir = new CoreDir();
		coreDir.setCrdirUuid(crdirUuid);
		coreDir.setCrdirName(crdirName);

		coreDirService.updateCoreDir(coreDir);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreDirController]:end updateCoreDir");

	}

	/**
	* 删除
	*
	* @param crdirUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreDir (String crdirUuid, HttpServletResponse response) {
		logger.info("[CoreDirController]:begin deleteCoreDir");
		if (StringUtil.isEmpty(crdirUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreDir coreDir = new CoreDir();
		coreDir.setCrdirUuid(crdirUuid);

		coreDirService.deleteCoreDir(coreDir);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreDirController]:end deleteCoreDir");

	}

	/**
	* 批量删除
	*
	* @param crdirUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreDir (String crdirUuids, HttpServletResponse response) {
		logger.info("[CoreDirController]:begin deleteBatchCoreDir");
		if (StringUtil.isEmpty(crdirUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crdirUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreDirService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreDirController]:end deleteBatchCoreDir");

	}

	/**
	* 获取单个
	*
	* @param crdirUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreDir (String crdirUuid, HttpServletResponse response) {
		logger.info("[CoreDirController]:begin viewsCoreDir");
		if (StringUtil.isEmpty(crdirUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreDir coreDir = new CoreDir();
		coreDir.setCrdirUuid(crdirUuid);

		coreDir = coreDirService.getCoreDir(coreDir);

		CoreDirVO coreDirVO = new CoreDirVO();
		coreDirVO.convertPOToVO(coreDir);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreDirVO), response);
		logger.info("[CoreDirController]:end viewsCoreDir");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreDirList (HttpServletResponse response) {
		logger.info("[CoreDirController]:begin findCoreDirList");
		List<CoreDir> lists = coreDirService.findCoreDirList();
		List<CoreDirVO> vos = new ArrayList<CoreDirVO>();
		CoreDirVO vo;
		for (CoreDir coreDir : lists) {
			vo = new CoreDirVO();
			vo.convertPOToVO(coreDir);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreDirController]:end findCoreDirList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crdirName 目录名
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreDirPage (String crdirName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreDirController]:begin findCoreDirPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreDir> page = coreDirService.findCoreDirPage(crdirName, pageNum, pageSize);
		Page<CoreDirVO> pageVO = new Page<CoreDirVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreDirVO> vos = new ArrayList<CoreDirVO>();
		CoreDirVO vo;
		for (CoreDir coreDir : page.getResult()) {
			vo = new CoreDirVO();
			vo.convertPOToVO(coreDir);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreDirController]:end findCoreDirPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}