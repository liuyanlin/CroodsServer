/**
 * 用户接口
   * 
   1.code换token和用户信息
   * http://www.example.com/qihoo/user?code=182717763c335a107b5d25a325ebef3c14a8e6b8966327d17&app_key=8689e00460eabb1e66277eb4232fde6f&scope=pay&act=get_info
   * 
   * {
		"access_token": "182717763f6d2887698f56675124513ac78f091855f8d0ddb",
		"user": {
			"id": "182717763",
			"name": "adamsunyu",
			"avatar": "http://u1.qhimg.com/qhimg/quc/48_48/29/02/73/290273aq114f3.92d56f.jpg?f=8689e00460eabb1e66277eb4232fde6f"
		}
	}
	* 
  2.token换用户信息
  * http://www.example.com/qihoo/user?token=182717763bbe69f2b71cf85047dea90f5f54e0883cc060d8c&app_key=8689e00460eabb1e66277eb4232fde6f&act=get_user
  * 
  * {

    "id": "182717763",
    "name": "adamsunyu",
    "avatar": "http://u1.qhimg.com/qhimg/quc/48_48/29/02/73/290273aq114f3.92d56f.jpg?f=8689e00460eabb1e66277eb4232fde6f"
	}

* 3.从token中获取相关信息
* http://www.example.com/qihoo/user?token=182717763bbe69f2b71cf85047dea90f5f54e0883cc060d8c&app_key=8689e00460eabb1e66277eb4232fde6f&act=get_token_info
* {
    "expres_in": "35965",
    "expires_at": "1370810343",
    "user_id": "182717763",
    "app_key": "8689e00460eabb1e66277eb4232fde6f"
}
 */
package com.thecroods.pay.qihoo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thecroods.pay.qihoo.action.PayApp;
import com.thecroods.pay.qihoo.dao.QihooUserInfo;
import com.thecroods.pay.qihoo.dao.TokenInfo;
import com.thecroods.pay.qihoo.msdk.QException;
import com.thecroods.pay.qihoo.msdk.QOAuth2;


public class QihooUser extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -976540703339976189L;

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");

        PrintWriter _writer = response.getWriter();
		try {
			String act = request.getParameter("act");
            
            QOAuth2 auth = new QOAuth2();
            		
			auth.Init(PayApp._appKey, PayApp._appSecret);
						
			String resp = "";			
			Map<String, Object> obj = new HashMap<String, Object>();
			
			try {
				if (act.equals("get_token_by_code")) {
					
					obj = auth.getAccessTokenByCode(request.getParameter("code"), null);					
					
					TokenInfo info = new TokenInfo();
					info.setAccessToken(String.valueOf(obj.get("access_token")));
					info.setRefreshToken(String.valueOf(obj.get("refresh_token")));
					info.setExpiresIn(Long.parseLong(String.valueOf(obj.get("expires_in"))));
					info.setScope(String.valueOf(obj.get("scope")));
					
					resp = info.toJsonString();
					
				} else if (act.equals("get_userinfo_by_token")) {
					
					obj = auth.userMe(request.getParameter("token"));
					
					QihooUserInfo info = new QihooUserInfo();
					info.setId(String.valueOf(obj.get("id")));
					info.setName(String.valueOf(obj.get("name")));
					info.setAvatar(String.valueOf(obj.get("avatar")));
					
					resp = info.toJsonString();
				}				
			} catch (QException e) {
				e.printStackTrace();
				obj.put("error_code", e.getCode());
				obj.put("error", e.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace(_writer);
			}
			
			_writer.write(resp);
		}
		catch (RuntimeException re) {			
			re.printStackTrace(_writer);
		}
		catch (Exception e1) {
			e1.printStackTrace(_writer);
		}
		finally {
			System.out.println("finally ");
			_writer.close();
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP
	 * <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP
	 * <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
