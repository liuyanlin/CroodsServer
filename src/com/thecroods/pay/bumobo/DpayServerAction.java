package com.thecroods.pay.bumobo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thecroods.util.DBUtil;


/**
 * <p>author   :  tangx</p>
 * <p>Datetime   :  2013-1-11 下午06:57:35</p>
 * <p>Title      :  DpayServerExample.java</p>
 * <p>Description:  Dpay服务端示例</p>
 * <p>Copyright  :  Copyright (c) 2012</p>
 * <p>Company    :  福建博动文化传播有限公司</p>
 *
 */
public class DpayServerAction extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		 Map<String, String> paraMap = getRequestMap(request);
		 //去除签名数据
		 if(paraMap.containsKey("Sign")){
			 paraMap.remove("Sign");
		 }
		 //获取签名信息
		 String sign = DpayUtil.encryptData(DpayUtil.Map2Str(paraMap) + Constant.APP_KEY, Constant.SIGN_TYPE);
		 //验证签名是否正确
		 if(sign.equals(getPrarmeterValue(request,"Sign",""))){
			 /**
			  * 开发者服务端逻辑处理代码
			  */
			
			 String money = paraMap.get("Recharge_Money");
			 String item = paraMap.get("Recharge_M");
			 String phone = paraMap.get("Uin");
			 String id = paraMap.get("Urecharge_Id");
			 
			String curTiem = String.valueOf(System.currentTimeMillis());
			String sql = "insert into c_shop values('"+id+"','"+ phone +"',"+money+",'"+item+"',"+1+","+curTiem+","+0+")";
			System.out.println("sql = " + sql);
				
			int i = DBUtil.update(sql);
			System.out.println("i = " + i); 
			 
			 //开发者成功处理后，显示Success
			 response.getWriter().println("Success");
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
	
	/**
	 * 获取requset的参数值
	 * @param request
	 * @param pname
	 * @param defv
	 * @return
	 */
	private String getPrarmeterValue(HttpServletRequest request, String pname, String defv) {
		String pvalue = defv;
		if (request != null) {
			String p = request.getParameter(pname);
			if (p != null && !p.equals(""))
				pvalue = p;
		}
		return pvalue;
	}
	
	/**
	 * 将Map转化为Json字符串
	 * @param map
	 * @return Json字符串
	 */
	private String Map2Json(Map<String,String> map){
		StringBuffer json = new StringBuffer();
		for (String key: map.keySet()) {
			String value = map.get(key);
			json.append(",\"" + key + "\":\"" + value + "\"");
		}
		return "{" + json.substring(1) + "}";
	}
	
}
