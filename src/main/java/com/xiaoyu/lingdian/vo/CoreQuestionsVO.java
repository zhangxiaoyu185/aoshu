package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreQuestions;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.StringUtil;

/**
* 题库表
*/
public class CoreQuestionsVO implements BaseVO {

	/**
	*标识UUID
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
	*创建日期
	*/
	private String crqtsCdate;

	/**
	*背景色
	*/
	private String crqtsColor;

	/**
	*修改日期
	*/
	private String crqtsUdate;

	/**
	*备注
	*/
	private String crqtsRemarks;

	/**
	*视频路径
	*/
	private String crqtsMovie;
	
	public void setCrqtsUuid(String crqtsUuid) {
		this.crqtsUuid = crqtsUuid;
	}

	public String getCrqtsUuid( ) {
		return crqtsUuid;
	}

	public String getCrqtsCode() {
		return crqtsCode;
	}

	public void setCrqtsCode(String crqtsCode) {
		this.crqtsCode = crqtsCode;
	}

	public void setCrqtsCategory(String crqtsCategory) {
		this.crqtsCategory = crqtsCategory;
	}

	public String getCrqtsCategory( ) {
		return crqtsCategory;
	}

	public void setCrqtsLevel(Integer crqtsLevel) {
		this.crqtsLevel = crqtsLevel;
	}

	public Integer getCrqtsLevel( ) {
		return crqtsLevel;
	}

	public void setCrqtsClass(String crqtsClass) {
		this.crqtsClass = crqtsClass;
	}

	public String getCrqtsClass( ) {
		return crqtsClass;
	}

	public void setCrqtsKnowledge(String crqtsKnowledge) {
		this.crqtsKnowledge = crqtsKnowledge;
	}

	public String getCrqtsKnowledge( ) {
		return crqtsKnowledge;
	}

	public String getCrqtsDir() {
		return crqtsDir;
	}

	public void setCrqtsDir(String crqtsDir) {
		this.crqtsDir = crqtsDir;
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

	public void setCrqtsProblem(String crqtsProblem) {
		this.crqtsProblem = crqtsProblem;
	}

	public String getCrqtsProblem( ) {
		return crqtsProblem;
	}

	public void setCrqtsAnswer(String crqtsAnswer) {
		this.crqtsAnswer = crqtsAnswer;
	}

	public String getCrqtsAnswer( ) {
		return crqtsAnswer;
	}

	public void setCrqtsCdate(String crqtsCdate) {
		this.crqtsCdate = crqtsCdate;
	}

	public String getCrqtsCdate( ) {
		return crqtsCdate;
	}

	public void setCrqtsColor(String crqtsColor) {
		this.crqtsColor = crqtsColor;
	}

	public String getCrqtsColor( ) {
		return crqtsColor;
	}

	public void setCrqtsUdate(String crqtsUdate) {
		this.crqtsUdate = crqtsUdate;
	}

	public String getCrqtsUdate( ) {
		return crqtsUdate;
	}

	public void setCrqtsRemarks(String crqtsRemarks) {
		this.crqtsRemarks = crqtsRemarks;
	}

	public String getCrqtsRemarks( ) {
		return crqtsRemarks;
	}

	public String getCrceyName() {
		return crceyName;
	}

	public void setCrceyName(String crceyName) {
		this.crceyName = crceyName;
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

	public CoreQuestionsVO( ) { 
	}

	@Override
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
		this.crqtsCdate = po.getCrqtsCdate()!=null?DateUtil.formatDefaultDate(po.getCrqtsCdate()):"";
		this.crqtsColor = po.getCrqtsColor();
		this.crqtsUdate = po.getCrqtsUdate()!=null?DateUtil.formatDefaultDate(po.getCrqtsUdate()):"";
		this.crqtsRemarks = po.getCrqtsRemarks();
		this.crqtsMovie = po.getCrqtsMovie();
		this.crqtsDirMovie = po.getCrqtsDirMovie();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}