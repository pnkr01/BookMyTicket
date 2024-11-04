
package com.fil.TicketBooking.service;

import com.fil.TicketBooking.exception.PaymentException;
import com.fil.TicketBooking.model.Payment;

import java.util.List;

//
////import com.fil.TicketBooking.model.Payment;
////
////import java.util.List;
////
////public interface PaymentService {
////    Payment createPayment(Payment payment);
//////    List<Payment> getPaymentsByTicketId(Long ticketId);
////    Payment updatePayment(Long id, Payment payment);
////    void deletePayment(Long id);
////    Payment getPaymentById(Long id);
////    List<Payment> getAllPayments();
////}
////package com.zosh.service;
////
////        import java.util.List;
////
////        import com.zosh.exception.OrderException;
////        import com.zosh.modal.Address;
////        import com.zosh.modal.Order;
////        import com.zosh.modal.User;
//
//import com.fil.TicketBooking.exception.PaymentException;
//import com.fil.TicketBooking.model.Payment;
//import com.fil.TicketBooking.model.User;
//
//import java.util.List;
//
//public interface PaymentService {
//    Payment createPaymentForTicket(Long ticketId) throws PaymentException;
//    public Payment createOrder(User user);
//
//    public Payment findPaymentById(Long orderId) throws PaymentException;
//
//    public List<Payment> usersOrderHistory(Long userId);
//
//    public Payment placedOrder(Long orderId) throws PaymentException;
//
//    public Payment confirmedOrder(Long orderId)throws PaymentException;
//
//    public Payment shippedOrder(Long orderId) throws PaymentException;
//
//    public Payment deliveredOrder(Long orderId) throws PaymentException;
//
//    public Payment cancledOrder(Long orderId) throws PaymentException;
//
//    public List<Payment>getAllOrders();
//
//    public void deleteOrder(Long orderId) throws PaymentException;
//
//}
public interface PaymentService {
    Payment createPaymentForTicket(Long ticketId) throws PaymentException; // New method to create payment for a ticket
    // Other existing methods...
    Payment findPaymentById(Long orderId) throws PaymentException;
    List<Payment> usersOrderHistory(Long userId);
    void deleteOrder(Long orderId) throws PaymentException;
}
