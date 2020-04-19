package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.share.exception.DAOException;
import com.share.exception.UserIsNotExistException;
import com.share.service.ShareService;

public class ChangePasswordServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡǰ�˲���
		String pwd = req.getParameter("pwd");
		String idStr = req.getParameter("userid");
		int id = Integer.parseInt(idStr);
		//�涨�����ʽ
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//�����߼�����
		ShareService service = new ShareService();
		try {
			//�޸�����
			service.ChangePassword(id, pwd);
			out.println("{\"msg\":\"success\",\"password\":\"" + pwd + "\"}");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		} catch (UserIsNotExistException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		}
	}

}
