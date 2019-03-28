package com.sqli.stories.services;

import com.sqli.stories.entities.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);
    Role update(Role role);
    List<Role> getByName(String name);
    List<Role> getAll();
    void delete(Long id);
}
