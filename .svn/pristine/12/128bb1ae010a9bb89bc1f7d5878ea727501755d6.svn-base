package com.thecroods.sina.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.thecroods.sina.Dao.SinaDao;

public class SinaGetInfo extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			System.out.println("SinaGetInfo Start");
			SinaDao dao = new SinaDao();
			String result ="";//返回的序列化后的json
			String strJson = req.getParameter("strJson");
			System.out.println("接受的JSON = "+ strJson);
//			if(strJson!=null)
//				strJson = new String(strJson); 
			
			List<String> ids = new ArrayList<String>();//相互关注好友列表
			//解析json	
			JSONObject jo = new JSONObject(strJson);
			JSONArray ja = jo.getJSONArray("ids");
			
			for (int i = 0; i < ja.length(); i++) {
				ids.add(String.valueOf(ja.get(i)));
			}
			//获得游戏中的好友id
			ids = dao.getFriendInGame(ids);
			System.out.println(" 游戏好友个数： "+ ids);
			//好友的starts 和pets 
			for (int i = 0; i < ids.size(); i++) {
//				//拼装返回json
				 if(i!=0) result += ";";
				 AccountInfo info = dao.getFriendsInfoForId(ids.get(i));
				 result+=info.getName()+"$"+info.getPic()+"$"+info.getStarts()+"$"+info.getPets()+"$"+info.getLevel();
			}

			
			//json 序列化xml
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			XMLEncoder xmlEncoder = new XMLEncoder(baos);
//			xmlEncoder.writeObject(friends);
//			xmlEncoder.close();
//			reJson = baos.toString();
			System.out.println("reJson = " + result);
			resp.setHeader("Content-type","text/html;charset=UTF-8");
			OutputStream out = resp.getOutputStream();
			out.write(result.getBytes("UTF-8"));
			out.flush();
			out.close();

		} catch (Exception e) {
			System.out.println("SinaAuthInfoAction Exception : "+e.toString());
		}

	}

	
}
