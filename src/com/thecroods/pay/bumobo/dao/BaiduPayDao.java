package com.thecroods.pay.bumobo.dao;

import com.thecroods.util.DBUtil;

public class BaiduPayDao {
	
	public void setOrderInfo(String app_Id,String uin,String urecharge_Id,String extra,String recharge_Money_Code,String recharge_Money,String recharge_M,String pay_Status,String create_Time)
	{
		String sql = "insert into [thecroods].[dbo].[c_dpay] values ('"+urecharge_Id+"','"+app_Id+"','"+uin+"','"+extra+"','"+recharge_Money_Code+"','"+recharge_Money+"','"+recharge_Money+"','"+pay_Status+"','"+create_Time+"') ";
		DBUtil.update(sql);
	}
}
