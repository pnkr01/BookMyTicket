package com.fil.TicketBooking.service;

import java.io.File;

public interface EmailService {
    void sendEmail(String to, String subject, String msg);

    void sendEmail(String[] to, String subject, String msg);

    void sendEmailWithHtml(String to, String subject, String htmlContent);


    void sendEmailWithFile(String to, String subject, String msg, File file);
}

