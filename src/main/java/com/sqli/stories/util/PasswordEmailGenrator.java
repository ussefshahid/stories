package com.sqli.stories.util;

public interface PasswordEmailGenrator {
    String generateMemberPassword();
    String generateMemberMailFromLogin(String login);
}
