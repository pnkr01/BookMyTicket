//package com.fil.TicketBooking.serviceimpl;
//import com.fil.TicketBooking.dto.EventDTO;
//import com.fil.TicketBooking.dto.LocationDTO;
//import com.fil.TicketBooking.model.Event;
//import com.fil.TicketBooking.model.TicketPricing;
//import com.fil.TicketBooking.repository.EventRepository;
//import com.fil.TicketBooking.service.EventService;
//import jakarta.transaction.Transactional;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class EventServiceImpl implements EventService {
//
//    private final EventRepository eventRepository;
//
//    @Autowired
//    public EventServiceImpl(EventRepository eventRepository) {
//        this.eventRepository = eventRepository;
//    }
//
//    @Transactional
//    @Override
//    public Event createEvent(Event event) {
//        for (TicketPricing ticketPricing : event.getTicketPricings()) {
//            ticketPricing.setPlace(event);
//        }
//        return eventRepository.save(event);
//    }
//
//    @Override
//    public Event updateEvent(Long id, Event event) {
//        Optional<Event> existingEvent = eventRepository.findById(id);
//        if (existingEvent.isPresent()) {
//            event.setPlaceId(id);
//            return eventRepository.save(event);
//        }
//        return null; // or throw an exception
//    }
//
//    @Override
//    public void deleteEvent(Long id) {
//        eventRepository.deleteById(id);
//    }
//
//    @Override
//    public Event getEventById(Long id) {
//        return eventRepository.findById(id).orElse(null);
//    }
//
//    public List<EventDTO> getOngoingEvents() {
//        Date currentDate = new Date(System.currentTimeMillis());
//        List<Event> ongoingEvents = eventRepository.findOngoingEvents(currentDate);
//        return getEventDTOS(ongoingEvents);
//    }
//
//    @NotNull
//    private List<EventDTO> getEventDTOS(List<Event> ongoingEvents) {
//        return ongoingEvents.stream()
//                .map(event -> new EventDTO(
//                        event.getPlaceId(),
//                        event.getPlaceName(),
//                        event.getDescription(),
//                        event.getMaxTicket(),
//                        event.getSoldTicket(),
//                        event.getTicketPrice(),
//                        event.getOpenTiming(),
//                        event.getOpenDays(),
//                        event.getCreatedAt(),
//                        event.getUpdatedAt(),
//                        event.getEventFromDate(),
//                        event.getEventToDate(),
//                        new LocationDTO(
//                                event.getLocation().getLocationId(),
//                                event.getLocation().getLocationName(),
//                                event.getLocation().getDescription()
//                        )
//                ))
//                .collect(Collectors.toList());
//    }
//
//    public List<EventDTO> getUpcomingEvents() {
//        Date currentDate = new Date(System.currentTimeMillis());
//        List<Event> ongoingEvents = eventRepository.findUpcomingEvents(currentDate);
//        return getEventDTOS(ongoingEvents);
//    }
//
//    @Override
//    public List<Event> getAllEvents() {
//        return eventRepository.findAll();
//    }
//
//    @Override
//    public List<EventDTO> getTop5SoldEvents() {
//        List<Event> topEvents = eventRepository.findTop5BySoldTicket();
//        return getEventDTOS(topEvents);
//    }
//}



package com.fil.TicketBooking.serviceimpl;

import com.fil.TicketBooking.dto.EventDTO;
import com.fil.TicketBooking.dto.LocationDTO;
import com.fil.TicketBooking.model.Event;
import com.fil.TicketBooking.model.TicketPricing;
import com.fil.TicketBooking.repository.EventRepository;
import com.fil.TicketBooking.service.EventService;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    @Override
    public Event createEvent(Event event) {
        for (TicketPricing ticketPricing : event.getTicketPricings()) {
            ticketPricing.setPlace(event);
        }
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            event.setPlaceId(id);
            return eventRepository.save(event);
        }
        return null; // or throw an exception
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @NotNull
    public EventDTO convertToEventDTO(Event event) {
        return new EventDTO(
                event.getPlaceId(),
                event.getPlaceName(),
                event.getDescription(),
                event.getMaxTicket(),
                event.getSoldTicket(),
                event.getTicketPrice(),
                event.getOpenTiming(),
                event.getOpenDays(),
                event.getCreatedAt(),
                event.getUpdatedAt(),
                event.getEventFromDate(),
                event.getEventToDate(),
                new LocationDTO(
                        event.getLocation().getLocationId(),
                        event.getLocation().getLocationName(),
                        event.getLocation().getDescription()
                )
        );
    }

    @Override
    public Page<EventDTO> getTopSoldEvents(Pageable pageable) {
        return eventRepository.findTopSoldEvents(pageable).map(this::convertToEventDTO);
    }

    @Override
    public Page<EventDTO> getOngoingEvents(Pageable pageable) {
        Date currentDate = new Date(System.currentTimeMillis());
        return eventRepository.findOngoingEvents(currentDate, pageable).map(this::convertToEventDTO);
    }

    @Override
    public Page<EventDTO> getUpcomingEvents(Pageable pageable) {
        Date currentDate = new Date(System.currentTimeMillis());
        return eventRepository.findUpcomingEvents(currentDate, pageable).map(this::convertToEventDTO);
    }

    @Override
    public List<EventDTO> searchEventsByLocation(String locationName) {
        List<Event> events = eventRepository.searchByLocationName(locationName);
       return events.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }
}
