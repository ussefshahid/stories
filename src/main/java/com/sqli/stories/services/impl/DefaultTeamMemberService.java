package com.sqli.stories.services.impl;

import com.sqli.stories.dao.TeamMemberRepository;
import com.sqli.stories.entities.TeamMember;
import com.sqli.stories.services.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultTeamMemberService implements TeamMemberService {
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Override
    public TeamMember add(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public TeamMember update(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public void delete(Long id) {
        teamMemberRepository.deleteById(id);
    }

    @Override
    public TeamMember getById(Long id) {
        return teamMemberRepository.getOne(id);
    }

    @Override
    public List<TeamMember> getAll() {
        return teamMemberRepository.findAll();
    }
}