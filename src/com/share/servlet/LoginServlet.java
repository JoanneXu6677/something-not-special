package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.share.exception.DAOException;
import com.share.exception.StudentIdOrPasswordException;
import com.share.exception.UserIsNotExistException;
import com.share.service.ShareService;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.��ȡǰ������
		String userName = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		
		System.out.println(userName+","+pwd);
		
		//2.��Ӧ���� ��Ӧ���ݸ�ʽΪjson
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//3.��ȡSession �Ự
		HttpSession session = req.getSession();
		ShareService service = new ShareService();
		try {
			//4.��ȡ�û�id
			int id = service.GetIdByUserName(userName);
			service.login(userName, pwd);
			//5.����sessionid��ά�ֵ�¼״̬
			session.setAttribute("LOGIN_USER", id);
			//6. ��¼�ɹ�
			out.println("{\"msg\":\"success\"}");
		} catch (UserIsNotExistException e) {
			out.println("{\"msg\":\""+ e.getMessage() +"\"}");
		} catch (StudentIdOrPasswordException e) {
			out.println("{\"msg\":\""+ e.getMessage() +"\"}");
		} catch (DAOException e) {
			out.println("{\"msg\":\""+ e.getMessage() +"\"}");
		}
		
	}

}
