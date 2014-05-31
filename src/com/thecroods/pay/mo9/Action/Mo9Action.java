package com.thecroods.pay.mo9.Action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mokredit.payment.Md5Encrypt;
import com.thecroods.util.DBUtil;

public class Mo9Action extends HttpServlet{

	DBUtil db = new DBUtil();
	
//	Logs log = Logs.getLogs(); 
	
	//private static Logger logger = Logger.getLogger(Mo9Action.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		
		ServletOutputStream out = resp.getOutputStream();
		// TODO Auto-generated method stub
		/***MO9的notify请求统一采用UTF-8编码，请在读取参数前指定字符集，以免出现乱码情况*/
		req.setCharacterEncoding("UTF-8");
		/**从HTTP请求中获取Notify参数*/
		Map<String,String> params = getParams(req);
		/**签名*/
		String sign = params.get("sign").toString();
		/**订单交易状态*/ 
		String status=params.get("trade_status").toString();
		/**invoice*/
		String invoice=params.get("invoice").toString();
		
		
		/**获得数据存储信息*/
		String id = req.getParameter("invoice");//订单号
		String phone = params.get("payer_id").toString(); 
		float money = Float.parseFloat(params.get("amount").toString()) ;
		String item =  params.get("item_name").toString();
		
		/**
		 *签名验证，请使用mo9.com提供给你的MD5密钥执行签名验证.
		 */	

		List<String> keys1 = new ArrayList<String>(params.keySet());
		System.out.println("keys1.size()" + keys1.size());
		Collections.sort(keys1);
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < keys1.size(); i++) {
			String key = (String) keys1.get(i);
			String value = "";
			value = (String) params.get(key);
			if (key == null || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")){
				continue;
			}
			content.append(key + "=" + value + "&");
		}
		String linkedContent = content.toString().substring(0, content.lastIndexOf("&"));
		String signcontent = linkedContent + Constants.PRIVATE_KEY;	
		//System.out.println("BeforeMD5:"+signcontent);
		System.out.println("BeforeMD5   " +  signcontent);
		try {
			System.out.println("params  "+ params);
			System.out.println("Tosign " + Md5Encrypt.sign(params, Constants.PRIVATE_KEY));
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("sign " + sign);
		
		if(sign.equalsIgnoreCase(Md5Encrypt.sign(params, Constants.PRIVATE_KEY)))
		{/***签名验证通过*/
			System.out.println("sign");
			if("TRADE_SUCCESS".equals(status))
			{/**交易状态成功.*/
				System.out.println("TRADE_SUCCESS");
				if(!hasProcessed(invoice))
				{
					System.out.println("hasProcessed");
					/***请在这里添加你的业务处理代码*/
					String curTiem = String.valueOf(System.currentTimeMillis());
					String sql = "insert into c_shop values('"+id+"','"+ phone +"',"+money+",'"+item+"',"+1+","+curTiem+","+0+")";
					System.out.println("sql = " + sql);
//					Logs log=Logs.getLogs();
					//log.loger.debug("sql == " + sql);
					
					int i = db.update(sql);
					System.out.println("i = " + i);
					//log.loger.debug("db.update(sql) === "+i);
					//db.update(sql);
					System.out.println("业务处理完成.invoice:"+invoice);
					/**返回mo9告知交易已经处理成功*/
					out.print("OK");
				}
				else
				{
					/**返回mo9告知交易已经处理成功，不需要再重复发送*/
					out.print("OK");
				}	
			}
			else
			{/**交易状态失败.当前版本mo9，不会notify给你任何失败交易.*/
				System.out.println("交易失败.invoice:"+invoice);
			}
		}
		else
		{/**签名验证不通过*/
			out.print("ILLEGAL SIGN");
		}
		
		out.flush();
	}

	/**CTO
	 * 从HTTP请求中提取NOTIFY参数，
	 */
	private Map<String,String> getParams(ServletRequest req)
	{
		Map<String,String> payParams= new HashMap<String,String>();
		payParams.put("pay_to_email",req.getParameter("pay_to_email"));
		payParams.put("payer_id", req.getParameter("payer_id"));
		payParams.put("trade_no",req.getParameter("trade_no"));
		payParams.put("trade_status",req.getParameter("trade_status"));
		payParams.put("sign",req.getParameter("sign"));
		payParams.put("amount",req.getParameter("amount"));	
		payParams.put("currency", req.getParameter("currency"));
		payParams.put("req_amount",req.getParameter("req_amount"));
		payParams.put("req_currency",req.getParameter("req_currency"));
		payParams.put("item_name",req.getParameter("item_name"));
		payParams.put("lc",req.getParameter("lc"));
		payParams.put("invoice",req.getParameter("invoice"));
		/**extra_param为可选字段,如果你的支付请求中不包含该参数，则不需要提取.*/
		//payParams.put("extra_param",req.getParameter("extra_param"));
		/**app_id为可选字段,如果你的支付请求中不包含该参数，则不需要提取.*/
		payParams.put("app_id",req.getParameter("app_id"));
		return payParams;
	}

	/**
	 * 根据Invoice验证，当前notify请求是否已经被你的系统处理
	 */
	private boolean hasProcessed(String invoice)
	{
		try {		
			String sql = "select * from c_shop where id = " + invoice;
			ResultSet rs = db.get(sql);
			int column = rs.getMetaData().getColumnCount();
			
			if(column == 0)
				return true;
			else 
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}	
}
