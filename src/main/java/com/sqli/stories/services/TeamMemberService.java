package com.sqli.stories.services;

import com.sqli.stories.entities.TeamMember;

import java.util.List;

public interface TeamMemberService {
    TeamMember add(TeamMember teamMember);
    TeamMember update(TeamMember teamMember);
    void delete(Long id);
    TeamMember getById(Long id);
    List<TeamMember> getAll();
}
