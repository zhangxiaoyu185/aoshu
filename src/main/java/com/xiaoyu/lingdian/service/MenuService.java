package com.xiaoyu.lingdian.service;

public interface MenuService {

	/**
	 * 创建菜单
	 * 
	 * @param jsonMenu 例如 {\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"}]}
	 *            菜单JSON
	 * @param accessToken
	 *            有效的access_token
	 * @return success表示成功，其他值表示失败
	 */
	public String createMenu(String jsonMenu, String accessToken);

}