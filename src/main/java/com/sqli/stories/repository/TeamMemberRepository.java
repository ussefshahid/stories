package com.sqli.stories.repository;

import com.sqli.stories.entities.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    @Query("SELECT m FROM TeamMember m WHERE m.team.id = :id")
    List<TeamMember> getByTeam(@Param("id") Long id);
}
