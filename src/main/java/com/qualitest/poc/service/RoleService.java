package com.qualitest.poc.service;

import com.qualitest.poc.model.Role;

public interface RoleService {
    Role findByName(String name);
}
