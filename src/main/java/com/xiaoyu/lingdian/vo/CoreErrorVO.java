package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreError;
import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 错题表
*/
public class CoreErrorVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crerrUuid;

	/**
	*来源UUID
	*/
	private String crerrFrom;

	/**
	*用户UUID
	*/
	private String crerrUser;
	
	/**
	*用户名称
	*/
	private String crusrName;
	
	/**
	*真实名称
	*/
	private String crusrCode;
	
	/**
	*来源名称
	*/
	private String crerrFromName;

	/**
	*来源类型（1模拟试卷、2经典练习、3强化训练）
	*/
	private Integer crerrFromType;

	/**
	*题目UUID
	*/
	private String crerrQues;

	/**
	*题目编号
	*/
	private String crqtsQuesCode;

	/**
	*类别
	*/
	private String crqtsQuesCategory;

	/**
	*难易程度
	*/
	private Integer crqtsQuesLevel;

	/**
	*年级
	*/
	private String crqtsQuesClass;
	
	/**
	*年级
	*/
	private String crqtsQuesClassName;

	/**
	*知识点
	*/
	private String crqtsQuesKnowledge;
	
	/**
	*知识点
	*/
	private String crqtsQuesKnowledgeName;

	/**
	*图片目录
	*/
	private String crqtsQuesDir;

	/**
	*题目类型（0文字1图片）
	*/
	private Integer crqtsQuesType;

	/**
	*题目图片UUID
	*/
	private String crqtsQuesUrl;

	/**
	*题目文字
	*/
	private String crqtsQuesFont;

	/**
	*解析类型（0文字1图片）
	*/
	private Integer crqtsAnalysisType;

	/**
	*解析图片UUID
	*/
	private String crqtsAnalysisUrl;

	/**
	*解析文字
	*/
	private String crqtsAnalysisFont;

	/**
	*问题（特定的文字格式）
	*/
	private String crqtsQuesProblem;

	/**
	*答案（特定格式文字编辑）
	*/
	private String crqtsQuesAnswer;

	/**
	*背景色
	*/
	private String crqtsQuesColor;

	/**
	*回答结果
	*/
	private String crerrResult;

	/**
	*判断（0对-》已订正;1错-未订正）
	*/
	private Integer crerrJudge;

	/**
	*创建时间
	*/
	private String crerrCdate;

	/**
	*修改时间
	*/
	private String crerrUdate;
	
	public void setCrerrUuid(String crerrUuid) {
		this.crerrUuid = crerrUuid;
	}

	public String getCrerrUuid( ) {
		return crerrUuid;
	}

	public String getCrerrUser() {
		return crerrUser;
	}

	public void setCrerrUser(String crerrUser) {
		this.crerrUser = crerrUser;
	}

	public String getCrusrName() {
		return crusrName;
	}

	public void setCrusrName(String crusrName) {
		this.crusrName = crusrName;
	}

	public void setCrerrFrom(String crerrFrom) {
		this.crerrFrom = crerrFrom;
	}

	public String getCrerrFrom( ) {
		return crerrFrom;
	}

	public void setCrerrFromName(String crerrFromName) {
		this.crerrFromName = crerrFromName;
	}

	public String getCrerrFromName( ) {
		return crerrFromName;
	}

	public void setCrerrFromType(Integer crerrFromType) {
		this.crerrFromType = crerrFromType;
	}

	public Integer getCrerrFromType( ) {
		return crerrFromType;
	}

	public void setCrerrQues(String crerrQues) {
		this.crerrQues = crerrQues;
	}

	public String getCrerrQues( ) {
		return crerrQues;
	}

	public void setCrqtsQuesCode(String crqtsQuesCode) {
		this.crqtsQuesCode = crqtsQuesCode;
	}

	public String getCrqtsQuesCode( ) {
		return crqtsQuesCode;
	}

	public void setCrqtsQuesCategory(String crqtsQuesCategory) {
		this.crqtsQuesCategory = crqtsQuesCategory;
	}

	public String getCrqtsQuesCategory( ) {
		return crqtsQuesCategory;
	}

	public void setCrqtsQuesLevel(Integer crqtsQuesLevel) {
		this.crqtsQuesLevel = crqtsQuesLevel;
	}

	public Integer getCrqtsQuesLevel( ) {
		return crqtsQuesLevel;
	}

	public void setCrqtsQuesClass(String crqtsQuesClass) {
		this.crqtsQuesClass = crqtsQuesClass;
	}

	public String getCrqtsQuesClass( ) {
		return crqtsQuesClass;
	}

	public void setCrqtsQuesKnowledge(String crqtsQuesKnowledge) {
		this.crqtsQuesKnowledge = crqtsQuesKnowledge;
	}

	public String getCrqtsQuesKnowledge( ) {
		return crqtsQuesKnowledge;
	}

	public void setCrqtsQuesDir(String crqtsQuesDir) {
		this.crqtsQuesDir = crqtsQuesDir;
	}

	public String getCrqtsQuesDir( ) {
		return crqtsQuesDir;
	}

	public void setCrqtsQuesType(Integer crqtsQuesType) {
		this.crqtsQuesType = crqtsQuesType;
	}

	public Integer getCrqtsQuesType( ) {
		return crqtsQuesType;
	}

	public void setCrqtsQuesUrl(String crqtsQuesUrl) {
		this.crqtsQuesUrl = crqtsQuesUrl;
	}

	public String getCrqtsQuesUrl( ) {
		return crqtsQuesUrl;
	}

	public void setCrqtsQuesFont(String crqtsQuesFont) {
		this.crqtsQuesFont = crqtsQuesFont;
	}

	public String getCrqtsQuesFont( ) {
		return crqtsQuesFont;
	}

	public void setCrqtsAnalysisType(Integer crqtsAnalysisType) {
		this.crqtsAnalysisType = crqtsAnalysisType;
	}

	public Integer getCrqtsAnalysisType( ) {
		return crqtsAnalysisType;
	}

	public void setCrqtsAnalysisUrl(String crqtsAnalysisUrl) {
		this.crqtsAnalysisUrl = crqtsAnalysisUrl;
	}

	public String getCrqtsAnalysisUrl( ) {
		return crqtsAnalysisUrl;
	}

	public void setCrqtsAnalysisFont(String crqtsAnalysisFont) {
		this.crqtsAnalysisFont = crqtsAnalysisFont;
	}

	public String getCrqtsAnalysisFont( ) {
		return crqtsAnalysisFont;
	}

	public void setCrqtsQuesProblem(String crqtsQuesProblem) {
		this.crqtsQuesProblem = crqtsQuesProblem;
	}

	public String getCrqtsQuesProblem( ) {
		return crqtsQuesProblem;
	}

	public void setCrqtsQuesAnswer(String crqtsQuesAnswer) {
		this.crqtsQuesAnswer = crqtsQuesAnswer;
	}

	public String getCrqtsQuesAnswer( ) {
		return crqtsQuesAnswer;
	}

	public void setCrqtsQuesColor(String crqtsQuesColor) {
		this.crqtsQuesColor = crqtsQuesColor;
	}

	public String getCrqtsQuesColor( ) {
		return crqtsQuesColor;
	}

	public void setCrerrResult(String crerrResult) {
		this.crerrResult = crerrResult;
	}

	public String getCrerrResult( ) {
		return crerrResult;
	}

	public void setCrerrJudge(Integer crerrJudge) {
		this.crerrJudge = crerrJudge;
	}

	public Integer getCrerrJudge( ) {
		return crerrJudge;
	}

	public String getCrerrCdate() {
		return crerrCdate;
	}

	public void setCrerrCdate(String crerrCdate) {
		this.crerrCdate = crerrCdate;
	}

	public String getCrerrUdate() {
		return crerrUdate;
	}

	public void setCrerrUdate(String crerrUdate) {
		this.crerrUdate = crerrUdate;
	}

	public String getCrqtsQuesClassName() {
		return crqtsQuesClassName;
	}

	public void setCrqtsQuesClassName(String crqtsQuesClassName) {
		this.crqtsQuesClassName = crqtsQuesClassName;
	}

	public String getCrqtsQuesKnowledgeName() {
		return crqtsQuesKnowledgeName;
	}

	public void setCrqtsQuesKnowledgeName(String crqtsQuesKnowledgeName) {
		this.crqtsQuesKnowledgeName = crqtsQuesKnowledgeName;
	}

	public String getCrusrCode() {
		return crusrCode;
	}

	public void setCrusrCode(String crusrCode) {
		this.crusrCode = crusrCode;
	}

	public CoreErrorVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreError po = (CoreError) poObj;
		this.crerrUuid = po.getCrerrUuid();
		this.crusrName = po.getCrusrName();
		this.crusrCode = po.getCrusrCode();
		this.crerrFrom = po.getCrerrFrom();
		this.crerrFromName = po.getCrerrFromName();
		this.crerrFromType = po.getCrerrFromType();
		this.crerrQues = po.getCrerrQues();
		this.crqtsQuesCode = po.getCrqtsQuesCode();
		this.crqtsQuesCategory = po.getCrqtsQuesCategory();
		this.crqtsQuesLevel = po.getCrqtsQuesLevel();
		this.crqtsQuesClass = po.getCrqtsQuesClass();
		this.crqtsQuesKnowledge = po.getCrqtsQuesKnowledge();
		this.crqtsQuesDir = po.getCrqtsQuesDir();
		this.crqtsQuesType = po.getCrqtsQuesType();
		this.crqtsQuesUrl = po.getCrqtsQuesUrl();
		if (!StringUtil.isEmpty(po.getCrqtsQuesFont())) {
			po.setCrqtsQuesFont(po.getCrqtsQuesFont().replaceAll(" ","&nbsp;"));
		}
		this.crqtsQuesFont = po.getCrqtsQuesFont();
		this.crqtsAnalysisType = po.getCrqtsAnalysisType();
		this.crqtsAnalysisUrl = po.getCrqtsAnalysisUrl();
		if (!StringUtil.isEmpty(po.getCrqtsAnalysisFont())) {
			po.setCrqtsAnalysisFont(po.getCrqtsAnalysisFont().replaceAll(" ","&nbsp;"));
		}
		this.crqtsAnalysisFont = po.getCrqtsAnalysisFont();
		if (!StringUtil.isEmpty(po.getCrqtsQuesProblem())) {
			po.setCrqtsQuesProblem(po.getCrqtsQuesProblem().replaceAll(" ","&nbsp;"));
		}
		this.crqtsQuesProblem = po.getCrqtsQuesProblem();
		this.crqtsQuesAnswer = po.getCrqtsQuesAnswer();
		this.crqtsQuesColor = po.getCrqtsQuesColor();
		this.crerrResult = po.getCrerrResult();
		this.crerrJudge = po.getCrerrJudge();
		this.crqtsQuesClassName = po.getCrqtsQuesClassName();
		this.crqtsQuesKnowledgeName = po.getCrqtsQuesKnowledgeName(); 
		this.crerrCdate = po.getCrerrCdate()!=null?DateUtil.formatTimesTampDate(po.getCrerrCdate()):"";
		this.crerrUdate = po.getCrerrUdate()!=null?DateUtil.formatTimesTampDate(po.getCrerrUdate()):"";
	}
	
	public void convertVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreQuestions po = (CoreQuestions) poObj;
		this.crerrQues = po.getCrqtsUuid();
		this.crqtsQuesCode = po.getCrqtsCode();
		this.crqtsQuesCategory = po.getCrqtsCategory();
		this.crqtsQuesLevel = po.getCrqtsLevel();
		this.crqtsQuesKnowledge = po.getCrqtsKnowledge();
		this.crqtsQuesDir = po.getCrqtsDir();
		this.crqtsQuesType = po.getCrqtsQuesType();
		this.crqtsQuesUrl = po.getCrqtsQuesUrl();
		if (!StringUtil.isEmpty(po.getCrqtsQuesFont())) {
			po.setCrqtsQuesFont(po.getCrqtsQuesFont().replaceAll(" ","&nbsp;"));
		}
		this.crqtsQuesFont = po.getCrqtsQuesFont();
		this.crqtsAnalysisType = po.getCrqtsAnalysisType();
		this.crqtsAnalysisUrl = po.getCrqtsAnalysisUrl();
		if (!StringUtil.isEmpty(po.getCrqtsAnalysisFont())) {
			po.setCrqtsAnalysisFont(po.getCrqtsAnalysisFont().replaceAll(" ","&nbsp;"));
		}
		this.crqtsAnalysisFont = po.getCrqtsAnalysisFont();
		if (!StringUtil.isEmpty(po.getCrqtsProblem())) {
			po.setCrqtsProblem(po.getCrqtsProblem().replaceAll(" ","&nbsp;"));
		}
		this.crqtsQuesProblem = po.getCrqtsProblem();
		this.crqtsQuesAnswer = po.getCrqtsAnswer();
		this.crqtsQuesColor = po.getCrqtsColor();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}