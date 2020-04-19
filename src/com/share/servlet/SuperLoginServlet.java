package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.share.service.ShareService;

public class SuperLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String idStr = req.getParameter("id");
			String password = req.getParameter("pwd");
			int id = Integer.parseInt(idStr);
			PrintWriter out = resp.getWriter(); 
			//响应数据 响应数据格式为json
			resp.setContentType("text/plain;charset=utf-8");
			//获取session会话
			HttpSession session = req.getSession();
			if(idStr.equals("102") && password.equals("zqf456")) {
				session.setAttribute("LOGIN_SUPER", id);
				out.println("{\"msg\":\"success\"}");
			}else {
				out.println("{\"msg\":\""+ "username or pwd error"+"\"}");
			}
	}
}
