package com.sqli.stories.dao;

import com.sqli.stories.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r where r.name like :name")
    List<Role> getByName(@Param("name") String name);
}
