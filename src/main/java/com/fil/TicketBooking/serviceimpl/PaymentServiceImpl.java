package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.enums.PaymentMethod;
import com.fil.TicketBooking.enums.PaymentStatus;
import com.fil.TicketBooking.model.Payment;
import com.fil.TicketBooking.model.TicketBooking;
import com.fil.TicketBooking.repository.PaymentRepository;
import com.fil.TicketBooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(TicketBooking ticketBooking) {
        //send to payment db
        Payment payment = createPaymentInstance();
        payment.setTicketBooking(ticketBooking);
        payment.setPaymentMethod(PaymentMethod.UPI);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setAmount(ticketBooking.getTotalPrice()*ticketBooking.getTotalMember());
        payment.setTransactionId(Long.valueOf(UUID.randomUUID().toString()));
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isPresent()) {
            payment.setPaymentId(id);
            return paymentRepository.save(payment);
        }
        return null; // or throw an exception
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Lookup
    protected Payment createPaymentInstance() {
        // Spring will override this method to return a new Payment instance
        return null; // This will be overridden by Spring
    }

}
