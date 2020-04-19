package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.share.entity.Inter;
import com.share.exception.ArticleNotExistException;
import com.share.exception.DAOException;
import com.share.service.ShareService;

public class DeleteInterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取前端参数
		String idStr = req.getParameter("actid");
		int id = Integer.parseInt(idStr);
		//设置编码格式
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain;charset=utf-8");
		//创建逻辑对象
		ShareService service = new ShareService();
		Inter result = service.GetInterById(id);
		try {
			//删除用户
			service.DeleteInterById(id);
			//返回成功消息
			out.println("{\"msg\":\"success\"}");
		} catch (ArticleNotExistException | DAOException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		}
			
	}

}
