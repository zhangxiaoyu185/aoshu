package com.xiaoyu.lingdian.tool.wx;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class WeixinUtil {
	
	private static Logger log = LoggerFactory.getLogger("BASE_LOGGER");
	// 主动发送客服消息url
	public final static String SEND_CUSTOM_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	// 微信模板消息调用接口URL
	public final static String TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	// 通过OpenID获取查询用户所在分组url
	protected final static String GET_PERSONGROUPID_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	// 生成临时二维码url
	protected final static String TEMPORARY_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	// 生成永久二维码url
	protected final static String PERMANENT_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	// 换取二维码url
	protected final static String GET_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/shoMyna Wangrcode?ticket=TICKET";
	// 获取关注者列表url
	protected final static String GET_USERLIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	// 获取所有分组信息url
	protected final static String GET_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	// 创建分组url
	protected final static String CREATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	// 修改分组url
	protected final static String UPDATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	// 移动用户分组url
	protected final static String REMOVE_MEMBER_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	// 上传多媒体文件url
	protected final static String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	// 下载多媒体文件url
	protected final static String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	// 菜单查询（GET）
	protected final static String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（GET）
	protected final static String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * URL编码(utf-8)
	 * 
	 * @param source
	 * @return String
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 根据类型判断文件扩展名 
	 * @param contentType 内容类型 
	 * @return String
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType)) {
			fileExt = ".jpg";
		}
		else if ("audio/mpeg".equals(contentType)) {
			fileExt = ".mp3";
		}
		else if ("audio/amr".equals(contentType)) {
			fileExt = ".amr";
		}
		else if ("video/mp4".equals(contentType)) {
			fileExt = ".mp4";
		}
		else if ("video/mpeg4".equals(contentType)) {
			fileExt = ".mp4";
		}
		return fileExt;
	}
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try{
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new WxX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr){
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null){
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		}
		catch (ConnectException ce){
			System.out.println("Weixin server connection timed out.");
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("https request error:{}" + e);
		}
		return jsonObject;
	}
	
	/**
	 * 发起http请求
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static String httpStringRequest(String requestUrl,
			String requestMethod, String outputStr) {
		String jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new WxX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				System.out.println("------------------------" + outputStr);
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = buffer.toString();
		}
		catch (ConnectException ce) {
			System.out.println("Weixin server connection timed out.");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("https request error:{}" + e);
		}
		return jsonObject;
	}
	
	/**
	 * 调用微信JS接口的临时票据
	 * 
	 * @param access_token 接口访问凭证
	 * @return
	 */
	public static String getJsApiTicket(String access_token) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		String ticket = null;
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
			} catch (JSONException e) {
				ticket = null;
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return ticket;
	}
	
	/**
	 * 发送客服消息方法
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式客服消息
	 * @return true|false
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
		log.info(jsonMsg);
		boolean result = false;
		String requestUrl = SEND_CUSTOM_URL.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info(errorMsg);
			}
			else {
				log.error(errorMsg);
			}
		}
		return result;
	}
	
	/**
	 * 下载多媒体文件
	 * 
	 * @param accessToken 调用接口凭证
	 * @param mediaId 媒体文件ID
	 * @param savePath 保存路径
	 * @return String 保存文件路径
	 */
	public static String getMedia(String accessToken, String mediaId,
			String savePath) {
		String filePath = null;
		String requestUrl = DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		try{
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = getFileExt(conn.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();
			conn.disconnect();
			log.info("下载媒体文件成功,filePath=" + filePath);
		}
		catch (Exception e) {
			filePath = null;
			log.error("下载媒体文件失败:{}", e);
		}
		return filePath;
	}
	
	/**
	 * 判断字符是否是中文
	 * 
	 * @param c 字符
	 * @return 是否是中文
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	
	/**
	 * 替换乱码的字符
	 * 
	 * @param strName
	 * @return
	 */
	public static String replaceMessy(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					ch[i] = '?';
				}
			}
		}
		return new String(ch);
	}
	
	/**
	 * 查询用户所在分组
	 * 
	 * @param accessToken 调用接口凭证
	 * @param openId  普通用户的标识，对当前公众号唯一
	 * @return groupid
	 */
	public static int getPersonGroupId(String accessToken, String openId) {
		int groupId = 0;
		String requestUrl = GET_PERSONGROUPID_URL.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\"}";
		// 创建分组
		JSONObject jsonObject = httpRequest(requestUrl, "POST",
				String.format(jsonData, openId));
		if (null != jsonObject) {
			try {
				groupId = jsonObject.getInt("groupid");
			}
			catch (JSONException e) {
				groupId = -1;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error(errorCode + ":" +errorMsg);
			}
		}
		return groupId;
	}
	
	/**
	 * 发送模版消息
	 * 
	 * @param jsonStr
	 * @param accessToken
	 * @return
	 */
	public static String sendTemplateMsg(String jsonStr, String accessToken) {
		String requestUrl = WeixinUtil.TEMPLATE_MSG_URL.replace("ACCESS_TOKEN",
				accessToken);
		System.out.println(accessToken);
		// 调用微信接口
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", jsonStr);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}
	
}