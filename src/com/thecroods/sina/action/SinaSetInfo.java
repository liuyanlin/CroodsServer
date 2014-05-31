package com.thecroods.sina.action;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thecroods.util.DBUtil;

public class SinaSetInfo extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp){
		// TODO Auto-generated method stub
		try {
			System.out.println("SinaSetInfo Start");
			
			String uid = req.getParameter("uid");
			String name =URLDecoder.decode(req.getParameter("name"), "UTF-8");
			String pic = req.getParameter("pic");
			int starts = Integer.valueOf(req.getParameter("starts"));
			int pets = Integer.valueOf(req.getParameter("pets"));
			int lv = Integer.valueOf(req.getParameter("level"));
			int flag = 0;
			
			System.out.println("uid = "+ uid);
			System.out.println("name = "+ name);
			System.out.println("pic = "+ pic);
			System.out.println("starts = "+ starts);
			System.out.println("pets = "+ pets);
			System.out.println("lv = "+ lv);
			System.out.println("flag = "+ flag);

			
			String strId = "SELECT * FROM [thecroods].[dbo].[c_sinainfo] where uid ='" + uid+ "' and del = 0";
			ResultSet rs = DBUtil.get(strId);
			if(rs.next())
				flag = 0;
			else 
				flag = 1;
			
			int state = 0;//执行是否成功 0=失败 1=成功
			if (flag == 0) { //如果存在该id 则更新信息
				String update = "update [thecroods].[dbo].[c_sinainfo] set name = '" +name
						+ "' ,pic = '" + pic + "' ,starts = " + starts
						+ ", pets = " + pets + ",lv = " + lv
						+ " where  uid = '" + uid + "' and del = 0";
				System.out.println("update sql = " + update);
				state = DBUtil.update(update);
			}else{//不存在则插入
				String insert = "insert into [thecroods].[dbo].[c_sinainfo] values('" + uid + "','"
						+ name + "','" + pic + "'," + starts + "," + pets + ","
						+ lv + ",0)";
				System.out.println("insert sql = " + insert);
				state = DBUtil.update(insert);
			}
			
			OutputStream out = resp.getOutputStream();
			out.write(state);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SinaSetInfo Exception" + e.toString());
		}
	}

}
