package com.xiaoyu.lingdian.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.xml.XMLSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.lingdian.entity.response.TextMessage;
import com.xiaoyu.lingdian.entity.weixin.CheckModel;
import com.xiaoyu.lingdian.entity.weixin.Constant;
import com.xiaoyu.lingdian.service.MenuService;
import com.xiaoyu.lingdian.service.TokenService;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.tool.wx.GetExistAccessToken;
import com.xiaoyu.lingdian.tool.wx.WxXmlOper;

@Controller
@RequestMapping("/weixin")
public class TokenController extends BaseController {

	/**
	 * 第三方用户唯一凭证  
	 */
	public static String APPID = "";
	
	/**
	 * 第三方用户唯一凭证密钥  
	 */
	public static String APPSECRET = "";
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 获取AccessToken
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	@RequestMapping(value = "/getAccessToken", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getAccessToken(String appid, String secret) {
		if (StringUtil.isEmpty(appid)) {
			appid = APPID;
		}
		if (StringUtil.isEmpty(secret)) {
			secret = APPSECRET;
		}
		try{
			GetExistAccessToken getExistAccessToken= GetExistAccessToken.getInstance();
			return getExistAccessToken.getExistAccessToken(appid, secret);
		}catch(Exception ex){
			if(logger.isErrorEnabled()){
				logger.error("获取access_token异常", ex);
			}
			return "获取access_token异常";
		}
	}
	
	/**
	 * 开发者模式token校验
	 * 
	 * @param wxToken 开发者url后缀
	 * @param tokenModel
	 * @param response
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/{wxToken}", method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody String validate(@PathVariable("wxToken")String wxToken, CheckModel tokenModel) throws ParseException, IOException {
		return tokenService.validate(wxToken, tokenModel);
	}
	
	/**
	 * 微信进入主方法1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/{wxToken}", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String mainMethod(@PathVariable("wxToken")String wxToken, HttpServletRequest request,
			HttpServletResponse response) {
		String respMessage = "";
		try {
			// 默认返回的文本消息内容
			String respContent = "";
			// xml请求解析
			Map<String, String> requestMap = WxXmlOper.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			logger.info("fromUserName:"+fromUserName);
			logger.info("toUserName:"+toUserName);
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				if (("?").equals(content)) {
					respContent = "帮助菜单请稍等!";
				}
			}
			// 图片消息
			else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";               
			}
			// 地理位置消息
			else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "欢迎";
					textMessage.setContent(respContent);
					respMessage = WxXmlOper.textMessageToXml(textMessage);
					return respMessage;
				}
				// 取消订阅
				else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
  
                    if (eventKey.equals("1")) {  
                    	respContent = "你点击的菜单是1";
                    } else if (eventKey.equals("2")) {  
                    	respContent = "你点击的菜单是2";
                    }
				}
			}
			textMessage.setContent(respContent);
			respMessage = WxXmlOper.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		respMessage = "";
		return respMessage;
	}
	
	/**
	 * 微信进入主方法2
	 * 
	 * @exception ServletException, IOException
	 * @return
	 */
	@RequestMapping(value = "/api/2", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream is = request.getInputStream();
		// 取HTTP请求流长度
		int size = request.getContentLength();
		// 用于缓存每次读取的数据
		byte[] buffer = new byte[size];
		// 用于存放结果的数组
		byte[] xmldataByte = new byte[size];
		int count = 0;
		int rbyte = 0;
		// 循环读取
		while (count < size) {
			// 每次实际读取长度存于rbyte中
			rbyte = is.read(buffer);
			for (int i = 0; i < rbyte; i++) {
				xmldataByte[count + i] = buffer[i];
			}
			count += rbyte;
		}
		is.close();
		String requestStr = new String(xmldataByte, "UTF-8");
		try {
			XMLSerializer xmlSerializer = new XMLSerializer();
			JSONObject jsonObject = (JSONObject) xmlSerializer.read(requestStr);
			String event = jsonObject.getString("Event");
			String msgtype = jsonObject.getString("MsgType");
			if ("CLICK".equals(event) && "event".equals(msgtype)) { // 菜单click事件
				String eventkey = jsonObject.getString("EventKey");
				if ("1".equals(eventkey)) { 
					jsonObject.put("Content", "这是按钮1!");
				}
			}			
			// 创建XML
			StringBuffer revert = new StringBuffer();
			revert.append("<xml>");
			revert.append("<ToUserName><![CDATA[" + jsonObject.get("ToUserName") + "]]></ToUserName>");
			revert.append("<FromUserName><![CDATA[" + jsonObject.get("FromUserName") + "]]></FromUserName>");
			revert.append("<CreateTime>" + jsonObject.get("CreateTime") + "</CreateTime>");
			revert.append("<MsgType><![CDATA[text]]></MsgType>");
			revert.append("<Content><![CDATA[" + jsonObject.get("Content") + "]]></Content>");
			revert.append("<FuncFlag>0</FuncFlag>");
			revert.append("</xml>");
			logger.info("responseStr:" + revert.toString());
			OutputStream os = response.getOutputStream();
			os.write(revert.toString().getBytes("UTF-8"));
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
			logger.info("请求返回结果:" + result);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建菜单
	 * 
	 * @param jsonMenu
	 * @return
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	public void getMenu(String jsonMenu, HttpServletResponse response) {
		logger.info("[TokenController]:begin getMenu");
		// 调用接口获取access_token
        GetExistAccessToken getExistAccessToken= GetExistAccessToken.getInstance();
		String at = getExistAccessToken.getExistAccessToken(APPID, APPSECRET);
		String strReturn = menuService.createMenu(jsonMenu, at);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, strReturn),response);
		logger.info("[TokenController]:end getMenu");
	}
	
}