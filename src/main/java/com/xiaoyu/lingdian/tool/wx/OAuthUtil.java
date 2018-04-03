package com.xiaoyu.lingdian.tool.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;

public class OAuthUtil {

	protected final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");
	
	/**
	 * OAuth2.0引导关注者打开 用户同意授权，获取code页面url
	 * 
	 * scope的设置为：snsapi_base（不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面) 
	 * redirect_uri：授权后重定向的回调链接地址，请使用urlencode对链接进行处理
	 * 
	 * 1. 用户关注微信公众账号。
	 * 2. 微信公众账号提供用户请求授权页面URL。
	 * 3. 用户点击授权页面URL，将向服务器发起请求
	 * 4. 服务器询问用户是否同意授权给微信公众账号(scope为snsapi_base时无此步骤)
	 * 5. 用户同意(scope为snsapi_base时无此步骤)
	 * 6. 服务器将CODE通过回调传给微信公众账号
	 * 7. 微信公众账号获得CODE
	 * 8. 微信公众账号通过CODE向服务器请求Access Token
	 * 9. 服务器返回Access_Token和OpenID给微信公众账号
	 * 10. 微信公众账号通过Access Token向服务器请求用户信息(scope为snsapi_base时无此步骤)
	 * 11. 服务器将用户信息回送给微信公众账号(scope为snsapi_base时无此步骤)
	 */
	public final static String FANS_GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	// OAuth2.0通过code换取网页授权access_token和openid
	protected final static String OAUTH2_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// OAuth2.0拉取用户信息(需scope为 snsapi_userinfo)
	protected final static String OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	// 根据OpenID机制获得用户详细信息
	public final static String OPENID_INFO="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 
	 * @param appid
	 * @param redirect_uri 授权后重定向的回调链接地址
	 * @param scope 应用授权作用域
	 * @param state 重定向后会带上state参数
	 * @return
	 */
	public static void getCode(String appid, String redirect_uri, String scope, String state){
		String requestUrl = FANS_GET_CODE.replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("SCOPE", scope).replace("STATE", state);
		httpRequest(requestUrl, "GET", null);
		return;
	}
	
	public static String getToken(String appid,String appsecret, String grantType, String code){
		String requestUrl = OAUTH2_ACCESSTOKEN_URL.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code).replace("authorization_code", grantType);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		return jsonObject.toString();
	}
	
	public static String getUserDetail(String access_token, String openid){
		String requestUrl = OAUTH2_USERINFO_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		return jsonObject.toString();
	}
	
	public static String getUserDetailByOpenID(String access_token, String openid){
		String requestUrl = OPENID_INFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=dfbNKW55fVY6b35OKvhyXJkWzUoytZzH2Qy1-BcEFcrETmH64Ty4kIah2z1N0OsNmPlMc3bBdVZHyum_QP4fzGvP8oae8KzCbXAkLz-BWtoTSNgAHAMLG&openid=ozfbQvwzLuneb_xmgjYV1XIywP_4&lang=zh_CN";
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		return jsonObject.toString();
	}

	/** 
     * 使用Get方式获取数据 
     *  
     * @param url 
     * @param charset 
     * @return 
     */  
    public String sendGet(String url, String charset) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection connection = realUrl.openConnection();  
            // 设置通用的请求属性  
            connection.setRequestProperty("accept", "*/*");  
            connection.setRequestProperty("connection", "Keep-Alive");  
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 建立实际的连接  
            connection.connect();  
            // 定义 BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {
            logger.info("发送GET请求出现异常！" + e);
        }  
        // 使用finally块来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (Exception e2) {  
                e2.printStackTrace();  
            }  
        }  
        return result;  
    }  
  
    /**  
     * POST请求，字符串形式数据  
     * 
     * @param url 请求地址  
     * @param param 请求数据  
     * @param charset 编码方式  
     */  
    public String sendPostUrl(String url, String param, String charset) {   
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {
            logger.info("发送POST请求出现异常！" + e);
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }
    
    /**  
     * POST请求，Map形式数据 
     *  
     * @param url 请求地址  
     * @param param 请求数据  
     * @param charset 编码方式  
     */  
    public String sendPost(String url, Map<String, String> param, String charset) {    
        StringBuffer buffer = new StringBuffer(); 
        buffer.append("<xml>");
        if (param != null && !param.isEmpty()) {  
            for (Map.Entry<String, String> entry : param.entrySet()) {  
            	if(entry.getKey().equals("attach") || entry.getKey().equals("body") || entry.getKey().equals("sign")){
            		buffer.append("<"+entry.getKey()+">");
            		buffer.append("<![CDATA["+entry.getValue()+"]]>");
            		buffer.append("</"+entry.getKey()+">");
            	}else{
            		buffer.append("<"+entry.getKey()+">");
            		buffer.append(entry.getValue());
            		buffer.append("</"+entry.getKey()+">");
            	}
            }  
        }  
        buffer.append("</xml>");
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(buffer);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
        	logger.info("发送POST请求出现异常！" + e);
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new WxX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
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
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return jsonObject;
	}

}