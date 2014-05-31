package com.thecroods.pay.bumobo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import com.thecroods.pay.bumobo.util.DpayUtil;

public class BaiduLogin extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		Map<String, String> data = new HashMap<String, String>();
		data.put("AppId", BaiduConfig.APP_ID);
		data.put("Act", "3");
		data.put("Uin", req.getParameter("Uin"));
		data.put("SessionId", req.getParameter("SessionId"));
		data.put("Version",  BaiduConfig.VERSION);
		
		String result = DpayUtil.postData(data, BaiduConfig.APP_KEY, BaiduConfig.SIGN_TYPE, BaiduConfig.URL);		
		JSONObject json = (JSONObject) JSONValue.parse(result);
		String error_code = (String) json.get("Error_Code");

		//（0 有效，1 无效）
		resp.getWriter().println(error_code);
	}

	
}
