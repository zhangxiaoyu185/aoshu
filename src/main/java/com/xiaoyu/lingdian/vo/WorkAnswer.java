package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreWorkAnswer;
import com.xiaoyu.lingdian.vo.BaseVO;

public class WorkAnswer implements BaseVO {

	/**
	*回答标识UUID
	*/
	private String crwkaUuid;

	/**
	*模拟试卷UUID
	*/
	private String crwkaWorkUuid;

	/**
	*得分
	*/
	private Integer crwkaScore;

	/**
	*时间
	*/
	private String crwkaTime;
	
	/**
	*状态：0已完结/1需订正
	*/
	private Integer crwkaState;

	public String getCrwkaUuid() {
		return crwkaUuid;
	}

	public void setCrwkaUuid(String crwkaUuid) {
		this.crwkaUuid = crwkaUuid;
	}

	public String getCrwkaWorkUuid() {
		return crwkaWorkUuid;
	}

	public void setCrwkaWorkUuid(String crwkaWorkUuid) {
		this.crwkaWorkUuid = crwkaWorkUuid;
	}

	public Integer getCrwkaScore() {
		return crwkaScore;
	}

	public void setCrwkaScore(Integer crwkaScore) {
		this.crwkaScore = crwkaScore;
	}

	public String getCrwkaTime() {
		return crwkaTime;
	}

	public void setCrwkaTime(String crwkaTime) {
		this.crwkaTime = crwkaTime;
	}

	public Integer getCrwkaState() {
		return crwkaState;
	}

	public void setCrwkaState(Integer crwkaState) {
		this.crwkaState = crwkaState;
	}

	public WorkAnswer( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreWorkAnswer po = (CoreWorkAnswer) poObj;
		this.crwkaWorkUuid = po.getCrwkaWorkUuid();
		this.crwkaScore = po.getCrwkaScore();
		this.crwkaTime = po.getCrwkaTime();
		this.crwkaState = po.getCrwkaState();
	}

}