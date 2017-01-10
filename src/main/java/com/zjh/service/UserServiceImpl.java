package com.zjh.service;

import com.zjh.dao.IUserDAO;
import com.zjh.dao.UserDAOImpl;
import com.zjh.vo.User;

public class UserServiceImpl implements IUserService{
	private IUserDAO dao=new UserDAOImpl();
	public User login(User user) {
		return dao.login(user);
	}

}
