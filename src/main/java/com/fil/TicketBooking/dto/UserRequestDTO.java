package com.fil.TicketBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}


