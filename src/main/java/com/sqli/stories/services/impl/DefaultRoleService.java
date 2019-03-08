package com.sqli.stories.services.impl;

import com.sqli.stories.dao.RoleRepository;
import com.sqli.stories.entities.Role;
import com.sqli.stories.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultRoleService implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getById(String name) {
        return roleRepository.getById(name);
    }
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(String name) {
        roleRepository.deleteById(name);
    }
}
