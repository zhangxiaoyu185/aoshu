package com.xiaoyu.lingdian.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
import com.xiaoyu.lingdian.vo.CoreGradeVO;

@Controller
@RequestMapping(value="/coreGrade")
public class CoreGradeController extends BaseController {

	/**
	* 班级表
	*/
	@Autowired
	private CoreGradeService coreGradeService;
	
	/**
	 * 年级表
	 */
	@Autowired
	private CoreClassService coreClassService;
	
	/**
	* 添加
	*
	* @param crgaeName 班级名
	* @param crgaeClasss 开通年级UUID集合，逗号隔开
	* @return
	*/
	@RequestMapping(value="/add/coreGrade", method=RequestMethod.POST)
	public void addCoreGrade (String crgaeName, String crgaeClasss, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin addCoreGrade");
		CoreGrade coreGrade = new CoreGrade();
		String uuid = RandomUtil.generateString(16);
		coreGrade.setCrgaeUuid(uuid);
		coreGrade.setCrgaeName(crgaeName);
		coreGrade.setCrgaeClasss(crgaeClasss);

		coreGradeService.insertCoreGrade(coreGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreGradeController]:end addCoreGrade");

	}

	/**
	* 修改班级
	*
	* @param crgaeUuid 标识UUID
	* @param crgaeName 班级名
	* @param crgaeClasss 开通年级UUID集合，逗号隔开
	* @return
	*/
	@RequestMapping(value="/update/coreGrade", method=RequestMethod.POST)
	public void updateCoreGrade (String crgaeUuid, String crgaeName, String crgaeClasss, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin updateCoreGrade");
		if (StringUtil.isEmpty(crgaeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgaeUuid(crgaeUuid);
		coreGrade.setCrgaeName(crgaeName);
		coreGrade.setCrgaeClasss(crgaeClasss);

		coreGradeService.updateCoreGrade(coreGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreGradeController]:end updateCoreGrade");

	}
	
	/**
	* 删除
	*
	* @param crgaeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreGrade (String crgaeUuid, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin deleteCoreGrade");
		if (StringUtil.isEmpty(crgaeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgaeUuid(crgaeUuid);

		coreGradeService.deleteCoreGrade(coreGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreGradeController]:end deleteCoreGrade");

	}

	/**
	* 批量删除
	*
	* @param crgaeUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreGrade (String crgaeUuids, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin deleteBatchCoreGrade");
		if (StringUtil.isEmpty(crgaeUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crgaeUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreGradeService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreGradeController]:end deleteBatchCoreGrade");

	}	
	
	/**
	* 获取单个
	*
	* @param crgaeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreGrade (String crgaeUuid, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin viewsCoreGrade");
		if (StringUtil.isEmpty(crgaeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgaeUuid(crgaeUuid);

		coreGrade = coreGradeService.getCoreGrade(coreGrade);

		CoreGradeVO coreGradeVO = new CoreGradeVO();
		coreGradeVO.convertPOToVO(coreGrade);
		String classStr=coreGrade.getCrgaeClasss();
		if (!StringUtil.isEmpty(classStr)) {
			String [] classs = classStr.split(",");
			List<String> ids = new ArrayList<String>();
			Collections.addAll(ids, classs);
			List<CoreClass> coreClassList = coreClassService.findCoreClassByIds(ids);
			if(coreClassList.size() > 0){
				Map<String, CoreClass> map = new HashMap<String, CoreClass>();
				StringBuffer className = new StringBuffer();
				for (CoreClass coreClass : coreClassList) {
					map.put(coreClass.getCrcasUuid(), coreClass);					
				}
				for (int i = 0; i < ids.size(); i++) {
					className.append(map.get(ids.get(i)).getCrcasName()).append(",");
				}
				coreGradeVO.setCrgaeClasssName(className.toString());
			}else{
				coreGradeVO.setCrgaeClasssName("");
			}
		}		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreGradeVO), response);
		logger.info("[CoreGradeController]:end viewsCoreGrade");

	}

	/**
	* 获取列表,包括权限名称<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreGradeList (HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin findCoreGradeList");
		List<CoreGrade> lists = coreGradeService.findCoreGradeList();
		List<CoreGradeVO> vos = new ArrayList<CoreGradeVO>();
		CoreGradeVO vo;
		for (CoreGrade coreGrade : lists) {
			vo = new CoreGradeVO();
			vo.convertPOToVO(coreGrade);
			String classStr=coreGrade.getCrgaeClasss();
			if (!StringUtil.isEmpty(classStr)) {
				String [] classs=classStr.split(",");
				List<String> ids=new ArrayList<String>();
				Collections.addAll(ids, classs);
				List<CoreClass> coreClassList=coreClassService.findCoreClassByIds(ids);
				if(coreClassList.size()>0){
					Map<String, CoreClass> map = new HashMap<String, CoreClass>();
					StringBuffer className = new StringBuffer();
					for (CoreClass coreClass : coreClassList) {
						map.put(coreClass.getCrcasUuid(), coreClass);					
					}
					for (int i = 0; i < ids.size(); i++) {
						className.append(map.get(ids.get(i)).getCrcasName()).append(",");
					}
					vo.setCrgaeClasssName(className.toString());
				}else{
					vo.setCrgaeClasssName("");
				}
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreGradeController]:end findCoreGradeList");

	}

	/**
	* 获取列表,不包括权限名称<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/select", method=RequestMethod.POST)
	public void findCoreGradeSelect (HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin findCoreGradeSelect");

		List<CoreGrade> lists = coreGradeService.findCoreGradeList();
		List<CoreGradeVO> vos = new ArrayList<CoreGradeVO>();
		CoreGradeVO vo;
		for (CoreGrade coreGrade : lists) {
			vo = new CoreGradeVO();
			vo.convertPOToVO(coreGrade);
			vos.add(vo);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreGradeController]:end findCoreGradeSelect");

	}
	
	/**
	* 获取列表<Page>,1普通班除外
	* 
	* @param crgaeName 班级名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreGradePage (String crgaeName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin findCoreGradePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreGrade> page = coreGradeService.findCoreGradePage(crgaeName, pageNum, pageSize);
		Page<CoreGradeVO> pageVO = new Page<CoreGradeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreGradeVO> vos = new ArrayList<CoreGradeVO>();
		CoreGradeVO vo;
		for (CoreGrade coreGrade : page.getResult()) {
			if (("1").equals(coreGrade.getCrgaeUuid())) {
				continue;
			}
			vo = new CoreGradeVO();
			vo.convertPOToVO(coreGrade);
			String classStr=coreGrade.getCrgaeClasss();
			if (!StringUtil.isEmpty(classStr)) {
				String [] classs=classStr.split(",");
				List<String> ids=new ArrayList<String>();
				Collections.addAll(ids, classs);
				List<CoreClass> coreClassList=coreClassService.findCoreClassByIds(ids);
				if(coreClassList.size()>0){
					Map<String, CoreClass> map = new HashMap<String, CoreClass>();
					StringBuffer className = new StringBuffer();
					for (CoreClass coreClass : coreClassList) {
						map.put(coreClass.getCrcasUuid(), coreClass);					
					}
					for (int i = 0; i < ids.size(); i++) {
						className.append(map.get(ids.get(i)).getCrcasName()).append(",");
					}
					vo.setCrgaeClasssName(className.toString());
				}else{
					vo.setCrgaeClasssName("");
				}
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreGradeController]:end findCoreGradePage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}