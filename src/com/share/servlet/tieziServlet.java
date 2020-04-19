package com.share.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.*;
public class tieziServlet extends HttpServlet {

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
		try {
			List<FileItem> items = upload.parseRequest(req);
			//遍历items
			for(FileItem item : items) {
				//若是一般表单域
				if(item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					System.out.println(name + ": " + value);
				}
				//若是文件域
				else {
					String fileName = item.getName();
					long sizeInBytes = item.getSize();
					System.out.println(fileName);
					System.out.println(sizeInBytes);
					
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					
					fileName = "e:\\files\\" + fileName;
					System.out.println(fileName);
					OutputStream out = new FileOutputStream(fileName);
					
					while((len = in.read(buffer)) != -1 ) {
						out.write(buffer, 0, len);
					}
					
					out.close();
					in.close();
				}
				
			}
		} catch(FileUploadException e) {
			e.printStackTrace();
		}
	}
	
}
