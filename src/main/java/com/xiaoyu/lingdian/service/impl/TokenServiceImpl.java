package com.xiaoyu.lingdian.service.impl;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiaoyu.lingdian.entity.weixin.CheckModel;
import com.xiaoyu.lingdian.service.TokenService;
import com.xiaoyu.lingdian.tool.encrypt.EncoderHandler;

@Service
public class TokenServiceImpl implements TokenService {
	
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
	@Transactional
	public String validate(String wxToken, CheckModel tokenModel){
		String signature = tokenModel.getSignature();
		Long timestamp = tokenModel.getTimestamp();
		Long nonce = tokenModel.getNonce();
		String echostr = tokenModel.getEchostr();
		if(signature != null && timestamp != null && nonce != null) {
			String[] str = {wxToken, timestamp+"", nonce+""};
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
	        // SHA1加密	
	        String digest = EncoderHandler.encode("SHA1", bigStr).toLowerCase();
	        // 确认请求来至微信
	        if (digest.equals(signature)) {
	        	return echostr;
	        }
		}
		return "error";
	}
	
}