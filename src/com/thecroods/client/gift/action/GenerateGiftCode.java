package com.thecroods.client.gift.action;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import com.thecroods.client.gift.bean.CodeInfo;
import com.thecroods.client.gift.dao.GiftDao;

public class GenerateGiftCode extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
			/**
			 * 
			 * Json示例：
				{
				    "id": "18",
				    "type": "测试大礼包2",
				    "about": "text2",
				    "codes":["123456","123456","123456"],
				    "gift": "101|200;105|2"
				}
			 */
			
			System.out.println("生成礼物兑换码... ...");
			
			CodeInfo info = new CodeInfo();
			
			String param = req.getParameter("param");
			
			JSONObject json = (JSONObject) JSONValue.parse(param);
			
			info.setId(json.get("id").toString());
			info.setType(new String(json.get("type").toString().getBytes("ISO-8859-1"), "utf-8"));
			info.setAbout(new String(json.get("about").toString().getBytes("ISO-8859-1"), "utf-8"));
			info.setGift(new String(json.get("gift").toString().getBytes("ISO-8859-1"), "utf-8"));
			
			JSONArray array = (JSONArray) json.get("codes");
			Object[] obj = array.toArray();
			String[] str = new String[obj.length];
			for (int i = 0; i < obj.length; i++) {
				str[i] = obj[i].toString();
			}
			info.setCodes(Arrays.asList(str));
			GiftDao dao = new GiftDao();
			dao.setCodeList(info);
			dao.setGiftinfo(info);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
	}
}
