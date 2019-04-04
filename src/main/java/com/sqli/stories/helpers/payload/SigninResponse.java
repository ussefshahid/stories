package com.sqli.stories.helpers.payload;

public class SigninResponse {
    private boolean success;
    private MemberAuthenticatedResponse authMember;

    public SigninResponse(boolean success, MemberAuthenticatedResponse authMember) {
        this.success = success;
        this.authMember = authMember;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MemberAuthenticatedResponse getAuthMember() {
        return authMember;
    }

    public void setAuthMember(MemberAuthenticatedResponse authMember) {
        this.authMember = authMember;
    }
}


