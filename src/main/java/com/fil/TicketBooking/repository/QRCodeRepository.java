package com.fil.TicketBooking.repository;

import com.fil.TicketBooking.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    Optional<QRCode> findByTicketTicketId(Long ticketId);
}
