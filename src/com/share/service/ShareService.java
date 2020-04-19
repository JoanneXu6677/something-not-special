package com.share.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.share.dao.ShareDAO;
import com.share.entity.Activity;
import com.share.entity.Found;
import com.share.entity.Inter;
import com.share.entity.Lost;
import com.share.entity.User;
import com.share.exception.ArticleNotExistException;
import com.share.exception.DAOException;
import com.share.exception.EmptyArticleException;
import com.share.exception.StudentIdOrPasswordException;
import com.share.exception.UserIsNotExistException;
import com.share.exception.UserNameAlreadyExistException;
import com.share.util.EntityUtils;
public class ShareService {
	/**
	 * 登录
	 * @param id
	 * @param password
	 * @throws UserIsNotExistException
	 * @throws StudentIdOrPasswordException
	 * @throws DAOException
	 * @throws EmptyArticleException 
	 * @throws ArticleNotExistException 
	 */
	public static void main(String[] args) throws DAOException, EmptyArticleException, UserIsNotExistException, ArticleNotExistException {
		ShareService test = new ShareService();
//		int pageNum = test.GetMaxIndex("interactive");
//		System.out.println(pageNum);
//		test.DeleteInterById(51002);
		System.out.println(test.GetALLUser());
//		List<Inter> iT = test.GetInterArticle(1);
//		List<Map> mResult = new ArrayList<>();
//		for (Iterator iterator = iT.iterator(); iterator.hasNext();) {
//			Inter inter = (Inter) iterator.next();
//			Map<String, Object> iMap = EntityUtils.entityToMap(inter);
//			String[] pcPath = ((String)iMap.get("pcPath")).split(",");
//			List<String> l = Arrays.asList(pcPath);
//			iMap.put("pcPath", l);
//			mResult.add(iMap);
//		}
//		String interJson = JSON.toJSONString(mResult);
//		System.out.println(interJson);
////		List<Lost> lT = test.GetLostArticle(1);
////		System.out.println(lT.size());
////		String LostJson = JSON.toJSONString(lT);
////		System.out.println("{\"msg\":\"success\",\"data\":"+ LostJson +"}");
	}
	/**
	 * 删除用户信息
	 * @param id
	 * @throws DAOException
	 * @throws UserIsNotExistException 
	 */
	public void deleteById(int id) throws DAOException, UserIsNotExistException{
		ShareDAO dao = new ShareDAO();
		User usr = dao.selectByUserId(id);
		if(usr == null){
			throw new UserIsNotExistException("用户信息不存在!");
		}
		dao.delete(id);
	}
	public User GetUserById(int id) throws DAOException {
		ShareDAO dao = new ShareDAO();
		User usr = dao.selectByUserId(id);
		return usr;
	}
	/**
	 * 查询所有用户信息
	 * @return 用户
	 * @throws DAOException DAO异常 
	 * @throws UserIsNotExistException 
	 * @throws StudentIsNotEixstException学生信息不存在异常 
	 */
	public List<User> searchAll() throws DAOException, UserIsNotExistException{
		ShareDAO dao = new ShareDAO();
		List<User> list = dao.selectAll();
		if(list.size() == 0 ){
			throw new UserIsNotExistException("用户信息不存在！");
		}
		return list;
	}
	/**
	 * 判断处理登录过程
	 * @param name
	 * @param password
	 * @throws UserIsNotExistException
	 * @throws StudentIdOrPasswordException
	 * @throws DAOException
	 */
	public void login(String name, String password) throws UserIsNotExistException, StudentIdOrPasswordException, DAOException {
		//1.获取界面上的编号和密码
		ShareDAO dao = new ShareDAO();
		//2.根据用户名查询数据库中的学生密码
		User usr = dao.selectByUserName(name);
		//3.判断usr是否为空
		if(usr == null) {
			throw new UserIsNotExistException("用户信息不存在！");
		}
		//4.判断密码不相等
		if(!usr.getPassword().equals(password)) {
			throw new StudentIdOrPasswordException("用户名或密码错误");
		}
	}
	/**
	 * 添加注册用户
	 * @param usr
	 * @throws DAOException
	 * @throws UserNameAlreadyExistException
	 */
	public void addUsr(User usr) throws DAOException, UserNameAlreadyExistException {
		String name = usr.getName();
		ShareDAO dao = new ShareDAO();
		if(dao.selectByUserName(name) != null) {
			throw new UserNameAlreadyExistException("用户名已存在！");
		}
		int maxId = dao.getMaxId("user");
		maxId ++;
		usr.setUserId(maxId);
		dao.insertUser(usr);
	}
	public void ChangePassword(int id, String password) throws DAOException, UserIsNotExistException {
		ShareDAO dao = new ShareDAO();
		User usr = dao.selectByUserId(id);
		if(usr == null) {
			throw new UserIsNotExistException("用户信息不存在！");
		}
		dao.UpdatePwd(id, password);
	}
	/**
	 * 获取所有用户信息
	 * @return
	 * @throws DAOException
	 */
	public List<User> GetALLUser() throws DAOException {
		ShareDAO dao = new ShareDAO();
		List<User> result = new ArrayList<>();
		result = dao.selectAll();
		return result;
	}
	/**
	 * 通过id获取用户名
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public String GetUserNameById(int id) throws DAOException {
		ShareDAO dao = new ShareDAO();
		User usr = dao.selectByUserId(id);
		return usr.getName();
	}
	/**
	 * 通过用户名获取id
	 * @param name
	 * @return
	 * @throws DAOException
	 * @throws UserIsNotExistException
	 */
	public int GetIdByUserName(String name) throws DAOException, UserIsNotExistException {
		ShareDAO dao = new ShareDAO();
		User usr = dao.selectByUserName(name);
		if(usr == null) {
			throw new UserIsNotExistException("用户信息不存在！");
		}
		return usr.getUserId();
	}
	public int GetMaxId(String name) throws DAOException {
		ShareDAO dao = new ShareDAO();
		int id = dao.getMaxId(name);
		return id;
	}
	/**
	 * 获取所有的交友互动帖子
	 * @return
	 * @throws DAOException
	 */
	public List<Inter> GetAllInter() throws DAOException {
		ShareDAO dao = new ShareDAO();
		List<Inter> result = new ArrayList<>();
		result = dao.showInter(51000, dao.getMaxId("interactive"));
		return result;
	}
	/**
	 * 根据交友互动帖子ID获取单个帖子
	 * @param id
	 * @return
	 */
	public Inter GetInterById(int id) {
		ShareDAO dao = new ShareDAO();
		Inter result = null;
		result = dao.showInter(id, id).get(0);
		return result;
	}
	/**
	 * 删除单个帖子
	 * @param id
	 * @throws ArticleNotExistException
	 * @throws DAOException
	 */
	public void DeleteInterById(int id) throws ArticleNotExistException, DAOException {
		ShareDAO dao = new ShareDAO();
		Inter result = dao.showInter(id, id).get(0);
		if(result == null) {
			throw new ArticleNotExistException("帖子信息不存在！");
		}
		else {
			dao.deleteInter(id);
		}
	};
	/**
	 * 修改用户密码
	 * @param id
	 * @param pwd
	 * @throws DAOException
	 */
	public void ChangePwd(int id, String pwd) throws DAOException {
		ShareDAO dao = new ShareDAO();
		dao.UpdatePwd(id, pwd);
	}
	/**
	 * 按照lostid从大到小返回帖子
	 * @param index
	 * @return
	 * @throws DAOException
	 * @throws EmptyArticleException 
	 */
	public List<Lost> GetLostArticle(int index) throws DAOException, EmptyArticleException {
		ShareDAO dao = new ShareDAO();
		List<Lost> result = null;
		int maxId = dao.getMaxId("lost");
		System.out.println(maxId);
		//每一张页面最多十张帖子
		int currentMax = maxId - 10 * (index - 1);
		int	currentMin = currentMax - 10 + 1;
		//如果index对应页没有帖子，则返回空
		if(currentMax <= 41000) {
			throw new EmptyArticleException("没有更多帖子了!");
		}
		result = dao.showLost(currentMin, currentMax);
		return result;
	}
	/**
	 * 按照foundid从大到小返回帖子
	 * @param index
	 * @return
	 * @throws DAOException
	 * @throws EmptyArticleException 
	 */
	public List<Found> GetFoundArticle(int index) throws DAOException, EmptyArticleException {
		ShareDAO dao = new ShareDAO();
		List<Found> result = null;
		int maxId = dao.getMaxId("found");
		System.out.println(maxId);
		//每一张页面最多十张帖子
		int currentMax = maxId - 10 * (index - 1);
		int	currentMin = currentMax - 10 + 1;
		//如果index对应页没有帖子，则返回空
		if(currentMax <= 41000) {
			throw new EmptyArticleException("没有更多帖子了!");
		}
		result = dao.showFound(currentMin, currentMax);
		return result;
	}
	/**
	 * 获取学生活动帖子
	 * @param index
	 * @return
	 * @throws DAOException
	 * @throws EmptyArticleException
	 */
	public List<Inter> GetInterArticle(int index) throws DAOException, EmptyArticleException {
		ShareDAO dao = new ShareDAO();
		List<Inter> result = null;
		int maxId = dao.getMaxId("interactive");
		System.out.println(maxId);
		//每张页面最多4张帖子
		int currentMax = maxId - 4 * (index - 1);
		int	currentMin = currentMax - 4 + 1;
		//如果index对应页没有帖子，则返回空
		if(currentMax <= 51000) {
			throw new EmptyArticleException("没有更多帖子了!");
		}
		result = dao.showInter(currentMin, currentMax);
		return result;
	}
	/**
	 * 添加活动帖子
	 * @param act
	 * @throws DAOException
	 */
	public void addActivity(Activity act) throws DAOException {
		ShareDAO dao = new ShareDAO();
		dao.insertActivity(act);
	}
	public void addInter(Inter it) throws DAOException {
		ShareDAO dao = new ShareDAO();
		dao.insertInter(it);
	}
	/**
	 * 返回表要分的页数
	 * @param table
	 * @return
	 * @throws DAOException
	 */
	public int GetMaxIndex(String table) throws DAOException {
		ShareDAO dao = new ShareDAO();
		int maxId = dao.getMaxId(table);
		if(table.equals("lost")) {
			maxId -= 41000;
			return (int)Math.ceil(maxId/10.0);
		}else if(table.equals("found")) {
			maxId -= 61000;
			return (int)Math.ceil(maxId/10.0);
		}else if(table.equals("activity")) {
			maxId -= 31000;
			return (int)Math.ceil(maxId/4.0);
		}else if(table.equals("shitem")) {
			maxId -= 21000;
			return (int)Math.ceil(maxId/4.0);
		}else if(table.equals("interactive")) {
			maxId -= 51000;
			return (int)Math.ceil(maxId/4.0);
		}
		return 0;
	}
}
