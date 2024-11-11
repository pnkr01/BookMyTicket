package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.dto.TicketBookingDTO;
import com.fil.TicketBooking.enums.UserStatus;
import com.fil.TicketBooking.model.Event;
import com.fil.TicketBooking.model.TicketBooking;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.repository.EventRepository;
import com.fil.TicketBooking.repository.TicketBookingRepository;
import com.fil.TicketBooking.repository.UserRepository;
import com.fil.TicketBooking.service.TicketBookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {

    @Autowired
    private final TicketBookingRepository ticketBookingRepository;
    @Autowired
    private final EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public TicketBookingServiceImpl(TicketBookingRepository ticketBookingRepository, EventRepository eventRepository) {
        this.ticketBookingRepository = ticketBookingRepository;
        this.eventRepository = eventRepository;
    }

//    @Transactional
//    @Override
//    public TicketBookingDTO bookEvent(TicketBooking ticketBooking) {
//        //first find the event
//        Optional<Event> eventOpt = eventRepository.findById(ticketBooking.getPlace().getPlaceId());
//        if (eventOpt.isPresent()) {
//            Event event = eventOpt.get();
//            if(event.getMaxTicket()-event.getSoldTicket()>=ticketBooking.getTotalMember()) {
//                ticketBooking.setPlace(event);
//                Optional<User> userOpt = userRepository.findById(ticketBooking.getUser().getUserId());
//                if (userOpt.isPresent()) {
//                    if (userOpt.get().getStatus() == null) {
//                        userOpt.get().setStatus(UserStatus.ACTIVE);
//                    }
//                    ticketBooking.setUser(userOpt.get());
//                } else {
//                    throw new RuntimeException("User not found.");
//                }
//                event.setSoldTicket(event.getSoldTicket() - ticketBooking.getTotalMember());
//                userOpt.get().getTicketBookings().add(ticketBooking);
//                event.getTicketBookings().add(ticketBooking);
//                ticketBookingRepository.save(ticketBooking);
//                return TicketBookingDTO.mapToDTO(ticketBooking);
//            }else {
//                throw new RuntimeException("Not enough tickets available.");
//            }
//        }else {
//            throw new RuntimeException("Event not found.");
//        }
//    }


    @Transactional
    @Override
    public TicketBookingDTO bookEvent(TicketBooking ticketBooking) {
        //first find the event
        Optional<Event> eventOpt = eventRepository.findById(ticketBooking.getPlace().getPlaceId());
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            if(event.getMaxTicket()-event.getSoldTicket()>=ticketBooking.getTotalMember()) {
                ticketBooking.setPlace(event);
                Optional<User> userOpt = userRepository.findById(ticketBooking.getUser().getUserId());
                if (userOpt.isPresent()) {
                    if (userOpt.get().getStatus() == null) {
                        userOpt.get().setStatus(UserStatus.ACTIVE);
                    }
                    ticketBooking.setUser(userOpt.get());
                } else {
                    throw new RuntimeException("User not found.");
                }
//                ticketBooking.setTotalMember(ticketBooking.getTotalMember());
                event.setSoldTicket(event.getSoldTicket() - ticketBooking.getTotalMember());
                userOpt.get().getTicketBookings().add(ticketBooking);
                event.getTicketBookings().add(ticketBooking);
                ticketBookingRepository.save(ticketBooking);
                return TicketBookingDTO.mapToDTO(ticketBooking);
            }else {
                throw new RuntimeException("Not enough tickets available.");
            }
        }else {
            throw new RuntimeException("Event not found.");
        }
    }

    @Override
    public TicketBooking updateTicketBooking(Long id, TicketBooking ticketBooking) {
        Optional<TicketBooking> existingTicketBooking = ticketBookingRepository.findById(id);
        if (existingTicketBooking.isPresent()) {
            ticketBooking.setTicketId(id);
            return ticketBookingRepository.save(ticketBooking);
        }
        return null; // or throw an exception
    }

    @Override
    public void deleteTicketBooking(Long id) {
        ticketBookingRepository.deleteById(id);
    }

    @Override
    public TicketBooking findTicketBookingById(Long id) {
        return ticketBookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<TicketBooking> getAllTicketBookings() {
        return ticketBookingRepository.findAll();
    }

    @Override
    public List<TicketBookingDTO> getBookingsByUserId(Long userId) {
        List<TicketBooking> ticketBookings = ticketBookingRepository.findByUserUserId(userId);

        // Convert TicketBooking entities to TicketBookingDTOs
        return ticketBookings.stream().map(TicketBookingDTO::mapToDTO).collect(Collectors.toList());


    }
}


