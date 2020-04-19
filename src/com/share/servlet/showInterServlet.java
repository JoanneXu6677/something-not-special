package com.share.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.share.entity.Found;
import com.share.entity.Inter;
import com.share.exception.DAOException;
import com.share.exception.EmptyArticleException;
import com.share.service.ShareService;
import com.share.util.EntityUtils;

public class showInterServlet extends HttpServlet {

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
		//1. ��ȡ��ǰ��ҳ����
		String sIndex = req.getParameter("index");
		int index = Integer.parseInt(sIndex);
		System.out.println(index);
		
		//2. ��Ӧ���� ��Ӧ���ݸ�ʽΪjson
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		//3.�����߼�����
		ShareService service = new ShareService();
		try {
			//4. ��ȡ���ص�����
			List<Inter> resultList = service.GetInterArticle(index);
			List<Map> mResult = new ArrayList<>();
			for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
				Inter inter = (Inter) iterator.next();
				Map<String, Object> iMap = EntityUtils.entityToMap(inter);
				//4. �ָ�pcPath���ó����ɸ�ͼƬ·���ֶ�
				String[] pcPath = ((String)iMap.get("pcPath")).split(",");
				List<String> l = Arrays.asList(pcPath);
				iMap.put("pcPath", l);
				//5. ���ָ�������mResult��
				mResult.add(iMap);
			}
			String interJson = JSON.toJSONString(mResult);
			//6. ��������
			String resultStr = "{\"msg\":\"success\",\"data\":"+ interJson +"}";
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
