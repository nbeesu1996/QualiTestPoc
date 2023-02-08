package com.qualitest.poc.service;

import java.util.List;

import com.qualitest.poc.model.User;
import com.qualitest.poc.model.UserDto;

public interface UserService {
    User save(UserDto user) throws Exception;
    List<User> findAll();
    User findOne(String username) throws Exception;
}
