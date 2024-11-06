//package com.fil.TicketBooking.controller;
//import com.fil.TicketBooking.dto.EventDTO;
//import com.fil.TicketBooking.model.Event;
//import com.fil.TicketBooking.serviceimpl.EventServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/events")
//public class EventController {
//    @Autowired
//    private EventServiceImpl eventService;
//
//    @GetMapping("/get-all-events")
//    public ResponseEntity<List<Event>> getAllEvents() {
//        List<Event> events = eventService.getAllEvents();
//        return new ResponseEntity<>(events, HttpStatus.OK);
//    }
//
//    @GetMapping("/top-sold")
//    public ResponseEntity<Page<EventDTO>> getTopSoldEvents(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size) {
//
//        Page<EventDTO> topSoldEvents = eventService.getTopSoldEvents(PageRequest.of(page, size));
//        return ResponseEntity.ok(topSoldEvents);
//    }
//
//    @GetMapping("/ongoing")
//    public ResponseEntity<Page<EventDTO>> getOngoingEvents(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size) {
//
//        Page<EventDTO> ongoingEvents = eventService.getOngoingEvents(PageRequest.of(page, size));
//        return ResponseEntity.ok(ongoingEvents);
//    }
//
//    @GetMapping("/upcoming")
//    public ResponseEntity<Page<EventDTO>> getUpcomingEvents(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size) {
//
//        Page<EventDTO> upcomingEvents = eventService.getUpcomingEvents(PageRequest.of(page, size));
//        return ResponseEntity.ok(upcomingEvents);
//    }
//
//    @GetMapping("/get-event-by-id/{id}")
//    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
//        Event event = eventService.getEventById(id);
//        return new ResponseEntity<>(event, HttpStatus.OK);
//    }
//
//    @PostMapping("/create-event")
//    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
//        Event createdEvent = eventService.createEvent(event);
//        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update-event-by-id/{id}")
//    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
//        Event updatedEvent = eventService.updateEvent(id, event);
//        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete-event-by-id/{id}")
//    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
//        eventService.deleteEvent(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}



package com.fil.TicketBooking.controller;

import com.fil.TicketBooking.dto.EventDTO;
import com.fil.TicketBooking.model.Event;
import com.fil.TicketBooking.serviceimpl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @GetMapping("/top-sold")
    public ResponseEntity<Page<EventDTO>> getTopSoldEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<EventDTO> topSoldEvents = eventService.getTopSoldEvents(PageRequest.of(page, size));
        return ResponseEntity.ok(topSoldEvents);
    }

    @GetMapping("/ongoing")
    public ResponseEntity<Page<EventDTO>> getOngoingEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<EventDTO> ongoingEvents = eventService.getOngoingEvents(PageRequest.of(page, size));
        return ResponseEntity.ok(ongoingEvents);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<Page<EventDTO>> getUpcomingEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<EventDTO> upcomingEvents = eventService.getUpcomingEvents(PageRequest.of(page, size));
        return ResponseEntity.ok(upcomingEvents);
    }

    @GetMapping("/get-event-by-id/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/create-event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/update-event-by-id/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/delete-event-by-id/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
