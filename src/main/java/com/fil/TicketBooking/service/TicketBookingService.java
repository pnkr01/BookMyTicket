package com.fil.TicketBooking.service;

import com.fil.TicketBooking.dto.TicketBookingDTO;
import com.fil.TicketBooking.model.TicketBooking;

import java.util.List;

public interface TicketBookingService {
    TicketBookingDTO bookEvent(TicketBooking ticketBooking);
    TicketBooking updateTicketBooking(Long id, TicketBooking ticketBooking);
    void deleteTicketBooking(Long id);
    TicketBooking findTicketBookingById(Long id);
    List<TicketBooking> getAllTicketBookings();

  //  TicketBooking findTicketBookingById(Long ticketId);
}