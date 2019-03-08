package com.sqli.stories.services;

import com.sqli.stories.entities.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);
    Role update(Role role);
    Role getById(Long id);
    List<Role> getAll();
    void delete(Long id);
}
