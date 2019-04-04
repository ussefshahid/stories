package com.sqli.stories.helpers.factory;

import com.sqli.stories.helpers.payload.MemberAuthenticatedResponse;
import com.sqli.stories.helpers.payload.SigninResponse;

public class SigninResponseFactory {
    private SigninResponseFactory(){}
    public static SigninResponse createSigninResponse(MemberAuthenticatedResponse response,boolean success){
        return new SigninResponse(success,response);
    }
}
