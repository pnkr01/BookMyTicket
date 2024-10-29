package com.fil.TicketBooking.controller;

import com.fil.TicketBooking.dto.TicketBookingDTO;
import com.fil.TicketBooking.model.TicketBooking;
import com.fil.TicketBooking.service.TicketBookingService;
import com.fil.TicketBooking.serviceimpl.TicketBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticketBookings")
public class TicketBookingController {
    @Autowired
    private TicketBookingServiceImpl ticketBookingService;

    @GetMapping("/get-all-ticket-bookings")
    public ResponseEntity<List<TicketBooking>> getAllTicketBookings() {
        List<TicketBooking> ticketBookings = ticketBookingService.getAllTicketBookings();
        return new ResponseEntity<>(ticketBookings, HttpStatus.OK);
    }

    @GetMapping("/get-ticket-booking-by-id/{id}")
    public ResponseEntity<TicketBooking> getTicketBookingById(@PathVariable Long id) {
        TicketBooking ticketBooking = ticketBookingService.getTicketBookingById(id);
        return new ResponseEntity<>(ticketBooking, HttpStatus.OK);
    }

    @PostMapping("/create-ticket-booking")
    public ResponseEntity<Object> createTicketBooking(@RequestBody TicketBooking ticketBooking) {
        TicketBookingDTO createdTicketBooking = ticketBookingService.bookEvent(ticketBooking);
        return new ResponseEntity<>(createdTicketBooking, HttpStatus.CREATED);
    }

    @PutMapping("/update-ticket-by-id/{id}")
    public ResponseEntity<TicketBooking> updateTicketBooking(@PathVariable Long id, @RequestBody TicketBooking ticketBooking) {
        TicketBooking updatedTicketBooking = ticketBookingService.updateTicketBooking(id, ticketBooking);
        return new ResponseEntity<>(updatedTicketBooking, HttpStatus.OK);
    }

    @DeleteMapping("/delete-ticket-by-id/{id}")
    public ResponseEntity<Void> deleteTicketBooking(@PathVariable Long id) {
        ticketBookingService.deleteTicketBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
