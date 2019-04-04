package com.sqli.stories.services.impl;

import com.sqli.stories.entities.Member;
import com.sqli.stories.helpers.factory.MemberResponseFactory;
import com.sqli.stories.helpers.payload.LoginRequest;
import com.sqli.stories.helpers.payload.MemberAuthenticatedResponse;
import com.sqli.stories.helpers.payload.MemberAuthenticatedTMResponse;
import com.sqli.stories.repository.MemberRepository;
import com.sqli.stories.repository.TeamMemberRepository;
import com.sqli.stories.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthServiceImpl implements AuthService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamMemberRepository teamMemTeamRepository;
    @Override
    public MemberAuthenticatedResponse login(LoginRequest loginRequest) {
        Member member=memberRepository.getMemberByLoginAndPassword(loginRequest.getLogin(),loginRequest.getPassword());
        MemberAuthenticatedTMResponse memberAuthenticatedTMResponse=teamMemTeamRepository.getTeamByMemberLogin(loginRequest.getLogin());
        return MemberResponseFactory.createMemberAuthenticatedResponse(member,memberAuthenticatedTMResponse);
    }
}
