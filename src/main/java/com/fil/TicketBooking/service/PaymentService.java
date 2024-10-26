package com.fil.TicketBooking.service;

import com.fil.TicketBooking.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
//    List<Payment> getPaymentsByTicketId(Long ticketId);
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
}