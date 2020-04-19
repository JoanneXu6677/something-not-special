package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.share.dao.ShareDAO;
import com.share.entity.User;
import com.share.exception.DAOException;
import com.share.exception.UserNameAlreadyExistException;
import com.share.service.ShareService;

public class registerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. ����ҳ���ϵ�����
		System.out.println("�ұ������ˣ�");
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String sGender = req.getParameter("gender");
		String email = req.getParameter("email");
		int gender = Integer.parseInt(sGender);
		System.out.println(name+","+pwd+","+email+","+gender);
		
		//2.��Ӧ���� ��Ӧ���ݸ�ʽΪjson
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//3.�����߼�����
		ShareService service = new ShareService();
		User usr = new User(0, name, pwd, email, gender);
		try {
			//4.����û�
			service.addUsr(usr);
			//5.���سɹ���Ϣ
			out.println("{\"msg\":\"success\"}");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		} catch (UserNameAlreadyExistException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		}
	}

}
