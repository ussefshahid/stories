package com.sqli.stories.services.impl;

import com.sqli.stories.dao.MemberRepository;
import com.sqli.stories.entities.Member;
import com.sqli.stories.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultMemberService  implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members=new ArrayList<>();
        memberRepository.findAll().forEach(members::add);
        return members;
    }

    @Override
    public void deleteMember(String loginMember) {
    memberRepository.deleteById(loginMember);
    }
}
