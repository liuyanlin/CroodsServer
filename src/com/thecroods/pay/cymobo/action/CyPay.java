package com.thecroods.pay.cymobo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thecroods.pay.bumobo.action.BaiduConfig;
import com.thecroods.pay.bumobo.util.DpayUtil;
import com.thecroods.pay.cymobo.dao.CyPayDao;

public class CyPay extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		
//		orderId：返回 APP 客户端生成唯一订单号，见“4.2.1CYPAY 支付接口  介绍“的参数 orderId
//		cypayOrderId：CYPaySDK 生成的订单号，在 CYPaySDK 中唯一
//		categoryType：商品类型，如“GOLD，VIP，ITEM“
//		productName：商品名称，如“1000 金币”
//		itemValue：商品自定义值
//		country：国家信息
//		currency：货币类型 
//		amount：付款金额
//		isCard：是否为点卡支付
//		cardAmount：点卡支付金额
//		status：2  支付成功；4  退款
//		channel：支付渠道
//		orderTime：订单下单时间
//		signature：MD5 校验值， // MD5(orderId + cypayOrderId + amount + status + currency + channel + secret)
		
		Map<String, String> paraMap = getRequestMap(req);
		 //去除签名数据
		 if(paraMap.containsKey("Sign")){
			 paraMap.remove("Sign");
		 }
		 //获取签名信息
		 
		 Map<String, String> tempMap = new HashMap<>();
		 tempMap.put("orderId", paraMap.get("orderId"));
		 tempMap.put("cypayOrderId", paraMap.get("cypayOrderId"));
		 tempMap.put("amount", paraMap.get("amount"));
		 tempMap.put("status", paraMap.get("status"));
		 tempMap.put("currency", paraMap.get("currency"));
		 tempMap.put("channel", paraMap.get("channel"));
		 
		 String sign = DpayUtil.encryptData(DpayUtil.Map2Str(tempMap) + CyPayConfig.SECRET, BaiduConfig.SIGN_TYPE);
		 //验证签名是否正确
		 if(sign.equals(DpayUtil.getPrarmeterValue(req,"Sign",""))){
			 /**
			  * 服务端逻辑处理代码
			  */
			 CyPayDao dao = new CyPayDao();
			 dao.SetOrderInfo(paraMap.get("orderId"), paraMap.get("cypayOrderId"), paraMap.get("categoryType"), paraMap.get("productName"), paraMap.get("itemValue"), paraMap.get("country"), paraMap.get("currency"), paraMap.get("amount"), paraMap.get("isCard"), paraMap.get("cardAmount"), paraMap.get("status"), paraMap.get("channel"), paraMap.get("orderTime"));
			 
			 
			 //开发者成功处理后，显示Success
			 resp.getWriter().println("SUCCESS");
		 }		
		
	}
	
	/**
	 * 将参数值封装到map中 
	 * @param request
	 * @return
	 */
	private Map<String, String> getRequestMap(HttpServletRequest request) {
		Map<String, String[]> paraMap = request.getParameterMap();
		Map<String, String> retMap = new HashMap<String, String>();
		for (String key: paraMap.keySet()) {
			String[] value = (String[]) paraMap.get(key);
			if(value[0] == null || "".equals(value[0])){
				retMap.put(key, "");
			}else{
				retMap.put(key, value[0]);
			}
		}
		return retMap;
	}
}
