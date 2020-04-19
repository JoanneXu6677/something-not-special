package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.share.entity.Inter;
import com.share.entity.User;
import com.share.exception.ArticleNotExistException;
import com.share.exception.DAOException;
import com.share.exception.UserIsNotExistException;
import com.share.service.ShareService;

public class DeleteUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取前端参数
				String idStr = req.getParameter("userid");
				int id = Integer.parseInt(idStr);
				//设置编码格式
				PrintWriter out = resp.getWriter();
				resp.setContentType("text/plain;charset=utf-8");
				//创建逻辑对象
				ShareService service = new ShareService();
				try {
					//删除用户
					User result = service.GetUserById(id);
					service.deleteById(id);
					//返回成功消息
					out.println("{\"msg\":\"success\"}");
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					out.println("{\"msg\":\""+e1.getMessage()+"\"}");
				} catch (UserIsNotExistException e1) {
					// TODO Auto-generated catch block
					out.println("{\"msg\":\""+e1.getMessage()+"\"}");
				}
	}
	
}
