package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.share.entity.Inter;
import com.share.exception.DAOException;
import com.share.service.ShareService;


public class selectInterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取前端参数
		String idStr = req.getParameter("actid");
		ShareService service = new ShareService();
		//设置编码类型
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//如果搜索框为空，则展示所有信息
		if(idStr.equals("")) {
			try {
				//System.out.println("true\n\n");
				List<Inter> result = new ArrayList<>();
				result = service.GetAllInter();
				//System.out.println("result" + result);
				out.println("{\"msg\":\"success\",\"data\":" + JSON.toJSONString(result) + "}");
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			int id = Integer.parseInt(idStr);
			Inter result = service.GetInterById(id);
			out.println("{\"msg\":\"success\",\"data\":[" + JSON.toJSONString(result) + "]}");
		}
	}
	
}
