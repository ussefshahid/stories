package com.sqli.stories.dao;

import com.sqli.stories.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,String> {
    @Query("SELECT r FROM Role r where r.name=:name")
    Role getById(@Param("name") String name);
}
