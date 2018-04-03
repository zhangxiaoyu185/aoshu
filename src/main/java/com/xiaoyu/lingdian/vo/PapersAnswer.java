package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CorePapersAnswer;
import com.xiaoyu.lingdian.vo.BaseVO;

public class PapersAnswer implements BaseVO {

	/**
	*回答标识UUID
	*/
	private String crpsaUuid;

	/**
	*模拟试卷UUID
	*/
	private String crpsaPapersUuid;

	/**
	*得分
	*/
	private Integer crpsaScore;

	/**
	*时间
	*/
	private String crpsaTime;
	
	/**
	*状态：0已完结/1需订正
	*/
	private Integer crpsaState;

	public void setCrpsaUuid(String crpsaUuid) {
		this.crpsaUuid = crpsaUuid;
	}

	public String getCrpsaUuid( ) {
		return crpsaUuid;
	}

	public void setCrpsaPapersUuid(String crpsaPapersUuid) {
		this.crpsaPapersUuid = crpsaPapersUuid;
	}

	public String getCrpsaPapersUuid( ) {
		return crpsaPapersUuid;
	}

	public void setCrpsaScore(Integer crpsaScore) {
		this.crpsaScore = crpsaScore;
	}

	public Integer getCrpsaScore( ) {
		return crpsaScore;
	}

	public String getCrpsaTime() {
		return crpsaTime;
	}

	public void setCrpsaTime(String crpsaTime) {
		this.crpsaTime = crpsaTime;
	}

	public void setCrpsaState(Integer crpsaState) {
		this.crpsaState = crpsaState;
	}

	public Integer getCrpsaState( ) {
		return crpsaState;
	}

	public PapersAnswer( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CorePapersAnswer po = (CorePapersAnswer) poObj;
		this.crpsaPapersUuid = po.getCrpsaPapersUuid();
		this.crpsaScore = po.getCrpsaScore();
		this.crpsaTime = po.getCrpsaTime();
		this.crpsaState = po.getCrpsaState();
	}

}