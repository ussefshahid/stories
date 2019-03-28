package com.sqli.stories.services;

import com.sqli.stories.entities.Member;

import java.util.List;

public interface MemberService {
    Member getByLogin(String login);
    Member add(Member member);
    Member update(Member member);
    List<Member> searchByKeyword(String keyword);
    List<Member> getAll();
    boolean existsByLogin(String login);
    void delete(String login);
}
