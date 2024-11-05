package com.fil.TicketBooking.controller;
import com.fil.TicketBooking.enums.PaymentStatus;
import com.fil.TicketBooking.exception.PaymentException;
import com.fil.TicketBooking.model.Payment;
import com.fil.TicketBooking.repository.PaymentRepository;
import com.fil.TicketBooking.response.ApiResponse;
import com.fil.TicketBooking.response.PaymentLinkResponse;
import com.fil.TicketBooking.service.PaymentService;
import com.fil.TicketBooking.service.UserService;
import com.fil.TicketBooking.serviceimpl.PaymentServiceImpl;
import com.razorpay.PaymentLink;
import com.razorpay.PaymentLinkClient;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("{razorpay.api.key.id}")
    String apiKey;

    @Value("{razorpay.api.key.secret}")
    String apiSecret;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private Environment environment;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/book-ticket/{ticketId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long ticketId, @RequestHeader("Authorization")String jwt) throws PaymentException, RazorpayException {
try{
        Payment payment = paymentService.createPaymentForTicket(ticketId);

        String apiKey = environment.getProperty("razorpay.api.key.id");
        String apiSecret = environment.getProperty("razorpay.api.secret");
        // Step 2: Create the payment link
        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_QAoLCRLU1ae6Xq", "Ec4Ok8I4ruDoewKq4GpdmRzU");
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", payment.getAmount() * 100);
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("callback_url", "http://localhost:4200/payment-success?order_id=" + ticketId);
        paymentLinkRequest.put("callback_method", "get");
        // Create the payment link using the paymentLink.create() method
        PaymentLink payment1 = razorpayClient.paymentLink.create(paymentLinkRequest);

        String paymentLinkId = payment1.get("id");
        String paymentLinkUrl = payment1.get("short_url");

        PaymentLinkResponse res = new PaymentLinkResponse(paymentLinkUrl, paymentLinkId);

        PaymentLink fetchedPayment = razorpayClient.paymentLink.fetch(paymentLinkId);

        payment.setPaymentId(fetchedPayment.get("order_id"));
        paymentRepository.save(payment);
        // Print the payment link ID and URL
        System.out.println("Payment link ID: " + paymentLinkId);
        System.out.println("Payment link URL: " + paymentLinkUrl);
        System.out.println("Order Id : " + fetchedPayment.get("order_id") + fetchedPayment);

        return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.ACCEPTED);
    }
    catch(Exception e){
        System.out.println("Error creating payment link: " + e.getMessage());
        throw new RazorpayException(e.getMessage());
    }
    }
        @PostMapping("/send-email")
        public void sendEmail(@RequestParam String email) {
            paymentService.sendEmail(email);
        }

        @PutMapping("/update-payment-by-id/{id}")
        public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
            Payment updatedPayment = paymentService.updatePayment(id, payment);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        }

        @GetMapping("/payments")
        public ResponseEntity<ApiResponse> redirect(@RequestParam(name="payment_id") String paymentId, @RequestParam("order_id")Long orderId) throws RazorpayException, PaymentException, PaymentException {
            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
            Payment order =paymentService.findPaymentById(orderId);

            try {


                com.razorpay.Payment payment = razorpay.payments.fetch(paymentId);
                System.out.println("payment details --- "+payment+payment.get("status"));

                if(payment.get("status").equals("captured")) {
                    System.out.println("payment details --- "+payment+payment.get("status"));

                    order.setPaymentId(Long.valueOf(paymentId));
                    order.setPaymentStatus(PaymentStatus.SUCCESS);
                    //    order.setPaymentStatus(PaymentStatus.PLACED);
//			order.setOrderItems(order.getOrderItems());
                    System.out.println(order.getPaymentStatus()+"payment status ");
                    paymentRepository.save(order);
                }
                ApiResponse res=new ApiResponse("your order get placed", true);
                return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);

            } catch (Exception e) {
                System.out.println("errrr payment -------- ");
                new RedirectView("https://shopwithzosh.vercel.app/payment/failed");
                throw new RazorpayException(e.getMessage());
            }

        }

    }