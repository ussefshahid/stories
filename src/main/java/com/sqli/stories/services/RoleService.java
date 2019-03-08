package com.sqli.stories.services;

import com.sqli.stories.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role add(Role role);
    Role update(Role role);
    List<Role> getById(String name);
    List<Role> getAll();
    void delete(String name);
}
