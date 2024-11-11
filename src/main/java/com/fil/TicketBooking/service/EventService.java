//package com.fil.TicketBooking.service;
//
//import com.fil.TicketBooking.dto.EventDTO;
//import com.fil.TicketBooking.model.Event;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//public interface EventService {
//    Event createEvent(Event event);
//    Event updateEvent(Long id, Event event);
//    void deleteEvent(Long id);
//    Event getEventById(Long id);
//    List<Event> getAllEvents();
//    List<EventDTO> getTop5SoldEvents();
//    List<EventDTO> getOngoingEvents();
//    List<EventDTO> getUpcomingEvents();
//
//     Page<EventDTO> getTopSoldEvents(Pageable pageable);
//
//     Page<EventDTO> getOngoingEvents(Pageable pageable);
//
//     Page<EventDTO> getUpcomingEvents(Pageable pageable);
//}


package com.fil.TicketBooking.service;

import com.fil.TicketBooking.dto.EventDTO;
import com.fil.TicketBooking.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
    Event getEventById(Long id);
    Page<EventDTO> getTopSoldEvents(String city,Pageable pageable);
    Page<EventDTO> getUpcomingEvents(String city,Pageable pageable);
    Page<EventDTO> getOngoingEvents(String city, Pageable pageable);
    List<EventDTO> searchEventsByLocation(String locationName);
    List<EventDTO> getEventsByUserId(Long userId);
}
