package com.thecroods.pay.bumobo;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>author   :  tangx</p>
 * <p>Datetime   :  2013-1-11 下午05:03:56</p>
 * <p>Title      :  DpayClientExample.java</p>
 * <p>Description:  Dpay服务端示例</p>
 * <p>Copyright  :  Copyright (c) 2012</p>
 * <p>Company    :  福建博动文化传播有限公司</p>
 *
 */
public class DpayClientExample {


	//查询支付结果   接口 1
	public String getPayResult() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("AppId", Constant.APP_ID);
		data.put("Act", "1");
		data.put("Version", Constant.VERSION);
		data.put("Recharge_number", "D20121116175942000009001452502207");//与Urecharge_id二选一
//		data.put("Urecharge_id", "");//与Recharge_number二选一
		return DpayUtil.postData(data, Constant.APP_KEY, Constant.SIGN_TYPE, Constant.URL);
	}

	//获取用户列表   接口 2
	public String getUserList() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("AppId", Constant.APP_ID);
		data.put("Act", "2");
		data.put("Page_Size", "1"); 
		data.put("PageNo", "10"); 
		data.put("Version", Constant.VERSION);
		return DpayUtil.postData(data, Constant.APP_KEY, Constant.SIGN_TYPE, Constant.URL);
	}

	//检查用户是否登录  接口 3
	public String isUserLogin() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("AppId", Constant.APP_ID);
		data.put("Act", "3");
		data.put("Uin", "1326");
		data.put("SessionId", "d891b6f03f361128b10c69d440c92c34");
		data.put("Version", Constant.VERSION);
		return DpayUtil.postData(data, Constant.APP_KEY, Constant.SIGN_TYPE, Constant.URL);
	}
	
	//查询购买结果  接口 4
	public String getBuyResult() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("AppId", Constant.APP_ID);
		data.put("Act", "4");
		data.put("Extra", "aaaa");
		data.put("Version", Constant.VERSION);
		return DpayUtil.postData(data, Constant.APP_KEY, Constant.SIGN_TYPE, Constant.URL);
	}
	
	public static void main(String[] args){
		DpayClientExample example = new DpayClientExample();
		example.getPayResult();
		example.getBuyResult();
		example.getUserList();
		example.isUserLogin();
	}
}
