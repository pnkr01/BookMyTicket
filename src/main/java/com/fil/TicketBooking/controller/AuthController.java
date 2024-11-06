package com.fil.TicketBooking.controller;
import com.fil.TicketBooking.config.JwtTokenProvider;
import com.fil.TicketBooking.dto.UserDTO;
import com.fil.TicketBooking.errors.UserException;
import com.fil.TicketBooking.model.User;
import com.fil.TicketBooking.repository.UserRepository;
import com.fil.TicketBooking.request.LoginRequest;
import com.fil.TicketBooking.response.ApiResponse;
import com.fil.TicketBooking.response.AuthResponse;
import com.fil.TicketBooking.service.CustomUserDetails;
import com.fil.TicketBooking.serviceimpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final UserServiceImpl userServiceImpl;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final CustomUserDetails customUserDetails;

	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, CustomUserDetails customUserDetails, UserServiceImpl userServiceImpl) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.jwtTokenProvider=jwtTokenProvider;
		this.customUserDetails=customUserDetails;
		this.userServiceImpl = userServiceImpl;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> createUserHandler(@Valid @RequestBody User user) throws UserException {

		User isEmailExist=userRepository.findByEmail(user.getEmail());

		// Check if user with the given email already exists
		if (isEmailExist!=null) {
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setMessage("email already exist. please login");
			apiResponse.setStatus(false);
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		}
//			createdUser.setLastName(lastName);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//   createdUser.setRole(role);

		User savedUser= userRepository.save(user);

		//   cartService.createCart(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);
		
		User user1 = userRepository.findByEmail(user.getEmail());

		AuthResponse authResponse= new AuthResponse(token,true,user1);

		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);

	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		AuthResponse authResponse= new AuthResponse();
		User user = userRepository.findByEmail(loginRequest.getEmail());
		authResponse.setStatus(true);
		authResponse.setJwt(token);
		authResponse.setUser(user);
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);

		System.out.println("sign in userDetails - "+userDetails);

		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
//	@GetMapping("/getAllUsers")
//	  public ResponseEntity<List<User>> getAllUsers() {
//	      List<User> users = userRepository.findAllUsers();
//	      return ResponseEntity.ok(users);
//	  }
}