package com.fil.TicketBooking.service;

import com.fil.TicketBooking.errors.ResourceAlreadyExistsException;
import com.fil.TicketBooking.errors.ValidationException;
import com.fil.TicketBooking.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user)throws ResourceAlreadyExistsException, ValidationException;
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    User getUserByEmailAndPassword(String email, String password);
//    User loginUser(String email, String password);
}
