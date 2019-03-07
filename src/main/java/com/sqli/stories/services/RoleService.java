package com.sqli.stories.services;

import com.sqli.stories.entities.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    Role updateRole(Role role);
    List<Role> getAllRoles();
    void deleteRole(Long id);
}
