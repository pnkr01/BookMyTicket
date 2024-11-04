//package com.fil.TicketBooking.controller;
////import com.fil.TicketBooking.model.Payment;
////import com.fil.TicketBooking.service.PaymentService;
////import com.fil.TicketBooking.serviceimpl.PaymentServiceImpl;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////@RestController
////@RequestMapping("/api/payments")
////public class PaymentController {
////    @Autowired
////    private PaymentServiceImpl paymentService;
////
////    @GetMapping("/get-all-payment")
////    public ResponseEntity<List<Payment>> getAllPayments() {
////        List<Payment> payments = paymentService.getAllPayments();
////        return new ResponseEntity<>(payments, HttpStatus.OK);
////    }
////
////    @GetMapping("/get-payment-by-id/{id}")
////    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
////        Payment payment = paymentService.getPaymentById(id);
////        return new ResponseEntity<>(payment, HttpStatus.OK);
////    }
////
////    @PostMapping("/create-payment")
////    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
////        Payment createdPayment = paymentService.createPayment(payment);
////        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
////    }
////
////    @PutMapping("/update-payment-by-id/{id}")
////    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
////        Payment updatedPayment = paymentService.updatePayment(id, payment);
////        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
////    }
////
////    @DeleteMapping("/delete-payment-by-id/{id}")
////    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
////        paymentService.deletePayment(id);
////        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////    }
////}
//
//
//import com.fil.TicketBooking.errors.UserException;
//import com.fil.TicketBooking.model.Payment;
//import com.fil.TicketBooking.repository.PaymentRepository;
//import com.fil.TicketBooking.service.PaymentService;
//import com.fil.TicketBooking.service.UserService;
//import com.razorpay.PaymentLink;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.view.RedirectView;
//
//@RestController
//@RequestMapping("/api")
//public class PaymentController {
//
//    @Value("${razorpay.api.key}")
//    private String apiKey;
//
//    @Value("${razorpay.api.secret}")
//    private String apiSecret;
//
//    private PaymentService paymentService;
//    private UserService userService;
//    private PaymentRepository paymentRepository;
//
//    public PaymentController(PaymentService paymentService,UserService userService,PaymentRepository paymentRepository) {
//        this.paymentService=paymentService;
//        this.userService=userService;
//        this.paymentRepository=paymentRepository;
//    }
//
//    @PostMapping("/payments/{orderId}")
//    public ResponseEntity<PaymentLinkResponse>createPaymentLink(@PathVariable Long orderId,
//                                                                @RequestHeader("Authorization")String jwt)
//            throws RazorpayException, UserException, OrderException{
//
//        Payment payment=paymentService.findOrderById(orderId);
//        try {
//            // Instantiate a Razorpay client with your key ID and secret
//            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
//
//            // Create a JSON object with the payment1 link request parameters
//            JSONObject paymentLinkRequest = new JSONObject();
//            paymentLinkRequest.put("amount",payment.getTotalPrice()* 100);
//            paymentLinkRequest.put("currency","INR");
////		      paymentLinkRequest.put("expire_by",1691097057);
////		      paymentLinkRequest.put("reference_id",order.getId().toString());
//
//
//            // Create a JSON object with the customer details
//            JSONObject customer = new JSONObject();
//            customer.put("name",payment.getUser().getFirstName()+" "+payment.getUser().getLastName());
//            customer.put("contact",payment.getUser().getMobile());
//            customer.put("email",payment.getUser().getEmail());
//            paymentLinkRequest.put("customer",customer);
//
//            // Create a JSON object with the notification settings
//            JSONObject notify = new JSONObject();
//            notify.put("sms",true);
//            notify.put("email",true);
//            paymentLinkRequest.put("notify",notify);
//
//            // Set the reminder settings
//            paymentLinkRequest.put("reminder_enable",true);
//
//            // Set the callback URL and method
//            paymentLinkRequest.put("callback_url","http://localhost:4200/payment-success?order_id="+orderId);
//            paymentLinkRequest.put("callback_method","get");
//
//            // Create the payment1 link using the paymentLink.create() method
//            PaymentLink payment1 = razorpay.paymentLink.create(paymentLinkRequest);
//
//            String paymentLinkId = payment1.get("id");
//            String paymentLinkUrl = payment1.get("short_url");
//
//            PaymentLinkResponse res=new PaymentLinkResponse(paymentLinkUrl,paymentLinkId);
//
//            PaymentLink fetchedPayment = razorpay.paymentLink.fetch(paymentLinkId);
//
//            payment.setOrderId(fetchedPayment.get("order_id"));
//            paymentRepository.save(payment);
//
//            // Print the payment1 link ID and URL
//            System.out.println("Payment link ID: " + paymentLinkId);
//            System.out.println("Payment link URL: " + paymentLinkUrl);
//            System.out.println("Order Id : "+fetchedPayment.get("order_id")+fetchedPayment);
//
//            return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.ACCEPTED);
//
//        } catch (RazorpayException e) {
//
//            System.out.println("Error creating payment link: " + e.getMessage());
//            throw new RazorpayException(e.getMessage());
//        }
//
//
////		order_id
//    }
//
//    @GetMapping("/payments")
//    public ResponseEntity<ApiResponse> redirect(@RequestParam(name="payment_id") String paymentId,@RequestParam("order_id")Long orderId) throws RazorpayException, OrderException {
//        RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
//        Payment order =paymentService.findOrderById(orderId);
//
//        try {
//
//
//            Payment payment = razorpay.payments.fetch(paymentId);
//            System.out.println("payment details --- "+payment+payment.get("status"));
//
//            if(payment.get("status").equals("captured")) {
//                System.out.println("payment details --- "+payment+payment.get("status"));
//
//                order.getPaymentDetails().setPaymentId(paymentId);
//                order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
//                order.setOrderStatus(OrderStatus.PLACED);
////			order.setOrderItems(order.getOrderItems());
//                System.out.println(order.getPaymentDetails().getStatus()+"payment status ");
//                orderRepository.save(order);
//            }
//            ApiResponse res=new ApiResponse("your order get placed", true);
//            return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
//
//        } catch (Exception e) {
//            System.out.println("errrr payment -------- ");
//            new RedirectView("https://shopwithzosh.vercel.app/payment/failed");
//            throw new RazorpayException(e.getMessage());
//        }
//
//    }
//
//}

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
    private UserService userService;
    @Autowired
    private PaymentRepository paymentRepository;
    @PostMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long paymentId, @RequestHeader("Authorization")String jwt) throws PaymentException, RazorpayException {
        Payment payment = paymentService.findPaymentById(paymentId);
        try{
            RazorpayClient razorpayClient = new RazorpayClient(apiKey,apiSecret);
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",payment.getAmount()*100);
            paymentLinkRequest.put("currency","INR");
           // JSONObject customer = new JSONObject();

            JSONObject customer = new JSONObject();
            customer.put("name",payment.getTicketBooking().getUser().getName());
            customer.put("contact",payment.getTicketBooking().getUser().getPhone());
            customer.put("email",payment.getTicketBooking().getUser().getEmail());
            paymentLinkRequest.put("customer",customer);
            // Create a JSON object with the notification settings
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            // Set the reminder settings
            paymentLinkRequest.put("reminder_enable",true);

            // Set the callback URL and method
            paymentLinkRequest.put("callback_url","http://localhost:4200/payment-success?order_id="+paymentId);
            paymentLinkRequest.put("callback_method","get");
            // Create the payment link using the paymentLink.create() method
            PaymentLink payment1 = razorpayClient.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = payment1.get("id");
            String paymentLinkUrl = payment1.get("short_url");

            PaymentLinkResponse res=new PaymentLinkResponse(paymentLinkUrl,paymentLinkId);

            PaymentLink fetchedPayment = razorpayClient.paymentLink.fetch(paymentLinkId);

            payment.setPaymentId(fetchedPayment.get("order_id"));
            paymentRepository.save(payment);
            // Print the payment link ID and URL
            System.out.println("Payment link ID: " + paymentLinkId);
            System.out.println("Payment link URL: " + paymentLinkUrl);
            System.out.println("Order Id : "+fetchedPayment.get("order_id")+fetchedPayment);

            return new ResponseEntity<PaymentLinkResponse>(res,HttpStatus.ACCEPTED);


        }
        catch(Exception e){
            System.out.println("Error creating payment link: " + e.getMessage());
            throw new RazorpayException(e.getMessage());
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
