package com.fil.TicketBooking.serviceimpl;

        import com.fil.TicketBooking.enums.PaymentMethod;
        import com.fil.TicketBooking.enums.PaymentStatus;
        import com.fil.TicketBooking.exception.PaymentException;
        import com.fil.TicketBooking.model.Payment;
        import com.fil.TicketBooking.model.TicketBooking;
        import com.fil.TicketBooking.repository.PaymentRepository;
        import com.fil.TicketBooking.service.PaymentService;
        import com.fil.TicketBooking.service.TicketBookingService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketBookingService ticketBookingService; // Assuming you have a service for ticket bookings

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

        return paymentRepository.save(payment); // Save the payment
    }

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

