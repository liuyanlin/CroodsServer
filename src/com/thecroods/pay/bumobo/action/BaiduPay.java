package com.thecroods.pay.bumobo.action;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thecroods.pay.bumobo.service.BaiduPayService;
import com.thecroods.pay.bumobo.util.DpayUtil;


/**
 * <p>author   :  tangx</p>
 * <p>Datetime   :  2013-1-11 下午06:57:35</p>
 * <p>Title      :  DpayServerExample.java</p>
 * <p>Description:  Dpay服务端示例</p>
 * <p>Copyright  :  Copyright (c) 2012</p>
 * <p>Company    :  福建博动文化传播有限公司</p>
 *
 */
public class BaiduPay extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 Map<String, String> paraMap = DpayUtil.getRequestMap(request);
		 //去除签名数据
		 if(paraMap.containsKey("Sign")){
			 paraMap.remove("Sign");
		 }
		 //获取签名信息
		 String sign = DpayUtil.encryptData(DpayUtil.Map2Str(paraMap) + BaiduConfig.APP_KEY, BaiduConfig.SIGN_TYPE);
		 //验证签名是否正确
		 if(sign.equals(DpayUtil.getPrarmeterValue(request,"Sign",""))){
			 /**
			  * 服务端逻辑处理代码
			  */
			 BaiduPayService service = new BaiduPayService();
			 
			 service.analysisParams(paraMap);
			 
			 
			 //开发者成功处理后，显示Success
			 response.getWriter().println("Success");
		 }
	}
	
}
