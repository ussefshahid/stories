package com.sqli.stories.dao;

import com.sqli.stories.entities.EquipeMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeMemberRepository extends JpaRepository<EquipeMember, Long> {
}
