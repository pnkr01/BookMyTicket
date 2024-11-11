package com.fil.TicketBooking.controller;
import com.fil.TicketBooking.dto.UserDTO;
import com.fil.TicketBooking.errors.UserException;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.request.LoginRequest;
import com.fil.TicketBooking.response.AuthResponse;
import com.fil.TicketBooking.service.AuthService;
import com.fil.TicketBooking.serviceimpl.AuthServiceImpl;
import com.fil.TicketBooking.serviceimpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {
    Map<String, Object> response = new HashMap<>();
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthServiceImpl authService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException {
        AuthResponse authResponse = authService.signup(user);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.signin(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @PostMapping(value = "/is-user-exist")
//    public ResponseEntity<Object> isUserExist(@RequestBody UserDTO userDTO) {
//        User user = userService.getUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
//        if (user == null) {
//            response.put("error", "User not found");
//            response.put("status", HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @GetMapping(value = "/get-user-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/change-to-po/{email}")
    public ResponseEntity<?> changeUserToPO(@PathVariable String email){
      return  userServiceImpl.changeUserStatusToPO(email);
    }


    @PutMapping(value = "/update-user-by-id/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-user-by-id/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
