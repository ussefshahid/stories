package com.sqli.stories.services.impl;

import com.sqli.stories.entities.Member;
import com.sqli.stories.helpers.factory.UserRequestFactory;
import com.sqli.stories.helpers.payload.UserRequest;
import com.sqli.stories.repository.MemberRepository;
import com.sqli.stories.services.MemberService;
import com.sqli.stories.util.PasswordEmailGenrator;
import com.sqli.stories.util.impl.DefaultSendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEmailGenrator passwordEmailGenrator;
    @Autowired
    private DefaultSendMailService defaultSendMailService;

    @Override
    public Member getByLogin(String login) {
        return memberRepository.getMemberByLogin(login);
    }

    @Override
    public Member add(Member member) {
        String randomPassword = passwordEmailGenrator.generateMemberPassword();
        member.setPassword(randomPassword);
        String email = passwordEmailGenrator.generateMemberMailFromLogin(member.getLogin());
        UserRequest userRequest = UserRequestFactory.createUserRequest(email, member.getPassword(), member.getFirstName(), member.getLastName());
        defaultSendMailService.sendEmail(userRequest);
        System.out.println("Congratulations !");
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
    public boolean existsByLogin(String login) {
        return memberRepository.existsByLogin(login);
    }

    @Override
    public void delete(String login) {
        memberRepository.deleteById(login);
    }
}
