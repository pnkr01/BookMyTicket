package com.fil.TicketBooking.service;

import com.fil.TicketBooking.exception.PaymentException;
import com.fil.TicketBooking.model.Payment;

import java.util.List;


    public interface PaymentService {
        Payment createPaymentForTicket(Long ticketId) throws PaymentException; // New method to create payment for a ticket
        // Other existing methods...
        Payment findPaymentById(Long orderId) throws PaymentException;
        List<Payment> usersOrderHistory(Long userId);
        void deleteOrder(Long orderId) throws PaymentException;
    }
