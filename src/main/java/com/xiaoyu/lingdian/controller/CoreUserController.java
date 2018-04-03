package com.xiaoyu.lingdian.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.encrypt.MD5Util;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
//import com.xiaoyu.lingdian.entity.CoreAttachment;
import com.xiaoyu.lingdian.entity.CoreUser;
//import com.xiaoyu.lingdian.service.CoreAttachmentService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.vo.CoreUserVO;

@Controller
@RequestMapping(value="/coreUser")
public class CoreUserController extends BaseController {

	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	 * 修改密码
	 * 
	 * @param crusrUuid
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @param response
	 */
	@RequestMapping(value = "/update/pwd", method = RequestMethod.POST)
	public void updatePwd(String crusrUuid, String oldPwd, String newPwd, String confirmPwd, HttpServletResponse response) {
		logger.info("[CoreUserController.updatePwd]:begin updatePwd.");
		if (StringUtils.isBlank(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入uuid！"), response);
			return;
		}		
		if (StringUtils.isBlank(oldPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入旧密码！"), response);
			return;
		}		
		if (StringUtils.isBlank(newPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入新密码！"), response);
			return;
		}		
		if (StringUtils.isBlank(confirmPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入确认密码！"), response);
			return;
		}		
		if (!newPwd.equals(confirmPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码输入不一致！"), response);
			return;
		}		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser = coreUserService.getCoreUser(coreUser);
		if(coreUser == null || !oldPwd.equals(coreUser.getCrusrPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码错误！"), response);
			return;
		}
		coreUser.setCrusrPassword(newPwd);
		coreUser.setCrusrUdate(new Date());
		coreUserService.updateCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改密码成功！"), response);
		logger.info("[CoreUserController.updatePwd]:end updatePwd.");
	}
	
	/**
	 * 后台重置密码
	 * 
	 * @param crusrUuid
	 * @param password
	 * @param response
	 */
	@RequestMapping(value = "/reset/pwd", method = RequestMethod.POST)
	public void resetPwd(String crusrUuid, String password, HttpServletResponse response) {
		logger.info("[CoreUserController.resetPwd]:begin resetPwd.");
		if (StringUtils.isBlank(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入uuid！"), response);
			return;
		}		
		if (StringUtils.isBlank(password)) {
			password = "e10adc3949ba59abbe56e057f20f883e";
		}
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrPassword(password);
		coreUser.setCrusrUdate(new Date());
		coreUserService.updateCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "重置密码成功！"), response);
		logger.info("[CoreUserController.resetPwd]:end resetPwd.");
	}
	
	/**
	 * 注册
	 * 
	 * @param crusrName
	 * @param crusrPassword
	 * @param response
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public void register(String crusrName, String crusrPassword, HttpServletResponse response) {
		logger.info("[CoreUserController.register]:begin register");
		
		if (StringUtils.isBlank(crusrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入账户名称！"), response);
			return;
		}
		if (StringUtils.isBlank(crusrPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			return;
		}
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrName(crusrName);	
		if(coreUserService.getCoreUserByName(coreUser) != null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该账户名称已存在，请重新输入！"), response);
			return;
		}
		coreUser.setCrusrName(crusrName);		
		coreUser.setCrusrStatus(1);
		coreUser.setCrusrCdate(new Date());
		String uuid = RandomUtil.generateString(16);
		coreUser.setCrusrUuid(uuid);
		coreUser.setCrusrPassword(crusrPassword);
		coreUser.setCrusrGrade("1"); //默认普通班
		coreUserService.insertCoreUser(coreUser);		
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "注册成功！",uuid), response);
		logger.info("[CoreUserController.register]:end register");
	}
	
	/**
	 * 登录
	 * 
	 * @param crusrName
	 * @param crusrPassword
	 * @param response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(String crusrName, String crusrPassword, HttpServletResponse response) {
		logger.info("[CoreUserController.login]:begin login.");
		if (StringUtils.isBlank(crusrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入帐户名称！"), response);
			return;
		}		
		if (StringUtils.isBlank(crusrPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			return;
		}
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrName(crusrName);
		coreUser = coreUserService.getCoreUserByName(coreUser);
		if(coreUser == null || !crusrPassword.equals(coreUser.getCrusrPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码错误！"), response);
			return;
		}
		CoreUserVO vo = new CoreUserVO();
		vo.convertPOToVO(coreUser);
		//查询头像附件UUID
//		List<CoreAttachment> list = coreAttachmentService.findCoreAttachmentByCnd(coreUser.getCrusrUuid());
//		if (list != null && list.size() > 0) {
//			vo.setHeadUuid(list.get(0).getCratmUuid());
//		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "登录成功", vo), response);
		logger.info("[CoreUserController.login]:end login.");
	}
	
	/**
	* 添加
	*
	* @param crusrName 帐户名称
	* @param crusrCode 真实姓名
	* @param crusrMobile 手机号码
	* @param crusrStatus 状态:1启用,0禁用
	* @param crusrBirthday 生日
	* @param crusrGender 性别:1男,0女,2其它
	* @param crusrQq QQ
	* @param crusrRemarks 备注
	* @param crusrCrade 所属班级
	* @param crusrSchool 就读学校
	* @param crusrReadClass 在读年级(1一年级2二年级3三年级4四年级5五年级6六年级7其他)
	* @return
	*/
	@RequestMapping(value="/add/coreUser", method=RequestMethod.POST)
	public void addCoreUser (String crusrName, String crusrCode, String crusrMobile, Integer crusrStatus, String crusrBirthday, Integer crusrGender, String crusrQq, String crusrRemarks, String crusrCrade, String crusrSchool, Integer crusrReadClass, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin addCoreUser");

		if (StringUtil.isEmpty(crusrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[帐户名称]不能为空!"), response);
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrName(crusrName);	
		if(coreUserService.getCoreUserByName(coreUser) != null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该账户名称已存在，请重新输入！"), response);
			return;
		}
		coreUser.setCrusrName(crusrName);
		String uuid = RandomUtil.generateString(16);
		coreUser.setCrusrUuid(uuid);
		coreUser.setCrusrCode(crusrCode);
		String md5PWD = MD5Util.getMD5("123456");
		coreUser.setCrusrPassword(md5PWD);
		coreUser.setCrusrMobile(crusrMobile);
		coreUser.setCrusrType(1);
		coreUser.setCrusrStatus(crusrStatus);
		coreUser.setCrusrCdate(new Date());
		coreUser.setCrusrUdate(new Date());
		coreUser.setCrusrBirthday(crusrBirthday);
		coreUser.setCrusrGender(crusrGender);
		coreUser.setCrusrQq(crusrQq);
		coreUser.setCrusrRemarks(crusrRemarks);
		coreUser.setCrusrGrade(crusrCrade);
		coreUser.setCrusrSchool(crusrSchool);
		coreUser.setCrusrReadClass(crusrReadClass);

		coreUserService.insertCoreUser(coreUser);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreUserController]:end addCoreUser");

	}

	/**
	* 修改
	*
	* @param crusrUuid 标识UUID
	* @param crusrCode 真实姓名
	* @param crusrMobile 手机号码
	* @param crusrStatus 状态:1启用,0禁用
	* @param crusrBirthday 生日
	* @param crusrGender 性别:1男,0女,2其它
	* @param crusrQq QQ
	* @param crusrRemarks 备注
	* @param crusrGrade 所属班级
	* @param crusrSchool 就读学校
	* @param crusrReadClass 在读年级(1一年级2二年级3三年级4四年级5五年级6六年级7其他)
	* @return
	*/
	@RequestMapping(value="/update/coreUser", method=RequestMethod.POST)
	public void updateCoreUser (String crusrUuid, String crusrCode, String crusrMobile, Integer crusrStatus, String crusrBirthday, Integer crusrGender, String crusrQq, String crusrRemarks, String crusrGrade, String crusrSchool, Integer crusrReadClass, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin updateCoreUser");

		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrCode(crusrCode);
		coreUser.setCrusrMobile(crusrMobile);
		coreUser.setCrusrStatus(crusrStatus);
		coreUser.setCrusrUdate(new Date());
		coreUser.setCrusrBirthday(crusrBirthday);
		coreUser.setCrusrGender(crusrGender);
		coreUser.setCrusrQq(crusrQq);
		coreUser.setCrusrRemarks(crusrRemarks);
		coreUser.setCrusrGrade(crusrGrade);
		coreUser.setCrusrSchool(crusrSchool);
		coreUser.setCrusrReadClass(crusrReadClass);

		coreUserService.updateCoreUser(coreUser);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreUserController]:end updateCoreUser");

	}

	/**
	* 删除
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreUser (String crusrUuid, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin deleteCoreUser");

		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);

		coreUserService.deleteCoreUser(coreUser);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreUserController]:end deleteCoreUser");

	}

	/**
	* 批量删除
	*
	* @param crusrUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreUser (String crusrUuids, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin deleteBatchCoreUser");

		if (StringUtil.isEmpty(crusrUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}

		String[] uuids=crusrUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreUserService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreUserController]:end deleteBatchCoreUser");

	}

	/**
	* 获取单个
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreUser (String crusrUuid, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin viewsCoreUser");

		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);

		coreUser = coreUserService.getCoreUser(coreUser);

		CoreUserVO coreUserVO = new CoreUserVO();
		coreUserVO.convertPOToVO(coreUser);
		
		//查询头像附件UUID
		//List<CoreAttachment> list = coreAttachmentService.findCoreAttachmentByCnd(coreUser.getCrusrUuid());
		//if (list != null && list.size() > 0) {
		//	coreUserVO.setHeadUuid(list.get(0).getCratmUuid());
		//}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreUserVO), response);
		logger.info("[CoreUserController]:end viewsCoreUser");

	}

	/**
	* 获取列表<List>
	* @param crusrGrade
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreUserList (String crusrGrade, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin findCoreUserList");

		List<CoreUser> lists = coreUserService.findCoreUserList(crusrGrade);
		List<CoreUserVO> vos = new ArrayList<CoreUserVO>();
		CoreUserVO vo;
		for (CoreUser coreUser : lists) {
			vo = new CoreUserVO();
			vo.convertPOToVO(coreUser);
			//查询头像附件UUID
			//List<CoreAttachment> list = coreAttachmentService.findCoreAttachmentByCnd(coreUser.getCrusrUuid());
			//if (list != null && list.size() > 0) {
			//	vo.setHeadUuid(list.get(0).getCratmUuid());
			//}
			
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreUserController]:end findCoreUserList");

	}

	/**
	* 获取列表<Page>
	* @param crusrName 账户名称
	* @param crusrGrade 所属班级
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreUserPage (String crusrName, String crusrGrade, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin findCoreUserPage");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreUser> page = coreUserService.findCoreUserPage(crusrName, crusrGrade, pageNum, pageSize);
		Page<CoreUserVO> pageVO = new Page<CoreUserVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreUserVO> vos = new ArrayList<CoreUserVO>();
		CoreUserVO vo;
		for (CoreUser coreUser : page.getResult()) {
			vo = new CoreUserVO();
			vo.convertPOToVO(coreUser);
			//查询头像附件UUID
			//List<CoreAttachment> list = coreAttachmentService.findCoreAttachmentByCnd(coreUser.getCrusrUuid());
			//if (list != null && list.size() > 0) {
			//	vo.setHeadUuid(list.get(0).getCratmUuid());
			//}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreUserController]:end findCoreUserPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}