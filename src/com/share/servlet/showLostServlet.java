package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.share.entity.Lost;
import com.share.exception.DAOException;
import com.share.exception.EmptyArticleException;
import com.share.service.ShareService;

public class showLostServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 获取当前分页索引
		String sIndex = req.getParameter("index");
		int index = Integer.parseInt(sIndex);
		System.out.println(index);
		
		//2. 响应数据 响应数据格式为json
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		//3.创建逻辑对象
		ShareService service = new ShareService();
		try {
			List<Lost> resultList = service.GetLostArticle(index);
			System.out.println(JSON.toJSONString(resultList));
			String LostJson = JSON.toJSONString(resultList);
			String resultStr = "{\"msg\":\"success\",\"data\":"+ LostJson +"}";
			System.out.println(resultStr);
			out.println(resultStr);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		} catch (EmptyArticleException e) {
			// TODO Auto-generated catch block
			out.println("{\"msg\":\""+e.getMessage()+"\"}");
		}
		
		
	}

}
