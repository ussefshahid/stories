package com.sqli.stories.services.impl;

import com.sqli.stories.dao.RoleRepository;
import com.sqli.stories.entities.Role;
import com.sqli.stories.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultRoleService implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles=new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public void deleteRole(Long id) {
    roleRepository.deleteById(id);
    }
}
