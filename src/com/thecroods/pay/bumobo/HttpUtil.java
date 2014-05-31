package com.thecroods.pay.bumobo;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 * author : tangx
 * </p>
 * <p>
 * Datetime : 2013-1-6 下午05:44:19
 * </p>
 * <p>
 * Title : HttpUtil.java
 * </p>
 * <p>
 * Description: 模拟Http get/post请求
 * </p>
 * <p>
 * Copyright : Copyright (c) 2012
 * </p>
 * <p>
 * Company : 福建博动文化传播有限公司
 * </p>
 * 
 */
public class HttpUtil {
	
	/**
	 * 创建DefaultHttpClient(设为10秒超时)
	 * @return
	 */
	private static DefaultHttpClient getHttpClient(){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//请求超时
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
		//读取超时
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
		return httpclient;
	}
	
	/**
	 * 执行参数为键值对的post请求
	 * @param url     请求地址
	 * @param kvMap   存放键值对的Map
	 * @param charset 编码（传空则默认UTF-8）
	 * @return        服务端返回的字符串
	 */
	public static String post(String url, Map<String, String> kvMap, String charset) {
		DefaultHttpClient httpclient = getHttpClient();
		String content = "";
		HttpPost post = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = kvMap.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, kvMap.get(key)));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps, charset == null?"UTF-8":charset));
			content = executeRequest(httpclient, post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpclient.getConnectionManager().shutdown();
		return content;
	}
	
	/**
	 * 执行参数为键值对的post请求
	 * @param url     请求地址
	 * @param kvMap   存放键值对的Map
	 * @return        服务端返回的字符串
	 */
	public static String post(String url, Map<String, String> kvMap){
		return post(url, kvMap, null);
	}
	
	/**
	 * 执行带HttpBody的post请求
	 * @param url             请求地址
	 * @param httpBodyString  HttpBody字符串
	 * @param charset         HttpBody采用的编码（传空则默认UTF-8）
	 * @return                服务端返回的代码（0：成功；非0：失败）
	 */
	public static String post(String url, String httpBodyString, String charset) {
		DefaultHttpClient httpclient = getHttpClient();
		String content = "";
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new StringEntity(httpBodyString, charset == null?"UTF-8":charset));
			content = executeRequest(httpclient, post); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		httpclient.getConnectionManager().shutdown();
		return content;
	}
	
	/**
	 * 执行带HttpBody的post请求
	 * @param url             请求地址
	 * @param httpBodyString  HttpBody字符串（以UTF-8编码）
	 * @return                服务端返回的代码（0：成功；非0：失败）
	 */
	public static String post(String url, String httpBodyString) {
		return post(url, httpBodyString, null);
	}


	/**
	 * 执行Http请求
	 * @param httpclient httpclient
	 * @param request    get/post请求
	 * @return 服务端返回的字符串
	 */
	private static String executeRequest(DefaultHttpClient httpclient,
			HttpUriRequest request) {
		String entityContent = "";
		try {
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			if(response.getStatusLine().getStatusCode() == 200){
				entityContent = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityContent;
	}
 
}
