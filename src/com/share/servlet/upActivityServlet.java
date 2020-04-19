package com.share.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.*;

import com.share.entity.Activity;
import com.share.exception.DAOException;
import com.share.service.ShareService;
import com.share.util.DateUtils;
public class upActivityServlet extends HttpServlet {

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
		//1.设置编码集
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//2. 创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//3. 创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		//4. 解决上传文件名的中文乱码
		upload.setHeaderEncoding("utf-8");
		factory.setSizeThreshold(1024 * 500);
		File linshi = new File("E:\\linshi");
		factory.setRepository(linshi);
		upload.setFileSizeMax(1024 * 1024 * 5);
		//5. 创建逻辑对象
		ShareService service = new ShareService();
		String filePath;
		Map<String, String> map = new HashMap();
		String pcPath = new String();
		try {
			map.put("articleid", (service.GetMaxId("activity") + 1) + "");
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			int i = 1;
			List<FileItem> items = upload.parseRequest(req);
			//遍历items
			for(FileItem item : items) {
				
				//若是一般表单域
				if(item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					map.put(name, value);
					System.out.println(name + ": " + value);
				}
				//若是文件域
				else {
					String fileName = (service.GetMaxId("activity") + 1) + "-" + i + ".png";
					long sizeInBytes = item.getSize();
//					System.out.println(fileName);
//					System.out.println(sizeInBytes);
					
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					if(sizeInBytes != 0) {
						filePath  = "e:\\files\\" + fileName;
						filePath = "e:\\coding\\web\\shixun\\WebContent\\img\\user\\inter\\" + fileName;
						System.out.println(fileName);
						OutputStream out = new FileOutputStream(filePath);
						
						while((len = in.read(buffer)) != -1 ) {
							out.write(buffer, 0, len);
						}
						pcPath += fileName + ",";
						out.close();
					}
					
					in.close();
					i ++;
				}
			}
			map.put("pcPath", pcPath);
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("LOGIN_USER");
			int id = (Integer)obj;
			String sId = id + "";
			map.put("userid", sId);
			System.out.println(map);
			//创建Activity对象
			Activity result = new Activity(Integer.parseInt(map.get("articleid")), map.get("topic"), map.get("title"), map.get("description"), 0, 0,
					DateUtils.dateToStr(new Date(), "yyyy-MM-dd"), map.get("pcPath"), Integer.parseInt(map.get("userid")));
			System.out.println(result.toString());
			//添加活动帖子
			service.addActivity(result);
			resp.sendRedirect("http://localhost:8080/shixun/fatie.html");
		} catch(FileUploadException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
