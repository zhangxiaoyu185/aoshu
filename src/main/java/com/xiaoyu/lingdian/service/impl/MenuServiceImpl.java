package com.xiaoyu.lingdian.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.lingdian.entity.menu.Menu;
import com.xiaoyu.lingdian.service.MenuService;
import com.xiaoyu.lingdian.tool.http.HttpClientWxUtil;

@Service
public class MenuServiceImpl implements MenuService {

	private static Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 创建菜单
	 * 
	 * @param jsonMenu 例如 {\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"}]}
	 *            菜单JSON
	 * @param accessToken
	 *            有效的access_token
	 * @return success表示成功，其他值表示失败
	 */
	public String createMenu(String jsonMenu, String accessToken) {
		String result = "success";
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = HttpClientWxUtil.httpRequest(url, "POST",
				jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getIntValue("errcode")) {
				result = "创建菜单失败 errcode:"+jsonObject.getIntValue("errcode")
						+",errmsg:{}"+jsonObject.getString("errmsg");
				log.error("创建菜单失败 errcode:{} errmsg:{}",
						jsonObject.getIntValue("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return result;
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 * @param accessToken
	 * @return
	 */
	public static int createMenu(Menu menu, String accessToken) {
		log.info("[createMenu]:start createMenu.");
		int result = 0;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		log.info("[createMenu]:menu_url replace accessToken,url is:" + url);
		String jsonMenu = JSONObject.toJSONString(menu);
		JSONObject jsonObject = HttpClientWxUtil.httpRequest(url, "POST",
				jsonMenu);

		if (jsonObject != null) {
			if (jsonObject.getInteger("errcode") != 0) {
				result = jsonObject.getInteger("errcode");
				log.error(
						"[createMenu]:create menu fail, errcode:{} errmsg:{}",
						jsonObject.getInteger("errcode"),
						jsonObject.getString("errmsg"));
			} else {
				log.info("[createMenu]:create menu success.");
			}
		}
		log.info("[createMenu]:end createMenu.");
		return result;
	}
	
}