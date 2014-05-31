package com.thecroods.pay.alipay.Dao;

import com.thecroods.util.DBUtil;

public class AliPayDao {
	
	/**
	 * 
	 * @param gmt_create 交易创建时间
	 * @param gmt_payment 交易付款时间
	 * @param out_trade_no 对应商户网站的订单系统中的唯一订单号，非支付宝交易号
	 * @param subject 商品名称
	 * @param trade_no 支付宝交易号
	 * @param buyer_id 卖家支付宝账号对应的支付宝唯一用户号。
	 * @param buyer_email 卖家支付宝账号，可以是email和手机号码。
	 * @param total_fee 该笔订单的总金额
	 */
	public void SetInfo(String gmt_create, String gmt_payment,
			String out_trade_no, String subject, String trade_no,
			String buyer_id, String buyer_email, String total_fee) {
		String sql = "insert into c_alipay values('" + out_trade_no + "','"
				+ subject + "','" + trade_no + "','" + buyer_id + "','"
				+ buyer_email + "'," + total_fee + "','" + gmt_create + "','"
				+ gmt_payment + "')";
		DBUtil.update(sql);
	}
	
	
}
