package com.sqli.stories.helpers.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest  {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    public LoginRequest(){}
    public LoginRequest(@NotBlank String login, @NotBlank String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
