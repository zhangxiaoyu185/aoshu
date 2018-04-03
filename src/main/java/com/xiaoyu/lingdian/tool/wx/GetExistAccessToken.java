package com.xiaoyu.lingdian.tool.wx;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.entity.weixin.AccessTokenModel;
import com.xiaoyu.lingdian.entity.weixin.Constant;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.IOUtil;
import com.xiaoyu.lingdian.tool.http.HttpClientUtil;

/**
 * 获取accessToken
 */
public class GetExistAccessToken {

	// 获取access_token的接口地址（GET） 限200（次/天）  
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token";
		
    //定义一个私有的静态全局变量来保存该类的唯一实例
    private static GetExistAccessToken getExistAccessToken;

    private GetExistAccessToken() {}

    public static GetExistAccessToken getInstance() {
        //单例模式
        if ( getExistAccessToken == null) {
        	getExistAccessToken = new GetExistAccessToken();
        }
        return getExistAccessToken;
    }

   public String getExistAccessToken(String appid, String secret) {
	   Map<String, String> map = new HashMap<String, String>();
       // 读取文件中的accessToken数据
       String filepath = GetExistAccessToken.class.getResource("").getPath()+"accessToken.txt";       
       System.out.println("existAccessTokenfilepath:" + filepath);
       if(filepath.startsWith("/"))
    	   filepath = filepath.substring(1, filepath.length());
       try {          
    	   map = IOUtil.readToMap(filepath);
    	   String lastTime = map.get("lastTime");
    	   String accessToken = map.get("accessToken");
           Date now = new Date();
           Date accessExpires = DateUtil.parseDate(lastTime, "yyyy-MM-dd HH:mm:ss");
           if (now.getTime() > accessExpires.getTime()) {
               accessToken = getAccessToken(appid, secret);
               accessExpires = new Date(now.getTime() + 7000000); //默认7200000秒
               String nextTime = DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", accessExpires);
               String content = "accessToken="+accessToken+"\r\nlastTime="+nextTime;
               IOUtil.write(filepath, content);
           }
           return accessToken;
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return "";
   }

	/**
	 * 获取access_token
	 * 
	 * @param appid 微信appid
	 * @param secret 微信secret
	 * @return
	 */
	public static String getAccessToken(String appid, String secret) {
		if (StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret))
			return "请输入appid或appsecret";
		String accessToken = "";
		if (("").equals(appid)) {
			appid = Constant.APP_ID;
		}
		if (("").equals(secret)) {
			secret = Constant.APP_SECRET;
		}
		AccessTokenModel accessTokenModel = JSON.parseObject(
				HttpClientUtil.sendGetSSLRequest(access_token_url
						+ "?grant_type=client_credential&appid=" + appid
						+ "&secret=" + secret, null), AccessTokenModel.class);
		if (StringUtils.isEmpty(accessTokenModel.getAccess_token())) {
			return null;
		} else {
			accessToken = accessTokenModel.getAccess_token();
		}
		return accessToken;
	}
	
}