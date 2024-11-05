package com.fil.TicketBooking.serviceimpl;

import com.fil.TicketBooking.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;

public class EmailServiceImpl implements EmailService {
    private JavaMailSender mailSender;
    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String to, String subject, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
          simpleMailMessage.setSubject(subject);
          simpleMailMessage.setText(msg);
        mailSender.send(simpleMailMessage);
        logger.info("email has been sent...");
    }

    @Override
    public void sendEmail(String[] to, String subject, String msg) {

    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {

    }

    @Override
    public void sendEmailWithFile(String to, String subject, String msg, File file) {

    }
}
