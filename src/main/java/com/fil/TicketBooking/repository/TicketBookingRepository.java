package com.fil.TicketBooking.repository;
import com.fil.TicketBooking.dto.TicketBookingDTO;
import com.fil.TicketBooking.model.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {
    List<TicketBooking> findByUserUserId(Long userId);
}
