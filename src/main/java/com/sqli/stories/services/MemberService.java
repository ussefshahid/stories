package com.sqli.stories.services;

import com.sqli.stories.entities.Member;

import java.util.List;

public interface MemberService {
    Member addMember(Member member);
    Member updateMember(Member member);
    List<Member> getAllMembers();
    void deleteMember(String loginMember);
}
