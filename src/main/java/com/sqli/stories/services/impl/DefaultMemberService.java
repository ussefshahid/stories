package com.sqli.stories.services.impl;

import com.sqli.stories.dao.MemberRepository;
import com.sqli.stories.entities.Member;
import com.sqli.stories.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultMemberService implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member getByLogin(String login) {
        return memberRepository.getOne(login);
    }

    @Override
    public Member add(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member update(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> searchByKeyword(String keyword) {
        return memberRepository.searchByKeyword(keyword);
    }

    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Override
    public void delete(String login) {
    memberRepository.deleteById(login);
    }
}
