package com.fil.TicketBooking.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long placeId;
    private String placeName;
    private String description;
    private Long maxTicket;
    private Long soldTicket;
    private Long ticketPrice;
    private String openTiming;
    private String openDays;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
