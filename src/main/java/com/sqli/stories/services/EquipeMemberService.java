package com.sqli.stories.services;

import com.sqli.stories.entities.TeamMember;

import java.util.List;

public interface EquipeMemberService {
    TeamMember addEquipemembre(TeamMember teamMember);

    TeamMember updateEquipeMembre(TeamMember teamMember);

    void deleteEquipeMember(Long idEquipeMember);

    List<TeamMember> getAllEquipeMembers();


}
