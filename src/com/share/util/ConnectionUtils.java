package com.share.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtils {
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	
	static{
		try {
			//1.加载配置文件
			Properties pro = new Properties();
			InputStream is = ConnectionUtils.class.getResourceAsStream("db.properties");
			pro.load(is);
			
			//2.读取配置文件
			driverClass = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			
			//3.加载驱动
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	
	
	public static void close(Connection conn,Statement stmt,ResultSet rs){
		if(rs != null){try{rs.close();}catch(SQLException e){e.printStackTrace();}}
		if(stmt != null){try{stmt.close();}catch(SQLException e){e.printStackTrace();}}
		if(conn != null){try{conn.close();}catch(SQLException e){e.printStackTrace();}}
	}
	
	public static void close(Connection conn,Statement stmt){
		close(conn,stmt,null);
	}
	
	
}
	