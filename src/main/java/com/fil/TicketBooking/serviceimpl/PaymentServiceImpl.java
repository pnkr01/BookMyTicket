package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.enums.PaymentMethod;
import com.fil.TicketBooking.enums.PaymentStatus;
import com.fil.TicketBooking.exception.PaymentException;
import com.fil.TicketBooking.model.Mail;
import com.fil.TicketBooking.model.Payment;
import com.fil.TicketBooking.model.TicketBooking;
import com.fil.TicketBooking.repository.PaymentRepository;
import com.fil.TicketBooking.service.PaymentService;
import com.fil.TicketBooking.service.TicketBookingService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private MailServiceImpl mailService;


    @Autowired
    private final JavaMailSender javaMailSender;

    private Mail mail;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketBookingService ticketBookingService; // Assuming you have a service for ticket bookings
    @Autowired
    private MailServiceImpl mailServiceImpl;

    public PaymentServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public Payment createPaymentForTicket(Long ticketId) throws PaymentException {
        TicketBooking ticketBooking = ticketBookingService.findTicketBookingById(ticketId); // Get ticket booking details
        if (ticketBooking == null) {
            throw new PaymentException("Ticket not found for ID: " + ticketId);
        }

        Payment payment = new Payment();
        payment.setTicketBooking(ticketBooking);
        payment.setAmount(ticketBooking.getTotalPrice()); // Assuming TicketBooking has an amount field
        payment.setPaymentMethod(PaymentMethod.CREDIT); // Set payment method as Razorpay
        payment.setPaymentStatus(PaymentStatus.PENDING); // Set initial status to pending
        payment.setRazorpayOrderId(""); // Initialize Razorpay order ID
        payment.setTransactionId(12L);
        return paymentRepository.save(payment); // Save the payment
    }

    @Override
    public void sendEmail(String email, int ticketId, int noOfPass){
        Mail mail = new Mail();
        mail.setMailFrom("infokumar66@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Payment Confirmation for Ticket Booking");
        mail.setMailContent("Hi" +
                "Your payment for ticket ID " + ticketId +
                " was successful. \n\nThank you for booking with us!\n\n");
        mailService.sendEmail(mail);

//        // Create and populate the Mail object
//        Mail mail = new Mail();
//        mail.setMailFrom("infokumar66@gmail.com"); // Set the 'from' address
//        mail.setMailTo(email); // Set the 'to' address
//        mail.setMailSubject("Payment Confirmation for Ticket Booking"); // Set the subject
//        mail.setMailContent("Dear User, <br>Your payment for the ticket has been successfully received.<br>Below is your QR Code:<br><img src='cid:qrCodeImage' alt='QR Code'/> <br>Thank you for booking with us!"); // Set the content
//
//        // Send email
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//
//            mimeMessageHelper.setSubject(mail.getMailSubject());
//            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
//            mimeMessageHelper.setTo(mail.getMailTo());
//
//            // Set email body as HTML content
//            mimeMessageHelper.setText(mail.getMailContent(), true);
//
//            // Embed the QR code image as an inline resource
//            if (qrCodeBase64 != null) {
//                byte[] qrImage = Base64.getDecoder().decode(qrCodeBase64);
//                mimeMessageHelper.addInline("qrCodeImage", new ByteArrayResource(qrImage), "image/png");
//            }
//
//            // Send the email
//            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        }




//    @Override
//    public void sendEmail(String email) {
//        mail.setMailFrom("infokumar66@gmail.com");
//        mail.setMailTo(email);
//        mail.setMailSubject("Ticket Booking Confirmation");
//        mail.setMailSubject("Payment Confirmation for Ticket Booking");
//        mail.setMailContent("Dear Pawan" +
//                "Your payment for ticket ID " + 1212 +
//                " was successful. \nThank you for booking with us!");
//        mailService.sendEmail(mail);
//    }

    @Override
    public Payment findPaymentById(Long orderId) throws PaymentException {
        return null;
    }

    @Override
    public List<Payment> usersOrderHistory(Long userId) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws PaymentException {

    }

    // Implement other methods...
}

