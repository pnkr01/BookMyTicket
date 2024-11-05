package com.fil.TicketBooking.controller;

import com.fil.TicketBooking.errors.UserException;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.request.LoginRequest;
import com.fil.TicketBooking.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;
//	private CartService cartService;

	@PostMapping("/signup")
	public ResponseEntity<?> createUserHandler(@Valid @RequestBody User user) throws UserException {
		return userServiceImpl.createUser(user);
	}
	
	@PostMapping("/signin")
    public ResponseEntity<?> doSignIn(@RequestBody LoginRequest loginRequest) {
        return userServiceImpl.logInUser(loginRequest);
    }
}
