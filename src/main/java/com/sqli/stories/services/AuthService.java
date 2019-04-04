package com.sqli.stories.services;

import com.sqli.stories.helpers.payload.LoginRequest;
import com.sqli.stories.helpers.payload.MemberAuthenticatedResponse;

public interface AuthService {
    MemberAuthenticatedResponse login(LoginRequest loginRequest);
}
