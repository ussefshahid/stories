package com.sqli.stories.util.impl;

import com.sqli.stories.util.PasswordEmailGenrator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class DefaultPasswordGenerator implements PasswordEmailGenrator {
    private static final int PASSWORD_LENGTH=12;
    private static final  boolean USELETTERS=true;
    private static final boolean USENUMBERS=true;
    private static final String SQLI_MAIL_DOMAIN="@sqli.com";

    @Override
    public String generateMemberPassword() {
        return RandomStringUtils.random(PASSWORD_LENGTH,USELETTERS,USENUMBERS);
    }

    @Override
    public String generateMemberMailFromLogin(String login) {
        return login+""+SQLI_MAIL_DOMAIN;
    }
}
