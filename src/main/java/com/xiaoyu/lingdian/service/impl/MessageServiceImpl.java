package com.xiaoyu.lingdian.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.entity.response.Article;
import com.xiaoyu.lingdian.entity.response.NewsMessage;
import com.xiaoyu.lingdian.entity.weixin.Constant;
import com.xiaoyu.lingdian.service.MessageService;
import com.xiaoyu.lingdian.tool.wx.WxXmlOper;

/**
 * 图文消息
 */
@Service
public class MessageServiceImpl implements MessageService {

	/**
	 * 图文消息返回
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 */
	public String picMessage(String fromUserName, String toUserName,
			String content) {
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		content = content.substring(content.indexOf("图文消息") + 3);
		// 单图文消息
		if ("1".equals(content)) {
			Article article = new Article();
			article.setTitle("微信公众帐号开发教程Java版");
			article.setDescription("为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
			article.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
			article.setUrl("http://blog.csdn.net/lyq8479");
			articleList.add(article);
			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			// 将图文消息对象转换成xml字符串
			return WxXmlOper.newsMessageToXml(newsMessage);
		}
		// 单图文消息---不含图片
		else if ("2".equals(content)) {
			Article article = new Article();
			article.setTitle("微信公众帐号开发教程Java版");
			// 图文消息中可以使用QQ表情、符号表情
			article.setDescription("柳峰，80后，"
					+ WxXmlOper.emoji(0x1F6B9)
					+ "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
			// 将图片置为空
			article.setPicUrl("");
			article.setUrl("http://blog.csdn.net/lyq8479");
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return WxXmlOper.newsMessageToXml(newsMessage);
		}
		// 多图文消息
		else if ("3".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("微信公众帐号开发教程\n引言");
			article1.setDescription("");
			article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
			article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

			Article article2 = new Article();
			article2.setTitle("第2篇\n微信公众帐号的类型");
			article2.setDescription("");
			article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");

			Article article3 = new Article();
			article3.setTitle("第3篇\n开发模式启用及接口配置");
			article3.setDescription("");
			article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return WxXmlOper.newsMessageToXml(newsMessage);
		}
		// 多图文消息---首条消息不含图片
		else if ("4".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("微信公众帐号开发教程Java版");
			article1.setDescription("");
			// 将图片置为空
			article1.setPicUrl("");
			article1.setUrl("http://blog.csdn.net/lyq8479");

			Article article2 = new Article();
			article2.setTitle("第4篇\n消息及消息处理工具的封装");
			article2.setDescription("");
			article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

			Article article3 = new Article();
			article3.setTitle("第5篇\n各种消息的接收与响应");
			article3.setDescription("");
			article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");

			Article article4 = new Article();
			article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
			article4.setDescription("");
			article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			articleList.add(article4);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return WxXmlOper.newsMessageToXml(newsMessage);
		}
		// 多图文消息---最后一条消息不含图片
		else if ("5".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("第7篇\n文本消息中换行符的使用");
			article1.setDescription("");
			article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
			article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

			Article article2 = new Article();
			article2.setTitle("第8篇\n文本消息中使用网页超链接");
			article2.setDescription("");
			article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

			Article article3 = new Article();
			article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
			article3.setDescription("");
			// 将图片置为空
			article3.setPicUrl("");
			article3.setUrl("http://blog.csdn.net/lyq8479");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			return WxXmlOper.newsMessageToXml(newsMessage);
		}
		newsMessage.setArticleCount(0);
		newsMessage.setArticles(articleList);
		return WxXmlOper.newsMessageToXml(newsMessage);
	}

	/**
	 * 微信公共账号发送给账号文本内容
	 * 
	 * @param content 文本内容
	 * @param toUser 微信用户
	 * @return
	 */
	public void sendTextMessageToUser(String content, String toUser, String accessToken ) {
		String json = "{\"touser\": \"" + toUser
				+ "\",\"msgtype\": \"text\", \"text\": {\"content\": \""
				+ content + "\"}}";
		// 获取access_token
		// 获取请求路径
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ accessToken;
		System.out.println("json:" + json);
		try {
			connectWeiXinInterface(action, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 微信公共账号发送给账号语音或者图片
	 * 
	 * @param mediaId 图片或者语音内容
	 * @param toUser 微信用户
	 * @param messageType 消息类型
	 * @return
	 */
	public void sendPicOrVoiceMessageToUser(String mediaId, String toUser,
			String msgType, String accessToken) {
		String json = null;
		if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) {
			json = "{\"touser\": \"" + toUser
					+ "\",\"msgtype\": \"image\", \"image\": {\"media_id\": \""
					+ mediaId + "\"}}";
		} else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) {
			json = "{\"touser\": \"" + toUser
					+ "\",\"msgtype\": \"voice\", \"voice\": {\"media_id\": \""
					+ mediaId + "\"}}";
		}
		// 获取请求路径
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ accessToken;
		try {
			connectWeiXinInterface(action, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送图文给指定用户
	 * 
	 * @param openId 用户的id
	 */
	public void sendNewsMessageToUser(String openId, String access_token) {
		ArrayList<Object> articles = new ArrayList<Object>();
		Article article1 = new Article();
		article1.setTitle("<h1>支付成功</h1>");
		article1.setDescription("<a>sddddd</a>dfdddd\njj;lkkk");
//		article1.setPicUrl("http://noavatar.csdn.net/3/A/D/1_yuhang123wo.jpg");
//		article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

//		Article article2 = new Article();
//		article2.setTitle("第2篇\n微信公众帐号的类型");
//		article2.setDescription("");
//		article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
//		article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");
//
//		Article article3 = new Article();
//		article3.setTitle("第3篇\n开发模式启用及接口配置");
//		article3.setDescription("");
//		article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
//		article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");

		articles.add(article1);
//		articles.add(article2);
//		articles.add(article3);
		String str = JSON.toJSONString(articles);
		String json = "{\"touser\":\"" + openId
				+ "\",\"msgtype\":\"news\",\"news\":{\"articles\":" + str + "}"
				+ "}";
		json = json.replace("picUrl", "picurl");
		System.out.println(json);
		// 获取请求路径
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ access_token;
		try {
			connectWeiXinInterface(action, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接请求微信后台接口
	 * 
	 * @param action 接口url
	 * @param json 请求接口传送的json字符串
	 */
	public void connectWeiXinInterface(String action, String json) {
		URL url;
		try {
			url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(json.getBytes("UTF-8"));// 传入参数
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			System.out.println("请求返回结果:" + result);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}