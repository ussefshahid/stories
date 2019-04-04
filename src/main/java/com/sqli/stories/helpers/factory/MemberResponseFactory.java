package com.sqli.stories.helpers.factory;

import com.sqli.stories.entities.Member;
import com.sqli.stories.helpers.payload.MemberAuthenticatedResponse;
import com.sqli.stories.helpers.payload.MemberAuthenticatedTMResponse;

public class MemberResponseFactory {
    private MemberResponseFactory(){}
    public static MemberAuthenticatedResponse createMemberAuthenticatedResponse(Member member, MemberAuthenticatedTMResponse tmResponse){
        if(tmResponse==null || member==null) return null;
        return new MemberAuthenticatedResponse(member.getLogin(),
                                               member.getFirstName(),
                                               member.getLastName(),
                                               tmResponse.getTeam(),
                                               member.getDateEntreeProjet(),
                                               tmResponse.getDateEntree());
    }
}
