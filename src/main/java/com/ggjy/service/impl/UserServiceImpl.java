package com.ggjy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ggjy.dao.UserDao;
import com.ggjy.entity.User;
import com.ggjy.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	@Override
	public String login(User user) throws Exception {
		// TODO Auto-generated method stub

		if (user == null) {
			return "登录的参数异常";
		}

		// 登录的id
		String id = user.getId();

		// 登录的密码
		String password = user.getPassword();

		if (password == null) {
			return "密码为空";
		}
		
		//id号不为空
		if (id != null) {
			User s = userDao.queryById(user.getId());
			// 用户不存在
			if (s == null) {
				return "该用户不存在";
			} else {
				if (password.equals(s.getPassword())) {
					// 登录成功
				} else {
					// 密码错误
					return "密码错误";
				}
			}
		} else {
			return "帐号为空";
		}
		return "success";
	}

}
