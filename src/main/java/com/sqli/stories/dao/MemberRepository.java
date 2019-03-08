package com.sqli.stories.dao;

import com.sqli.stories.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("select member from Member where m.firstName like :keyword or m.lastName like :keyword or m.login like :keyword")
    List<Member> searchByKeyword(@Param("keyword") String keyword);
}
