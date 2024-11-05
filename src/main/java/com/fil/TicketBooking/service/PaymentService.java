package com.fil.TicketBooking.service;

import com.fil.TicketBooking.exception.PaymentException;
import com.fil.TicketBooking.model.Payment;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.model.TicketBooking;

import java.util.List;

public interface PaymentService {

    Payment createPaymentForTicket(Long ticketId) throws PaymentException; // New method to create payment for a ticket
    // Other existing methods...
    public Payment createOrder(User user);

    public Payment findPaymentById(Long orderId) throws PaymentException;

    public List<Payment> usersOrderHistory(Long userId);

    public Payment placedOrder(Long orderId) throws PaymentException;

    public Payment confirmedOrder(Long orderId)throws PaymentException;

    public Payment shippedOrder(Long orderId) throws PaymentException;

    public Payment deliveredOrder(Long orderId) throws PaymentException;

    public Payment cancledOrder(Long orderId) throws PaymentException;

    public List<Payment>getAllOrders();

    public void deleteOrder(Long orderId) throws PaymentException;

    Payment createPayment(Payment payment);
    void sendEmail(String  email);
    //    List<Payment> getPaymentsByTicketId(Long ticketId);
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
}