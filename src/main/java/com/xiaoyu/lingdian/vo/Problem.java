package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.tool.StringUtil;

/**
* 题目json串
*/
public class Problem  {

	/**
	*题目UUID
	*/
	private String crqtsUuid;

	/**
	*题目编号
	*/
	private String crqtsCode;

	/**
	*类别
	*/
	private String crqtsCategory;
	
	/**
	*类别名称 
	*/
	private String crceyName;
	
	/**
	*难易程度
	*/
	private Integer crqtsLevel;

	/**
	*年级
	*/
	private String crqtsClass;

	/**
	*年级名称
	*/
	private String crcasName;

	/**
	*知识点
	*/
	private String crqtsKnowledge;

	/**
	*知识点名称
	*/
	private String crkleName;

	/**
	*图片目录
	*/
	private String crqtsDir;
	
	/**
	*视频目录
	*/
	private String crqtsDirMovie;
	
	/**
	*题目类型（0文字1图片）
	*/
	private Integer crqtsQuesType;

	/**
	*题目图片URL
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
	*解析图片URL
	*/
	private String crqtsAnalysisUrl;

	/**
	*解析文字
	*/
	private String crqtsAnalysisFont;

	/**
	*问题（特定的文字格式）
	*/
	private String crqtsProblem;

	/**
	*答案（特定格式文字编辑）
	*/
	private String crqtsAnswer;

	/**
	*背景色
	*/
	private String crqtsColor;

	/**
	*回答结果
	*/
	private String result;
	
	/**
	*判断结果(0对1错)
	*/
	private Integer judge;

	/**
	*视频路径
	*/
	private String crqtsMovie;
	
	public String getCrqtsUuid() {
		return crqtsUuid;
	}

	public void setCrqtsUuid(String crqtsUuid) {
		this.crqtsUuid = crqtsUuid;
	}

	public String getCrqtsCode() {
		return crqtsCode;
	}

	public void setCrqtsCode(String crqtsCode) {
		this.crqtsCode = crqtsCode;
	}

	public String getCrceyName() {
		return crceyName;
	}

	public void setCrceyName(String crceyName) {
		this.crceyName = crceyName;
	}

	public Integer getCrqtsLevel() {
		return crqtsLevel;
	}

	public void setCrqtsLevel(Integer crqtsLevel) {
		this.crqtsLevel = crqtsLevel;
	}

	public String getCrqtsClass() {
		return crqtsClass;
	}

	public void setCrqtsClass(String crqtsClass) {
		this.crqtsClass = crqtsClass;
	}

	public String getCrcasName() {
		return crcasName;
	}

	public void setCrcasName(String crcasName) {
		this.crcasName = crcasName;
	}

	public String getCrkleName() {
		return crkleName;
	}

	public void setCrkleName(String crkleName) {
		this.crkleName = crkleName;
	}

	public String getCrqtsDir() {
		return crqtsDir;
	}

	public void setCrqtsDir(String crqtsDir) {
		this.crqtsDir = crqtsDir;
	}

	public Integer getCrqtsQuesType() {
		return crqtsQuesType;
	}

	public void setCrqtsQuesType(Integer crqtsQuesType) {
		this.crqtsQuesType = crqtsQuesType;
	}

	public String getCrqtsQuesUrl() {
		return crqtsQuesUrl;
	}

	public void setCrqtsQuesUrl(String crqtsQuesUrl) {
		this.crqtsQuesUrl = crqtsQuesUrl;
	}

	public String getCrqtsQuesFont() {
		return crqtsQuesFont;
	}

	public void setCrqtsQuesFont(String crqtsQuesFont) {
		this.crqtsQuesFont = crqtsQuesFont;
	}

	public Integer getCrqtsAnalysisType() {
		return crqtsAnalysisType;
	}

	public void setCrqtsAnalysisType(Integer crqtsAnalysisType) {
		this.crqtsAnalysisType = crqtsAnalysisType;
	}

	public String getCrqtsAnalysisUrl() {
		return crqtsAnalysisUrl;
	}

	public void setCrqtsAnalysisUrl(String crqtsAnalysisUrl) {
		this.crqtsAnalysisUrl = crqtsAnalysisUrl;
	}

	public String getCrqtsAnalysisFont() {
		return crqtsAnalysisFont;
	}

	public void setCrqtsAnalysisFont(String crqtsAnalysisFont) {
		this.crqtsAnalysisFont = crqtsAnalysisFont;
	}

	public String getCrqtsProblem() {
		return crqtsProblem;
	}

	public void setCrqtsProblem(String crqtsProblem) {
		this.crqtsProblem = crqtsProblem;
	}

	public String getCrqtsAnswer() {
		return crqtsAnswer;
	}

	public void setCrqtsAnswer(String crqtsAnswer) {
		this.crqtsAnswer = crqtsAnswer;
	}

	public String getCrqtsColor() {
		return crqtsColor;
	}

	public void setCrqtsColor(String crqtsColor) {
		this.crqtsColor = crqtsColor;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getJudge() {
		return judge;
	}

	public void setJudge(Integer judge) {
		this.judge = judge;
	}

	public String getCrqtsCategory() {
		return crqtsCategory;
	}

	public void setCrqtsCategory(String crqtsCategory) {
		this.crqtsCategory = crqtsCategory;
	}

	public String getCrqtsKnowledge() {
		return crqtsKnowledge;
	}

	public void setCrqtsKnowledge(String crqtsKnowledge) {
		this.crqtsKnowledge = crqtsKnowledge;
	}

	public String getCrqtsMovie() {
		return crqtsMovie;
	}

	public void setCrqtsMovie(String crqtsMovie) {
		this.crqtsMovie = crqtsMovie;
	}

	public String getCrqtsDirMovie() {
		return crqtsDirMovie;
	}

	public void setCrqtsDirMovie(String crqtsDirMovie) {
		this.crqtsDirMovie = crqtsDirMovie;
	}

	public Problem() {
	}
	
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreQuestions po = (CoreQuestions) poObj;
		this.crqtsUuid = po.getCrqtsUuid();
		this.crqtsCode = po.getCrqtsCode();
		this.crqtsCategory = po.getCrqtsCategory();
		this.crcasName = po.getCrcasName();
		this.crceyName = po.getCrceyName();
		this.crkleName = po.getCrkleName();
		this.crqtsLevel = po.getCrqtsLevel();
		this.crqtsClass = po.getCrqtsClass();
		this.crqtsKnowledge = po.getCrqtsKnowledge();
		this.crqtsDir = po.getCrqtsDir();
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
		this.crqtsProblem = po.getCrqtsProblem();
		this.crqtsAnswer = po.getCrqtsAnswer();
		this.crqtsColor = po.getCrqtsColor();
		this.crqtsMovie = po.getCrqtsMovie();
		this.crqtsDirMovie = po.getCrqtsDirMovie();
	}
	
}