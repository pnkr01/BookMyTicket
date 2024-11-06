package com.fil.TicketBooking.service;



import com.fil.TicketBooking.errors.UserException;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.request.LoginRequest;
import com.fil.TicketBooking.response.AuthResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthResponse signup(User user) throws UserException;
    AuthResponse signin(LoginRequest loginRequest);
    Authentication authenticate(String username, String password);
}

