package com.xiaoyu.lingdian.entity.weixin;

import org.apache.commons.httpclient.NameValuePair;

public class HttpRequest {

    public static final String METHOD_GET = "GET";

    public static final String METHOD_POST = "POST";

    /**
     * 待请求的url
     */
    private String url = null;

    /**
     * 默认的请求方式
     */
    private String method = METHOD_POST;

    private int timeout = 0;

    private int connectionTimeout = 0;

    /**
     * Post方式请求时组装好的参数值对
     */
    private NameValuePair[] parameters = null;

    /**
     * Get方式请求时对应的参数
     */
    private String queryString = null;

    /**
     * 默认的请求编码方式
     */
    private String charset = "GBK";

    /**
     * 请求发起方的ip地址
     */
    private String clientIp;

    /**
     * 请求返回的方式
     */
    private HttpResultType resultType = HttpResultType.BYTES;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public NameValuePair[] getParameters() {
		return parameters;
	}

	public void setParameters(NameValuePair[] parameters) {
		this.parameters = parameters;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public HttpResultType getResultType() {
		return resultType;
	}

	public void setResultType(HttpResultType resultType) {
		this.resultType = resultType;
	}

	public static String getMethodGet() {
		return METHOD_GET;
	}

	public static String getMethodPost() {
		return METHOD_POST;
	}

}