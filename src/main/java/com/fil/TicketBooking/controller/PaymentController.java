package com.fil.TicketBooking.controller;


import com.fil.TicketBooking.enums.PaymentStatus;
import com.fil.TicketBooking.exception.PaymentException;
import com.fil.TicketBooking.model.Payment;
import com.fil.TicketBooking.repository.PaymentRepository;
import com.fil.TicketBooking.response.PaymentLinkResponse;
import com.fil.TicketBooking.service.PaymentService;
import com.fil.TicketBooking.service.TicketBookingService;
import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private Environment environment;

    @Autowired
    private TicketBookingService ticketBookingService; // Assuming this service handles ticket booking
    @Value("{razorpay.api.key.id}")
    String apiKey;
    @Autowired
    private PaymentRepository paymentRepository;
    @Value("{razorpay.api.key.secret}")
    String apiSecret;

    @PostMapping("/book-ticket/{ticketId}")
    public ResponseEntity<PaymentLinkResponse> bookTicketAndCreatePayment(@PathVariable Long ticketId, @RequestHeader("Authorization") String jwt) throws PaymentException, RazorpayException, PaymentException, RazorpayException {
        // Step 1: Create a payment for the ticket
        Payment payment = paymentService.createPaymentForTicket(ticketId);

        String apiKey = environment.getProperty("razorpay.api.key.id");
        String apiSecret = environment.getProperty("razorpay.api.secret");
        // Step 2: Create the payment link
        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_QAoLCRLU1ae6Xq", "Ec4Ok8I4ruDoewKq4GpdmRzU");
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", payment.getAmount()*100);
        paymentLinkRequest.put("currency", "INR");

        Order order = razorpayClient.orders.create(paymentLinkRequest);
//
        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
//
//        // Update the payment with Razorpay order ID
        payment.setRazorpayOrderId(order.get("id"));
        payment.setPaymentStatus(PaymentStatus.CREATED); // Update payment status
        paymentRepository.save(payment); // Save updated payment

        String paymentLinkUrl = paymentLink.get("short_url");
        PaymentLinkResponse response = new PaymentLinkResponse(paymentLinkUrl, paymentLink.get("id"));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Other methods...
}

