package com.qualitest.poc.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qualitest.poc.model.Role;

//@Repository
public interface RoleDao {//extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
}