package com.qualitest.poc.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qualitest.poc.model.User;

//@Repository
public interface UserDao {//extends CrudRepository<User, Long> {
    User findByUsername(String username) throws Exception;
    User save(User user) throws Exception;
}