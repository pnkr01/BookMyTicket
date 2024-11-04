package com.fil.TicketBooking.dto;
import com.fil.TicketBooking.enums.BookingStatus;
import com.fil.TicketBooking.model.TicketBooking;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketBookingDTO {
    private Long ticketId;
    private Long userId;
    private Long placeId;
    private Date bookingDate;
    private String ticketDetails;
    private double totalPrice;
    private BookingStatus status;
    private int totalMember;


    public static TicketBookingDTO mapToDTO(TicketBooking ticketBooking) {
        TicketBookingDTO dto = new TicketBookingDTO();
        dto.setTicketId(ticketBooking.getTicketId());
        dto.setUserId(ticketBooking.getUser().getUserId());
        dto.setPlaceId(ticketBooking.getPlace().getPlaceId());
        dto.setTotalMember(ticketBooking.getTotalMember());
        dto.setTicketDetails(ticketBooking.getTicketDetails());
        dto.setTotalPrice(ticketBooking.getTotalPrice());
        dto.setStatus(ticketBooking.getStatus());
        dto.setBookingDate(ticketBooking.getBookingDate());
        return dto;
    }

}