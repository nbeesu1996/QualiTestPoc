package com.qualitest.poc.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.qualitest.poc.dao.UserDao;
import com.qualitest.poc.model.User;
import com.qualitest.poc.util.QualitestUtil;
@Component
public class UserServiceDaoImpl implements UserDao{
	
	private UserServiceDaoImpl userServiceDaoImpl ;
	public UserServiceDaoImpl(@Lazy UserServiceDaoImpl userServiceDaoImpl) {
		super();
		this.userServiceDaoImpl = userServiceDaoImpl;
	}
	
	@Override
	public User findByUsername(String username) {
		List<User> usersList =	QualitestUtil.readUsers();
		if(!usersList.isEmpty()) {
			return usersList.stream().filter(us->us.getUsername().equalsIgnoreCase(username))
					.findFirst().get();
		}
		return null;
	}

	@Override
	public User save(User user) throws Exception {
		// TODO Auto-generated method stub
		QualitestUtil.writeUserIntoFile(user);
		return user;
	}

}
