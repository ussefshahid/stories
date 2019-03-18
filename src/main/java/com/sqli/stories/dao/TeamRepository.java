package com.sqli.stories.dao;

import com.sqli.stories.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("select t from Team t where t.name like :name")
    List<Team> findByName(@Param("name") String name);
    Team findTeamById(Long id);
}
