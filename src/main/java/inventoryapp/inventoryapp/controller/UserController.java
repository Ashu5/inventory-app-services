package inventoryapp.inventoryapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import inventoryapp.inventoryapp.exceptions.UserException;
import inventoryapp.inventoryapp.model.AuthenticationRequest;
import inventoryapp.inventoryapp.model.AuthenticationResponse;
import inventoryapp.inventoryapp.model.User;
import inventoryapp.inventoryapp.service.MyUserDetailsService;
import inventoryapp.inventoryapp.service.UserService;
import inventoryapp.inventoryapp.utils.JwtUtils;

@RestController
@RequestMapping("/api")
@CrossOrigin
@ControllerAdvice
public class UserController {

	List<User> users;
	@Autowired
	UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getemail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getemail());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/getUser/{email}")
	public List<User> getUsers(@PathVariable String email) {
		LOGGER.info("getUser/email API Call initiated *****");
		System.out.println(this.userService.findByEmail(email));
		return this.userService.findByEmail(email);

	}

	@GetMapping("/getUser/{email}/{password}")
	public User getUserDetails(@PathVariable(value = "email") String email,
			@PathVariable(value = "password") String password) {
		LOGGER.info("getUser/email/password API Call initiated *****");
		return this.userService.getByEmailAndPass(email, password);

	}

	@ExceptionHandler(UserException.class)
	@PostMapping(path = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public User add(@RequestBody User user) {
		LOGGER.info("addUser API Call initiated *****");
		if (user != null) {
			System.out.println("Users inserted Successful" + user);
			return userService.save(user);
		} else
			return user;

	}

	@GetMapping(value = "/logout")
	public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("logout API Call initiated *****");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}

}
