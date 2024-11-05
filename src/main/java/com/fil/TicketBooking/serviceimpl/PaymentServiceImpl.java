package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.enums.PaymentStatus;
import com.fil.TicketBooking.exception.PaymentException;
import com.fil.TicketBooking.model.Mail;
import com.fil.TicketBooking.model.Payment;
import com.fil.TicketBooking.model.TicketBooking;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.repository.PaymentRepository;
import com.fil.TicketBooking.service.PaymentService;
import com.fil.TicketBooking.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private TicketBookingService ticketBookingService;
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
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
        // payment.setPaymentMethod(PaymentMethod.RAZORPAY); // Set payment method as Razorpay
        payment.setPaymentStatus(PaymentStatus.PENDING); // Set initial status to pending
        payment.setRazorpayOrderId(""); // Initialize Razorpay order ID

        return paymentRepository.save(payment); // Save the payment
    }

    @Override
    public Payment createOrder(User user) {
        return null;
    }

    @Override
    public Payment findPaymentById(Long orderId) throws PaymentException {
        return null;
    }

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private Mail mail;

    @Autowired
    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> usersOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Payment placedOrder(Long orderId) throws PaymentException {
        return null;
    }

    public Payment createPayment(Payment payment) {
        //call raxorpay get the payment then save it.
        Payment save = paymentRepository.save(payment);


        //here save will be coming from razorpay if its success then generate qr
//        //otherwise set the payment status coming from db.
//        if (save.getPaymentStatus() == PaymentStatus.SUCCESS) {
//            //call qr controller
//            //sent email to user.
//
//            sendEmailToThisUser(payment);
//        }else{
//            return save; //if status equals
//        }
        return save;

        //TODO
    }

    @Override
    public void sendEmail(String email) {
        mail.setMailFrom("infokumar66@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Ticket Booking Confirmation");
        mail.setMailSubject("Payment Confirmation for Ticket Booking");
        mail.setMailContent("Dear Pawan" +
                "Your payment for ticket ID " + 1212 +
                " was successful. \nThank you for booking with us!");
        mailService.sendEmail(mail);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
        return null;
    }

    @Override
    public void deletePayment(Long id) {

    }

    @Override
    public Payment getPaymentById(Long id) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return List.of();
    }

    @Override
    public Payment confirmedOrder(Long orderId) throws PaymentException {
        return null;
    }

    @Override
    public Payment shippedOrder(Long orderId) throws PaymentException {
        return null;
    }

    @Override
    public Payment deliveredOrder(Long orderId) throws PaymentException {
        return null;
    }

    @Override
    public Payment cancledOrder(Long orderId) throws PaymentException {
        return null;
    }

    @Override
    public List<Payment> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws PaymentException {

    }
}