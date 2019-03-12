package com.sqli.stories.dao;

import com.sqli.stories.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("SELECT m FROM Member m where m.firstName like CONCAT( '%',:keyword,'%') or m.lastName like CONCAT( '%',:keyword,'%')or m.login like CONCAT( '%',:keyword,'%')")
    List<Member> searchByKeyword(@Param("keyword") String keyword);
    @Query("SELECT m FROM Member  m WHERE m.login=:login")
    Member getMemberByLogin(@Param("login") String login);
}
