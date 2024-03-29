package com.sqli.stories.repository;

import com.sqli.stories.entities.TeamMember;
import com.sqli.stories.helpers.payload.MemberAuthenticatedTMResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    @Query("SELECT m FROM TeamMember m WHERE m.team.id = :id")
    List<TeamMember> getByTeam(@Param("id") Long id);

    @Query("SELECT new com.sqli.stories.helpers.payload.MemberAuthenticatedTMResponse(tm.team,tm.dateEntree) FROM TeamMember  tm WHERE tm.member.login=:login")
    MemberAuthenticatedTMResponse getTeamByMemberLogin(@Param("login") String login);

    @Query("SELECT m FROM TeamMember m WHERE m.member.login = :login AND m.dateSortie = null")
    TeamMember getTeamMemberByLogin(@Param("login") String login);

    @Query("SELECT m FROM TeamMember m WHERE m.member.login = :login")
    List<TeamMember> getTeamsByLogin(@Param("login") String login);
}

