package com.thecroods.pay.bumobo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Hex;


/**
 * <p>
 * author : tangx
 * </p>
 * <p>
 * Datetime : 2013-1-11 下午04:56:24
 * </p>
 * <p>
 * Title : DpayUtil.java
 * </p>
 * <p>
 * Description: Dpay接口工具
 * </p>
 * <p>
 * Copyright : Copyright (c) 2012
 * </p>
 * <p>
 * Company : 福建博动文化传播有限公司
 * </p>
 * 
 */
public class DpayUtil {

	
	/**
	 * 获取Dpay服务端返回的信息
	 * @param data     存放键值对的Map  
	 * @param key      appKey
	 * @param signType 签名方式 
	 * @param url	        接口地址
	 * @return 服务端返回的字符串
	 */
	public static String postData(Map<String, String> data, String key,
			String signType, String url){
		String sign = encryptData(Map2Str(data) + key, signType);
		data.put("Sign", sign);
		return HttpUtil.post(url, data);
	}
	
	/**
	 * 将Map按key排序后转化为请求字符串
	 * @param data 存放键值对的Map  
	 * @return 请求字符串
	 */
	public static String Map2Str(Map<String, String> data) {
		TreeMap<String, String> treemap = new TreeMap<String, String>(data);  
		StringBuffer buffer = new StringBuffer("");
		for (String key: treemap.keySet()) {
			String value = treemap.get(key);
			buffer.append("&" + key + "=" + value);
		}
		if(!buffer.toString().equals("")){
			return buffer.toString().substring(1);
		}else{
			return "";
		}
	}
	
	/**
	 * 加密
	 * @param data     要加密的字符串
	 * @param signType 加密方式
	 * @return 加密后的字符串
	 */
	public static String encryptData(String data, String signType) {
		System.out.println("加密前:" + data);
		String ret = "";
		try {
			MessageDigest sha = MessageDigest.getInstance(signType);
			ret = new String(Hex.encodeHex(sha.digest(data.getBytes())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("加密后:" + ret);
		return ret;
	}

}
