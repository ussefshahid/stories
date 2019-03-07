package com.sqli.stories.services;

import com.sqli.stories.entities.EquipeMember;

import java.util.List;

public interface EquipeMemberService {
    EquipeMember addEquipemembre(EquipeMember equipeMember);

    EquipeMember updateEquipeMembre(EquipeMember equipeMember);

    void deleteEquipeMember(Long idEquipeMember);
    EquipeMember getEquipeMemberById(long id);

    List<EquipeMember> getAllEquipeMembers();


}
