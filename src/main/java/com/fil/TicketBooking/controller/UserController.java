package com.fil.TicketBooking.controller;
import com.fil.TicketBooking.dto.UserDTO;
import com.fil.TicketBooking.model.User;
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
@RequestMapping("/api/user")
public class UserController {
    Map<String, Object> response = new HashMap<>();
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/get-all-user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/is-user-exist")
    public ResponseEntity<Object> isUserExist(@RequestBody UserDTO userDTO) {
        User user = userService.getUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if (user == null) {
            response.put("error", "User not found");
            response.put("status", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/get-user-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PostMapping(value = "/login")
//    public ResponseEntity<Object> login(@Valid @RequestBody UserDTO user) {
//        User createdUser = userService.(user);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody User user) {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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
