package com.sqli.stories.util;

import com.sqli.stories.helpers.payload.UserRequest;

import javax.mail.MessagingException;

public interface SendMailService {
   void sendEmail(UserRequest userRequest) ;
   void sendEmailWithAttachment(UserRequest userRequest) throws MessagingException;

}
