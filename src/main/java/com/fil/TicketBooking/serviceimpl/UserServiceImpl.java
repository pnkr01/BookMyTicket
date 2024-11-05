package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.config.JwtTokenProvider;
import com.fil.TicketBooking.dto.ErrorResponseDTO;
import com.fil.TicketBooking.enums.UserRole;
import com.fil.TicketBooking.errors.ResourceAlreadyExistsException;
import com.fil.TicketBooking.errors.ValidationException;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.repository.UserRepository;
import com.fil.TicketBooking.request.LoginRequest;
import com.fil.TicketBooking.response.AuthResponse;
import com.fil.TicketBooking.service.CustomUserDetails;
import com.fil.TicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createUser(User user) {
        User isEmailExist = userRepository.findByEmail(user.getEmail());
//        if(byEmail != null){
//            throw new ResourceAlreadyExistsException("User with this email already exists");
//        }
//        return userRepository.save(user);

        if (isEmailExist!=null) {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Email is already used with another account" ,400);
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser= userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(token,true,user);

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> logInUser(LoginRequest loginRequest) throws ResourceAlreadyExistsException, ValidationException {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        User user = userRepository.findByEmail(loginRequest.getEmail());
        AuthResponse authResponse= new AuthResponse();
        authResponse.setStatus(true);
        authResponse.setJwt(token);
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
    }


    @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            user.setUserId(id);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("sign in userDetails - "+userDetails);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public ResponseEntity<?> changeUserStatusToPO(String email) {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            user.setRole(UserRole.PO);
            userRepository.save(user);
            return ResponseEntity.ok("User status changed to PlaceOwner successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }
}
