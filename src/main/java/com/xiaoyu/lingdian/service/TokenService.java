package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.weixin.CheckModel;

public interface TokenService {

	/**
	 * 微信开发者验证
	 * 
	 * @param wxToken  
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	public String validate(String wxToken, CheckModel tokenModel);
	
}