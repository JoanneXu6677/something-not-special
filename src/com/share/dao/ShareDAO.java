package com.share.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.share.entity.Activity;
import com.share.entity.Found;
import com.share.entity.Inter;
import com.share.entity.Lost;
import com.share.entity.SecondHand;
import com.share.entity.User;
import com.share.exception.DAOException;
import com.share.util.ConnectionUtils;
/**
 *	 校内信息共享平台
 * @author zqf
 * @version 1.0
 * @since 2019-06-28
 * @see ConnectionUtils
 */
public class ShareDAO {
	public static void main(String[] args) throws DAOException {
		ShareDAO test = new ShareDAO();
		test.UpdatePwd(102, "zqf123");
	}
	/**
	 * 	插入用户信息
	 * @param u
	 * @throws DAOException
	 */
	public void insertUser(User u) throws DAOException {
		System.out.println("我被调用了");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			int gender = u.getGender();
			String sql = "INSERT INTO USER(USERID, NAME, PASSWORD, MAIL, GENDER) VALUES("
					+ u.getUserId() + ",'" + u.getName() + "','" + u.getPassword() + "','" + 
					u.getMail() + "','" + u.getGender() + "')";
			stmt.executeUpdate(sql);
		} catch(SQLException e) {
			throw new DAOException(e.getMessage());
		}finally {
			ConnectionUtils.close(conn, stmt);
		}
	};
	/**
	 * 	更新用户数据
	 * @param u
	 */
	public void update(User u) {
//		System.out.println("我被调用了");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			int gender = u.getGender();
			String sql = "UPDATE USER SET" +
						" NAME = " +  "'" + u.getName() + "'," +
						" PASSWORD = " + "'" + u.getPassword() + "'," +
						" MAIL = " + "'" + u.getMail() + "'," +
						" GENDER = " + "'" + u.getGender() + "'" +
						" WHERE USERID = " + u.getUserId();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	/**
	 * 删除学生信息
	 * 
	 * @param stu 学生对象
	 */
	public void delete(int id) throws DAOException{
		Connection conn = null;
		Statement stmt  = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			//转换字段
			String sql  ="DELETE FROM USER WHERE USERID = "+id;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DAOException(e.getMessage());
		}finally{
			ConnectionUtils.close(conn, stmt);
		}
	}
	/**
	 * 	根据用户名检索
	 * @param name
	 * @return
	 * @throws DAOException 
	 */
	public User selectByUserName(String name) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM USER " + 
						 "WHERE NAME = '" + name + "'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int userId = Integer.parseInt(rs.getString("userid"));
				String sName = rs.getString("name");
				String sPassword = rs.getString("password");
				String sMail = rs.getString("mail");
				int gender = Integer.parseInt(rs.getString("gender"));
				u = new User(userId, sName, sPassword, sMail, gender);
			}
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	};
	/**
	 * 	检索所有用户
	 * @param name
	 * @return
	 * @throws DAOException 
	 */
	public List<User> selectAll() throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<User> result = new ArrayList<>();
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM USER ";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int userId = Integer.parseInt(rs.getString("userid"));
				String sName = rs.getString("name");
				String sPassword = rs.getString("password");
				String sMail = rs.getString("mail");
				int gender = Integer.parseInt(rs.getString("gender"));
				User u = new User(userId, sName, sPassword, sMail, gender);
				result.add(u);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	};
	/**
	 * 更新密码
	 * @param id
	 * executeUpdate
	 * @throws DAOException 
	 */
	public void UpdatePwd(int id,String password) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<User> result = new ArrayList<>();
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "UPDATE USER SET PASSWORD = \"" + password + "\" WHERE USERID = " + id;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	}
	/**
	 * 获取最大的Id
	 * @return
	 * @throws DAOException
	 */
	public int getMaxId(String table) throws DAOException{
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs  = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = null;
			if(table.equals("activity")) {
				sql = "SELECT MAX(ARTICLEID) MAXID FROM ACTIVITY";
			}else if(table.equals("user")) {
				sql = "SELECT MAX(USERID) MAXID FROM USER";
			}else if(table.equals("shitem")) {
				sql = "SELECT MAX(ITEMID) MAXID FROM SHITEM";
			}else if(table.equals("lost")) {
				sql = "SELECT MAX(LOSTID) MAXID FROM LOST";
			}else if(table.equals("found")) {
				sql = "SELECT MAX(FOUNDID) MAXID FROM FOUND";
			}else if(table.equals("interactive")) {
				sql = "SELECT MAX(ACTID) MAXID FROM INTERACTIVE";
			}
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getInt("MAXID");
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}finally{
			ConnectionUtils.close(conn, stmt, rs);
		}
		return -1;
	}
	/**
	 * 	根据用户id检索
	 * @param name
	 * @return
	 * @throws DAOException 
	 */
	public User selectByUserId(int id) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM USER " + 
						 "WHERE USERID = '" + id + "'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int userId = Integer.parseInt(rs.getString("userid"));
				String sName = rs.getString("name");
				String sPassword = rs.getString("password");
				String sMail = rs.getString("mail");
				int gender = Integer.parseInt(rs.getString("gender"));
				u = new User(userId, sName, sPassword, sMail, gender);
			}
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	};
	/**
	 * 插入学生活动帖子
	 * @param aTest
	 * @throws DAOException
	 */
	public void insertActivity(Activity aTest) throws DAOException {
//		System.out.println("我被访问了");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO ACTIVITY VALUES("+
					aTest.getArticleId() + "," +
					"'" + aTest.getTopic() + "'," +
					"'" + aTest.getTitle() + "'," +
					"'" + aTest.getDescription() + "'," +					
					"" + aTest.getLikeCount() + "," +					
					"" + aTest.getCommentCount() + "," +					
					"'" + aTest.getTime() + "'," +					
					"'" + aTest.getPcPath() + "'," +
					"" + aTest.getUserId() +
					")";
//			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	};
	/**
	 * 插入失物招领帖子
	 * @param lTest
	 * @throws DAOException
	 */
	public void insertLostAndFound(Lost lTest) throws DAOException {
//		System.out.println("我被访问了");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO LOST VALUES("+
						lTest.getLostId() + "," +
						"'" + lTest.getLocation() + "'," +
						"'" + lTest.getPcPath()  + "'," +
						"'" + lTest.getTime() + "'," +
						"'" + lTest.getDescription() + "'," +
						"" + lTest.getUserId()+ "," +
						"'" + lTest.getContract() + "'" +
					")";
//			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	/**
	 * 插入寻物启事帖子
	 * @param lTest
	 * @throws DAOException
	 */
	public void insertFound(Found lTest) throws DAOException {
//		System.out.println("我被访问了");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO FOUND VALUES("+
						lTest.getFoundId() + "," +
						"'" + lTest.getLocation() + "'," +
						"'" + lTest.getPcPath()  + "'," +
						"'" + lTest.getTime() + "'," +
						"'" + lTest.getDescription() + "'," +
						"" + lTest.getUserId()+ "," +
						"'" + lTest.getContract() + "'" +
					")";
//			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	/**
	 * 插入二手交易帖子
	 * @param sTest
	 * @throws DAOException
	 */
	public void insertSecondHand(SecondHand sTest) throws DAOException {
//		System.out.println("我被访问了");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO SHITEM VALUES("+
						sTest.getItemId()+ "," +
						"" + sTest.getPrice()+ "," +
						"'" + sTest.getPcPath() + "'," +
						"'" + sTest.getDescription() + "'," +
						"" + sTest.getUserId() +
					")";
//			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	/**
	 * 插入互动帖子
	 * @param iTest
	 * @throws DAOException
	 */
	public void insertInter(Inter iTest) throws DAOException {
		System.out.println("我被访问了");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO INTERACTIVE VALUES("+
					iTest.getActId() + "," +
					"'" + iTest.getTopic() + "'," +
					"'" + iTest.getTitle() + "'," +
					"'" + iTest.getDescription() + "'," +					
					"" + iTest.getLikeCount() + "," +					
					"" + iTest.getCommentCount() + "," +					
					"'" + iTest.getTime() + "'," +					
					"'" + iTest.getPcPath() + "'," +
					"" + iTest.getUserId() +
					")";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	};
	/**
	 * 展示互动帖子
	 * @param index
	 * @return
	 */
	public List<Inter> showInter(int start, int end) {
		List<Inter> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Inter iTemp = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM INTERACTIVE WHERE ACTID >= "+ (start) + " AND ACTID <= " +  (end) + " ORDER BY ACTID DESC";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int actid = Integer.parseInt(rs.getString("actid"));
				String topic = rs.getString("topic");
				String title = rs.getNString("title");
				String description = rs.getString("description");
				int like = Integer.parseInt(rs.getString("like"));
				int comment = Integer.parseInt(rs.getString("comment"));
				String time = rs.getString("time");
				String pcPath = rs.getString("pcpath");
				int userid = Integer.parseInt(rs.getString("userid"));
				iTemp = new Inter(actid, topic, title, description, like, comment, time, pcPath, userid);
				result.add(iTemp);
			}
			System.out.println(result);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	/** 展示活动帖子
	 * @param index
	 * @return
	 */
	public List<Activity> showAct(int start, int end) {
		List<Activity> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Activity iTemp = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM ACTIVITY WHERE ARTICLEID >= "+ (31000 + start) + " AND ARTICLEID <= " +  (31000 + end) + " ORDER BY ARTICLEID DESC";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int actid = Integer.parseInt(rs.getString("articleid"));
				String topic = rs.getString("topic");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int like = Integer.parseInt(rs.getString("like"));
				int comment = Integer.parseInt(rs.getString("comment"));
				String time = rs.getString("time");
				String pcPath = rs.getString("pcpath");
				int userid = Integer.parseInt(rs.getString("userid"));
				iTemp = new Activity(actid, topic, title, description, like, comment, time, pcPath, userid);
				result.add(iTemp);
			}
			System.out.println(result);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	/**
	 * 展示失物招领帖子
	 * @param index
	 * @return
	 */
	public List<Lost> showLost(int start, int end) {
		List<Lost> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Lost iTemp = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM LOST WHERE LOSTID >= "+ (start) + " AND LOSTID <= " +  (end) + " ORDER BY LOSTID DESC";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int lostId = Integer.parseInt(rs.getString(1));
				String location = rs.getString(2);
				String pcPath = rs.getString(3);
				String time = rs.getString(4);
				String description = rs.getString(5);
				int userId = Integer.parseInt(rs.getString(6));
				String contract = rs.getString(7);
				iTemp = new Lost(lostId, location, pcPath, time, description, userId, contract);
				result.add(iTemp);
			}
//			System.out.println(result);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	/**
	 * 展示寻物启事帖子
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Found> showFound(int start, int end) {
		List<Found> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Found iTemp = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM FOUND WHERE FOUNDID >= "+ (start) + " AND FOUNDID <= " +  (end) + " ORDER BY FOUNDID DESC";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int lostId = Integer.parseInt(rs.getString(1));
				String location = rs.getString(2);
				String pcPath = rs.getString(3);
				String time = rs.getString(4);
				String description = rs.getString(5);
				int userId = Integer.parseInt(rs.getString(6));
				String contract = rs.getString(7);
				iTemp = new Found(lostId, location, pcPath, time, description, userId, contract);
				result.add(iTemp);
			}
//			System.out.println(result);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
	/**
	 * 展示二手交易帖子
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SecondHand> showSH(int start, int end) {
		List<SecondHand> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		SecondHand iTemp = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM SHITEM WHERE ITEMID >= "+ (21000 + start) + " AND ITEMID <= " +  (21000 + end);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int itemId = Integer.parseInt(rs.getString("itemid"));
				int price = Integer.parseInt(rs.getString("price"));
				String pcPath = rs.getString("pcpath");
				String description = rs.getString("description");
				int userId = Integer.parseInt(rs.getString("userid"));
				iTemp = new SecondHand(itemId, price, pcPath, description, userId);
				result.add(iTemp);
			}
//			System.out.println(result);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void deleteInter(int id) throws DAOException {
		Connection conn = null;
		Statement stmt  = null;
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			//转换字段
			String sql  ="DELETE FROM INTERACTIVE WHERE ACTID = "+ id;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DAOException(e.getMessage());
		}finally{
			ConnectionUtils.close(conn, stmt);
		}
	}
};

