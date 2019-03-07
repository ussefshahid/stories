package com.sqli.stories.services.impl;

import com.sqli.stories.dao.EquipeMemberRepository;
import com.sqli.stories.entities.EquipeMember;
import com.sqli.stories.services.EquipeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultEquipeMemberService implements EquipeMemberService {
    @Autowired
    private EquipeMemberRepository equipeMemberRepository;
    @Override
    public EquipeMember addEquipemembre(EquipeMember equipeMember) {
        return equipeMemberRepository.save(equipeMember);

    }

    @Override
    public EquipeMember updateEquipeMembre(EquipeMember equipeMember) {
        return equipeMemberRepository.save(equipeMember);
    }

    @Override
    public void deleteEquipeMember(Long idEquipeMember) {
        equipeMemberRepository.deleteById(idEquipeMember);
    }

    @Override
    public EquipeMember getEquipeMemberById(long id) {
        Optional<EquipeMember> equipeMember=equipeMemberRepository.findById(id);
        return equipeMember.orElse(null);
    }

    @Override
    public List<EquipeMember> getAllEquipeMembers() {
        List<EquipeMember> equipeMembers=new ArrayList<>();
        equipeMemberRepository.findAll().forEach(equipeMembers::add);
        return  equipeMembers;
    }
}
