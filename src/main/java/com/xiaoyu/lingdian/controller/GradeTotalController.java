package com.xiaoyu.lingdian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreGradeCourse;
import com.xiaoyu.lingdian.entity.CoreGradePapers;
import com.xiaoyu.lingdian.entity.CorePapersAnswer;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.entity.CoreWork;
import com.xiaoyu.lingdian.entity.CoreWorkAnswer;
import com.xiaoyu.lingdian.service.CoreGradeCourseService;
import com.xiaoyu.lingdian.service.CoreGradePapersService;
import com.xiaoyu.lingdian.service.CorePapersAnswerService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.CoreWorkAnswerService;
import com.xiaoyu.lingdian.service.CoreWorkService;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.vo.GradeTotal;

@Controller
@RequestMapping(value="/gradeTotal")
public class GradeTotalController extends BaseController {

	public Integer MAX_SCORE = 100;
	
	/**
	* 班级课程表
	*/
	@Autowired
	private CoreGradeCourseService coreGradeCourseService;
	
	/**
	 * 班级模拟试卷表
	 */
	@Autowired
	private CoreGradePapersService coreGradePapersService;
	
	/**
	* 试卷回答表
	*/
	@Autowired
	private CorePapersAnswerService corePapersAnswerService;

	/**
	* 训练回答表
	*/
	@Autowired
	public CoreWorkAnswerService coreWorkAnswerService;
	
	/**
	* 强化训练表
	*/
	@Autowired
	public CoreWorkService coreWorkService;
	
	/**
	* 用户表
	*/
	@Autowired
	public CoreUserService coreUserService;
	
	/**
	* 试卷学生
	* 
	* @return
	*/
	@RequestMapping(value="/papers/user", method=RequestMethod.POST)
	public void papersUser (String strGrade, String strClass, HttpServletResponse response) {
		logger.info("[GradeTotalController]:begin papersUser");
		List<GradeTotal> totalList = new ArrayList<GradeTotal>();
		//1.查询班级下的所有学生集合，userList->userIdList
		List<String> userIdList = new ArrayList<String>();		List<CoreUser> userList = coreUserService.findCoreUserList(strGrade);
		for (CoreUser coreUser : userList) {
			userIdList.add(coreUser.getCrusrUuid());
		}
		//2.查询班级下的所有模拟试卷集合，papersList->papersIdList
		List<String> papersIdList = new ArrayList<String>();
		List<CoreGradePapers> papersList = coreGradePapersService.findCoreGradePapersList(strGrade, strClass);
		for (CoreGradePapers coreGradePapers : papersList) {
			papersIdList.add(coreGradePapers.getCrpesUuid());
		}
		
		if (userIdList.size() <= 0 || papersIdList.size() <= 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "该班级年级下没有学生或没有试卷!", totalList), response);
			logger.info("[GradeTotalController]:end papersUser");
		}
		//3.根据papersIdList和userIdList查询试卷answer集合,answerList->Map<key->userUuid+";"+papersUuid>
		Map<String, CorePapersAnswer> map = new HashMap<String, CorePapersAnswer>();
		List<CorePapersAnswer> answerList = corePapersAnswerService.findCorePapersAnswerForListsByFilters(papersIdList, userIdList);
		for (CorePapersAnswer corePapersAnswer : answerList) {
			map.put(corePapersAnswer.getCrpsaUser()+";"+corePapersAnswer.getCrpsaPapersUuid(), corePapersAnswer);
		}
		//试卷学生统计，包装对象
		GradeTotal gradeTotal = null;
		for (CoreGradePapers coreGradePapers : papersList) {
			gradeTotal = new GradeTotal();
			int hwzKey = 0; //行未做Key
			StringBuffer hwzValue = new StringBuffer(); //行未做Value
			int hyzwmfKey = 0; //行已做未满分Key
			StringBuffer hyzwmfValue = new StringBuffer(); //行已做未满分Value
			int hyzmfKey = 0; //行已做满分Key
			StringBuffer hyzmfValue = new StringBuffer();; //行已做满分Value
			gradeTotal.setColumnObj(coreGradePapers.getCrpesName());
			for (CoreUser coreUser : userList) {
				CorePapersAnswer corePapersAnswer = map.get(coreUser.getCrusrUuid() + ";" + coreGradePapers.getCrpesUuid());
				if (corePapersAnswer != null) { //已做
					if (corePapersAnswer.getCrpsaScore().equals(MAX_SCORE)) { //满分
						hyzmfKey++;
						String code = coreUser.getCrusrCode();
						if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
							code = coreUser.getCrusrName();
						}
						hyzmfValue.append(code).append(",");
					} else { //未满分
						hyzwmfKey++;
						String code = coreUser.getCrusrCode();
						if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
							code = coreUser.getCrusrName();
						}
						hyzwmfValue.append(code).append(",");
					}					
				} else { //未做
					hwzKey++;
					String code = coreUser.getCrusrCode();
					if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
						code = coreUser.getCrusrName();
					}
					hwzValue.append(code).append(",");
				}
			}
			gradeTotal.setHwzKey(String.valueOf(hwzKey));
			gradeTotal.setHwzValue(hwzValue.toString());
			gradeTotal.setHyzmfKey(String.valueOf(hyzmfKey));
			gradeTotal.setHyzmfValue(hyzmfValue.toString());
			gradeTotal.setHyzwmfKey(String.valueOf(hyzwmfKey));
			gradeTotal.setHyzwmfValue(hyzwmfValue.toString());
			totalList.add(gradeTotal);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "统计获取成功!", totalList),response);
		logger.info("[GradeTotalController]:end papersUser");

	}

	/**
	* 学生试卷
	* 
	* @return
	*/
	@RequestMapping(value="/user/papers", method=RequestMethod.POST)
	public void userPapers (String strGrade, String strClass, HttpServletResponse response) {
		logger.info("[GradeTotalController]:begin userPapers");
		List<GradeTotal> totalList = new ArrayList<GradeTotal>();
		//1.查询班级下的所有学生集合，userList->userIdList
		List<String> userIdList = new ArrayList<String>();
		List<CoreUser> userList = coreUserService.findCoreUserList(strGrade);
		for (CoreUser coreUser : userList) {
			userIdList.add(coreUser.getCrusrUuid());
		}
		//2.查询班级下的所有模拟试卷集合，papersList->papersIdList
		List<String> papersIdList = new ArrayList<String>();
		List<CoreGradePapers> papersList = coreGradePapersService.findCoreGradePapersList(strGrade, strClass);
		for (CoreGradePapers coreGradePapers : papersList) {
			papersIdList.add(coreGradePapers.getCrpesUuid());
		}
		
		if (userIdList.size() <= 0 || papersIdList.size() <= 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "该班级年级下没有学生或没有试卷!", totalList), response);
			logger.info("[GradeTotalController]:end userPapers");
		}
		//3.根据papersIdList和userIdList查询试卷answer集合,answerList->Map<key->userUuid+";"+papersUuid>
		Map<String, CorePapersAnswer> map = new HashMap<String, CorePapersAnswer>();
		List<CorePapersAnswer> answerList = corePapersAnswerService.findCorePapersAnswerForListsByFilters(papersIdList, userIdList);
		for (CorePapersAnswer corePapersAnswer : answerList) {
			map.put(corePapersAnswer.getCrpsaUser()+";"+corePapersAnswer.getCrpsaPapersUuid(), corePapersAnswer);
		}
		//学生试卷统计，包装对象
		GradeTotal gradeTotal = null;
		for (CoreUser coreUser : userList) {		
			gradeTotal = new GradeTotal();
			int hwzKey = 0; //行未做Key
			StringBuffer hwzValue = new StringBuffer(); //行未做Value
			int hyzwmfKey = 0; //行已做未满分Key
			StringBuffer hyzwmfValue = new StringBuffer(); //行已做未满分Value
			int hyzmfKey = 0; //行已做满分Key
			StringBuffer hyzmfValue = new StringBuffer();; //行已做满分Value
			String code = coreUser.getCrusrCode();
			if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
				code = coreUser.getCrusrName();
			}
			gradeTotal.setColumnObj(code);
			for (CoreGradePapers coreGradePapers : papersList) {
				CorePapersAnswer corePapersAnswer = map.get(coreUser.getCrusrUuid() + ";" + coreGradePapers.getCrpesUuid());
				if (corePapersAnswer != null) { //已做
					if (corePapersAnswer.getCrpsaScore().equals(MAX_SCORE)) { //满分
						hyzmfKey++;
						hyzmfValue.append(coreGradePapers.getCrpesName()).append(",");
					} else { //未满分
						hyzwmfKey++;
						hyzwmfValue.append(coreGradePapers.getCrpesName()).append(",");
					}					
				} else { //未做
					hwzKey++;
					hwzValue.append(coreGradePapers.getCrpesName()).append(",");
				}
			}
			gradeTotal.setHwzKey(String.valueOf(hwzKey));
			gradeTotal.setHwzValue(hwzValue.toString());
			gradeTotal.setHyzmfKey(String.valueOf(hyzmfKey));
			gradeTotal.setHyzmfValue(hyzmfValue.toString());
			gradeTotal.setHyzwmfKey(String.valueOf(hyzwmfKey));
			gradeTotal.setHyzwmfValue(hyzwmfValue.toString());
			totalList.add(gradeTotal);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "统计获取成功!", totalList),response);
		logger.info("[GradeTotalController]:end userPapers");

	}

	/**
	* 学生课程
	* 
	* @return
	*/
	@RequestMapping(value="/user/course", method=RequestMethod.POST)
	public void userCourse (String strGrade, String strClass, HttpServletResponse response) {
		logger.info("[GradeTotalController]:begin userCourse");
		List<GradeTotal> totalList = new ArrayList<GradeTotal>();
		//1.查询班级下的所有学生集合，userList->userIdList
		List<String> userIdList = new ArrayList<String>();
		List<CoreUser> userList = coreUserService.findCoreUserList(strGrade);
		for (CoreUser coreUser : userList) {
			userIdList.add(coreUser.getCrusrUuid());
		}
		//2.查询班级下的所有课程集合，courseList->courseIdList
		List<String> courseIdList = new ArrayList<String>();
		List<CoreGradeCourse> courseList = coreGradeCourseService.findCoreGradeCourseList(strGrade, strClass);
		for (CoreGradeCourse coreGradeCourse : courseList) {
			courseIdList.add(coreGradeCourse.getCrcreUuid());
		}
		if (courseIdList.size() <= 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "该班级年级下没有课程!", totalList), response);
			logger.info("[GradeTotalController]:end userCourse");
		}
		//3.根据courseIdList查询训练workList集合->workIdList
		List<String> workIdList = new ArrayList<String>();
		List<CoreWork> workList = coreWorkService.findCoreWorkForListByFilters(courseIdList);
		for (CoreWork coreWork : workList) {
			workIdList.add(coreWork.getCrwokUuid());
		}
		
		if (userIdList.size() <= 0 || workIdList.size() <= 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "该班级年级下没有学生或没有课程!", totalList), response);
			logger.info("[GradeTotalController]:end userCourse");
		}
		//4.根据workIdList和userIdList查询训练answer集合,answerList->Map<key->userUuid+";"+workUuid>
		Map<String, CoreWorkAnswer> map = new HashMap<String, CoreWorkAnswer>();
		List<CoreWorkAnswer> answerList = coreWorkAnswerService.findCoreWorkAnswerForListsByFilters(workIdList, userIdList);
		for (CoreWorkAnswer coreWorkAnswer : answerList) {
			map.put(coreWorkAnswer.getCrwkaUser()+";"+coreWorkAnswer.getCrwkaWorkUuid(), coreWorkAnswer);
		}
		//学生课程统计，包装对象
		GradeTotal gradeTotal = null;
		for (CoreUser coreUser : userList) {		
			gradeTotal = new GradeTotal();
			int hwzKey = 0; //行未做Key
			StringBuffer hwzValue = new StringBuffer(); //行未做Value
			int hyzwmfKey = 0; //行已做未满分Key
			StringBuffer hyzwmfValue = new StringBuffer(); //行已做未满分Value
			int hyzmfKey = 0; //行已做满分Key
			StringBuffer hyzmfValue = new StringBuffer();; //行已做满分Value
			String code = coreUser.getCrusrCode();
			if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
				code = coreUser.getCrusrName();
			}
			gradeTotal.setColumnObj(code);
			for (CoreWork coreWork : workList) {
				CoreWorkAnswer coreWorkAnswer = map.get(coreUser.getCrusrUuid() + ";" + coreWork.getCrwokUuid());
				if (coreWorkAnswer != null) { //已做
					if (coreWorkAnswer.getCrwkaScore().equals(MAX_SCORE)) { //满分
						hyzmfKey++;
						hyzmfValue.append(coreWork.getCrwokCourseName()).append(",");
					} else { //未满分
						hyzwmfKey++;
						hyzwmfValue.append(coreWork.getCrwokCourseName()).append(",");
					}					
				} else { //未做
					hwzKey++;
					hwzValue.append(coreWork.getCrwokCourseName()).append(",");
				}
			}
			gradeTotal.setHwzKey(String.valueOf(hwzKey));
			gradeTotal.setHwzValue(hwzValue.toString());
			gradeTotal.setHyzmfKey(String.valueOf(hyzmfKey));
			gradeTotal.setHyzmfValue(hyzmfValue.toString());
			gradeTotal.setHyzwmfKey(String.valueOf(hyzwmfKey));
			gradeTotal.setHyzwmfValue(hyzwmfValue.toString());
			totalList.add(gradeTotal);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "统计获取成功!", totalList),response);
		logger.info("[GradeTotalController]:end userCourse");

	}
	
	/**
	* 课程学生
	* 
	* @return
	*/
	@RequestMapping(value="/course/user", method=RequestMethod.POST)
	public void courseUser (String strGrade, String strClass, HttpServletResponse response) {
		logger.info("[GradeTotalController]:begin courseUser");
		List<GradeTotal> totalList = new ArrayList<GradeTotal>();
		//1.查询班级下的所有学生集合，userList->userIdList
		List<String> userIdList = new ArrayList<String>();
		List<CoreUser> userList = coreUserService.findCoreUserList(strGrade);
		for (CoreUser coreUser : userList) {
			userIdList.add(coreUser.getCrusrUuid());
		}
		//2.查询班级下的所有课程集合，courseList->courseIdList
		List<String> courseIdList = new ArrayList<String>();
		List<CoreGradeCourse> courseList = coreGradeCourseService.findCoreGradeCourseList(strGrade, strClass);
		for (CoreGradeCourse coreGradeCourse : courseList) {
			courseIdList.add(coreGradeCourse.getCrcreUuid());
		}
		if (courseIdList.size() <= 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "该班级年级下没有课程!", totalList), response);
			logger.info("[GradeTotalController]:end userCourse");
		}
		//3.根据courseIdList查询训练workList集合->workIdList
		List<String> workIdList = new ArrayList<String>();
		List<CoreWork> workList = coreWorkService.findCoreWorkForListByFilters(courseIdList);
		for (CoreWork coreWork : workList) {
			workIdList.add(coreWork.getCrwokUuid());
		}
		
		if (userIdList.size() <= 0 || workIdList.size() <= 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "该班级年级下没有学生或没有课程!", totalList), response);
			logger.info("[GradeTotalController]:end courseUser");
		}
		//4.根据workIdList和userIdList查询训练answer集合,answerList->Map<key->userUuid+";"+workUuid>
		Map<String, CoreWorkAnswer> map = new HashMap<String, CoreWorkAnswer>();
		List<CoreWorkAnswer> answerList = coreWorkAnswerService.findCoreWorkAnswerForListsByFilters(workIdList, userIdList);
		for (CoreWorkAnswer coreWorkAnswer : answerList) {
			map.put(coreWorkAnswer.getCrwkaUser()+";"+coreWorkAnswer.getCrwkaWorkUuid(), coreWorkAnswer);
		}
		//课程学生统计，包装对象
		GradeTotal gradeTotal = null;
		for (CoreWork coreWork : workList) {		
			gradeTotal = new GradeTotal();
			int hwzKey = 0; //行未做Key
			StringBuffer hwzValue = new StringBuffer(); //行未做Value
			int hyzwmfKey = 0; //行已做未满分Key
			StringBuffer hyzwmfValue = new StringBuffer(); //行已做未满分Value
			int hyzmfKey = 0; //行已做满分Key
			StringBuffer hyzmfValue = new StringBuffer();; //行已做满分Value			
			gradeTotal.setColumnObj(coreWork.getCrwokCourseName());
			for (CoreUser coreUser : userList) {
				CoreWorkAnswer coreWorkAnswer = map.get(coreUser.getCrusrUuid() + ";" + coreWork.getCrwokUuid());
				if (coreWorkAnswer != null) { //已做
					if (coreWorkAnswer.getCrwkaScore().equals(MAX_SCORE)) { //满分
						hyzmfKey++;
						String code = coreUser.getCrusrCode();
						if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
							code = coreUser.getCrusrName();
						}
						hyzmfValue.append(code).append(",");
					} else { //未满分
						hyzwmfKey++;
						String code = coreUser.getCrusrCode();
						if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
							code = coreUser.getCrusrName();
						}
						hyzwmfValue.append(code).append(",");
					}					
				} else { //未做
					hwzKey++;
					String code = coreUser.getCrusrCode();
					if (StringUtil.isEmpty(coreUser.getCrusrCode())) {
						code = coreUser.getCrusrName();
					}
					hwzValue.append(code).append(",");
				}
			}
			gradeTotal.setHwzKey(String.valueOf(hwzKey));
			gradeTotal.setHwzValue(hwzValue.toString());
			gradeTotal.setHyzmfKey(String.valueOf(hyzmfKey));
			gradeTotal.setHyzmfValue(hyzmfValue.toString());
			gradeTotal.setHyzwmfKey(String.valueOf(hyzwmfKey));
			gradeTotal.setHyzwmfValue(hyzwmfValue.toString());
			totalList.add(gradeTotal);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "统计获取成功!", totalList),response);
		logger.info("[GradeTotalController]:end courseUser");

	}
	
}