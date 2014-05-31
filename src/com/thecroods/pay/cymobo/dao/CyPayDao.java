package com.thecroods.pay.cymobo.dao;

import com.thecroods.util.DBUtil;

public class CyPayDao {

/**
 * 畅游支付数据	
 * @param orderId
 * @param cypayOrderId
 * @param categoryType
 * @param productName
 * @param itemValue
 * @param country
 * @param currency
 * @param amount
 * @param isCard
 * @param cardAmount
 * @param status
 * @param channel
 * @param orderTime
 */
	public void SetOrderInfo(String orderId,String cypayOrderId, String categoryType,String productName,String itemValue,String country,String currency,String amount,String isCard,String cardAmount,String status,String channel,String orderTime){
		String sql = "insert into [thecroods].[dbo].[c_cypay] values ('"+orderId+"','"+cypayOrderId+"','"+categoryType+"','"+productName+"','"+itemValue+"','"+country+"','"+currency+"','"+amount+"','"+isCard+"','"+cardAmount+"','"+status+"','"+channel+"','"+orderTime+"')";
		DBUtil.update(sql);		
	}
}
