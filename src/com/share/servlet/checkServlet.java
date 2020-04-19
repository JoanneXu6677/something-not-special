package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.share.exception.DAOException;
import com.share.service.ShareService;

public class checkServlet extends HttpServlet {

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
		ShareService service = new ShareService();
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("LOGIN_USER");
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		if(obj == null) {
			out.println("{\"msg\":\"logout\"}");
		}else {
			try {
				out.println("{"
						+ "\"msg\":\"success\","
						+ "\"data\":{\"name\":\"" + service.GetUserNameById((Integer)obj)+"\"}"
						+ "}");
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
