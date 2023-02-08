package com.qualitest.poc.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qualitest.poc.dao.UserDao;
import com.qualitest.poc.model.Role;
import com.qualitest.poc.model.User;
import com.qualitest.poc.model.UserDto;
import com.qualitest.poc.service.RoleService;
import com.qualitest.poc.service.UserService;
import com.qualitest.poc.util.QualitestUtil;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

   // @Autowired
    //private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =null;
		try {
			user = userDao.findByUsername(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = QualitestUtil.readUsers();
        return list;
    }

    @Override
    public User findOne(String username) throws Exception {
        return userDao.findByUsername(username);
    }

    @Override
    public User save(UserDto user) throws Exception {
        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        nUser.setId(String.valueOf(UUID.randomUUID()));
        return userDao.save(nUser);
    }
}
