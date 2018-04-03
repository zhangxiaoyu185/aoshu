package com.xiaoyu.lingdian.service;

/**
 * 图文消息
 */
public interface MessageService {

	/**
	 * 图文消息返回
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 */
	public String picMessage(String fromUserName, String toUserName, String content);

	/**
	 * 微信公共账号发送给账号文本内容
	 * 
	 * @param content 文本内容
	 * @param toUser 微信用户
	 * @return
	 */
	public void sendTextMessageToUser(String content, String toUser, String accessToken);
	
	/**
	 * 微信公共账号发送给账号语音或者图片
	 * 
	 * @param mediaId 图片或者语音内容
	 * @param toUser 微信用户
	 * @param messageType 消息类型
	 * @return
	 */
	public void sendPicOrVoiceMessageToUser(String mediaId, String toUser,
			String msgType, String accessToken);
	
	/**
	 * 发送图文给指定用户
	 * 
	 * @param openId 用户的id
	 */
	public void sendNewsMessageToUser(String openId, String access_token);
	
}