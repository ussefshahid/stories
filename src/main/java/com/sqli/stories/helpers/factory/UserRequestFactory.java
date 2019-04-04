package com.sqli.stories.helpers.factory;

import com.sqli.stories.helpers.payload.UserRequest;

public class UserRequestFactory {
    private UserRequestFactory(){}
    public static UserRequest createUserRequest(String email,String password,String firstname,String lastname){
        return new UserRequest(email,password,firstname,lastname);
    }
}
