package com.fil.TicketBooking.dto;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    String email;
    String password;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
