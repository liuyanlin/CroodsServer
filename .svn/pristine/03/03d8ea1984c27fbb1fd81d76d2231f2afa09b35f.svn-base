package com.thecroods.client.gift.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thecroods.client.gift.dao.GiftDao;
import com.thecroods.client.gift.server.GiftServer;

public class GetGiftAction extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);

		PrintWriter writer = resp.getWriter();
		
		String clientCode = req.getParameter("code");
		GiftServer server = new GiftServer();
		GiftDao dao = new GiftDao();
		
		
		//查找是否存在此code 不存在给客户端返回：-1
		int existCode = dao.findCode(clientCode);
		if(existCode == 0){//code 不存在
			writer.print(-2);
		}
		//此code是否过期，过期给客户端返回：-1
		else if(server.CodeExpired(clientCode)){
			writer.print(-1);
		}
		//该code 已被使用  客户端返回：0
		else if(server.isUsed(clientCode)){
			writer.print(0);
		}
		//返回code的礼物
		else {
			String gift = dao.findGiftbyCode(clientCode);
			dao.setUsed(clientCode, 1);
			writer.print(gift);
		}
	}
}
