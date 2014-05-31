package com.thecroods.pay.alipay.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thecroods.pay.alipay.Dao.AliPayDao;
import com.thecroods.pay.alipay.util.AlipayNotify;

public class Notify extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("AlipayActionAlipayAction");
		AliPayDao dao = new AliPayDao();
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]> requestParams = req.getParameterMap();
		System.out.println("requestParams = "+requestParams);
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(req.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
		//支付宝交易号
		String trade_no = new String(req.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(req.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//商品名称
		String subject = new String(req.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8"); 
		
		//卖家支付宝账号对应的支付宝唯一用户号。
		String buyer_id = new String(req.getParameter("buyer_id").getBytes("ISO-8859-1"),"UTF-8"); 
		
		//卖家支付宝账号，可以是email和手机号码。
		String buyer_email = new String(req.getParameter("buyer_email").getBytes("ISO-8859-1"),"UTF-8"); 
		
		//该笔订单的总金额
		String total_fee = new String(req.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易创建时间
		String gmt_create = new String(req.getParameter("gmt_create").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易付款时间
		String gmt_payment = new String(req.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8"); 


		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			System.out.println("验证成功");
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				System.out.println("TRADE_FINISHE");
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//该种交易状态只在两种情况下出现
				//1、开通了普通即时到账，买家付款成功后。
				//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				
				
				//保存交易信息
				dao.SetInfo(gmt_create, gmt_payment, out_trade_no, subject,
						trade_no, buyer_id, buyer_email, total_fee);
				
			} else if (trade_status.equals("TRADE_SUCCESS")){
				System.out.println("TRADE_SUCCESS");
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			resp.getWriter().print("success".getBytes("utf-8"));	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			System.out.println("验证失败");
			resp.getWriter().print("fail".getBytes("utf-8"));
		}
	}
	
}
