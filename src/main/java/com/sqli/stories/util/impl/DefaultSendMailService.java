package com.sqli.stories.util.impl;

import com.sqli.stories.helpers.payload.UserRequest;
import com.sqli.stories.util.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class DefaultSendMailService implements SendMailService {


    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    @Autowired
    private JavaMailSender javaMailSender;

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     *
     * @param javaMailSender
     */

    /**
     * This function is used to send mail without attachment.
     * @param userRequest
     * @throws MailException
     */
    @Override
    public void sendEmail(UserRequest userRequest) throws MailException {

        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function. SimpleMailMessage Object is required because send() function uses
         * object of SimpleMailMessage as a Parameter
         */
        String content="Hello ,"+userRequest.getLastname()+" "+userRequest.getFirstname()+"\n"
                               +"Your information to authenticate to Stories SQLI APP : \n"
                               +"Email : "+userRequest.getEmail()+"\n"
                               +"Password : "+userRequest.getPassword()+"\n\n"
                               +"You can change your password from user profile page after been authenticated  ! \n\n\n"
                               +"Best regards SQLI";


        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(userRequest.getEmail());
        mail.setSubject("Your AUTHENTICATION INFORMATION TO STORIES APP ");
        mail.setText(content);

        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }

    /**
     * This fucntion is used to send mail that contains a attachment.
     *
     * @param userRequest
     * @throws MailException
     * @throws MessagingException
     */
    @Override
    public void sendEmailWithAttachment(UserRequest userRequest) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(userRequest.getEmail());
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");

        FileSystemResource file = new FileSystemResource("/home/rockhard/Desktop/Registration.pdf");
        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(mimeMessage);
    }

}
