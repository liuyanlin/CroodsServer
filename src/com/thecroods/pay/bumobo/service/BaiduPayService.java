package com.thecroods.pay.bumobo.service;

import java.util.Map;

import com.thecroods.pay.bumobo.dao.BaiduPayDao;

public class BaiduPayService {

	public void analysisParams(Map<String, String> paraMap)
	{
		String app_Id; // 应用 id
		String uin; // 用户 ID
		String urecharge_Id; // 开发者发起购买请求时传过来的开发者自身订单 ID
		String extra;// 开发者发起购买请求时传过来的 开发者自定义字段
		String recharge_Money_Code; // USD 充值货币代码
		String recharge_Money; // 充值金额
		String recharge_M; // 充值 M 币个数
		String pay_Status; // 支付状态:0 未处理，1 成功，2 失败
		String create_Time; // 1351675829 创建时间
		
		app_Id = paraMap.get("App_Id");
		uin = paraMap.get("Uin");
		urecharge_Id = paraMap.get("Urecharge_Id");
		extra = paraMap.get("Extra");
		recharge_Money_Code = paraMap.get("Recharge_Money_Code");
		recharge_Money = paraMap.get("Recharge_Money");
		recharge_M = paraMap.get("Recharge_M");
		pay_Status = paraMap.get("Pay_Status");
		create_Time = paraMap.get("Create_Time");
		
		BaiduPayDao dao = new BaiduPayDao();
		dao.setOrderInfo(app_Id, uin, urecharge_Id, extra, recharge_Money_Code, recharge_Money, recharge_M, pay_Status, create_Time);
		
		
	}
}
