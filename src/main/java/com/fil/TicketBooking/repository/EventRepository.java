//package com.fil.TicketBooking.repository;
//import com.fil.TicketBooking.dto.EventDTO;
//import com.fil.TicketBooking.model.Event;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Date;
//import java.util.List;
//
//@Repository
//public interface EventRepository extends JpaRepository<Event, Long> {
//    List<Event> findByLocationLocationId(Long locationId);
//    @Query("SELECT e FROM Event e ORDER BY e.soldTicket DESC")
//    List<Event> findTop5BySoldTicket();
//
//    @Query("SELECT e FROM Event e WHERE e.eventFromDate <= :currentDate AND e.eventToDate >= :currentDate")
//    List<Event> findOngoingEvents(@Param("currentDate") Date currentDate);
//
//
//    @Query("SELECT e FROM Event e WHERE e.eventFromDate > :currentDate")
//    List<Event> findUpcomingEvents(@Param("currentDate") Date currentDate);
//
//
//
//    Page<Event> findTopSoldEvents(Pageable pageable);
//
//    Page<Event> findOngoingEvents(Pageable pageable);
//
//    Page<Event> findUpcomingEvents(Pageable pageable);
//}


package com.fil.TicketBooking.repository;

import com.fil.TicketBooking.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e ORDER BY e.soldTicket DESC")
    Page<Event> findTopSoldEvents(Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.eventFromDate <= :currentDate AND e.eventToDate >= :currentDate")
    Page<Event> findOngoingEvents(@Param("currentDate") Date currentDate, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.eventFromDate > :currentDate")
    Page<Event> findUpcomingEvents(@Param("currentDate") Date currentDate, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE LOWER(e.location.locationName) LIKE LOWER(CONCAT('%', :locationName, '%'))")
    List<Event> searchByLocationName(@Param("locationName") String locationName);
}
