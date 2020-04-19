package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.share.exception.DAOException;
import com.share.service.ShareService;

public class pageIndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 获取前端传来的表名
		String table = req.getParameter("tableName");
		
		//2.设置响应数据格式为json
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		//3. 创建逻辑对象
		ShareService service = new ShareService();
		int pageSum;
		try {
			pageSum = service.GetMaxIndex(table);
			System.out.println(pageSum);
			out.println("{\"msg\":\"success\",\"index\":"+ pageSum +"}");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		}
	}

}
