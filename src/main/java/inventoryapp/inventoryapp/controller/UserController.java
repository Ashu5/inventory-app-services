package inventoryapp.inventoryapp.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import inventoryapp.inventoryapp.model.User;
import inventoryapp.inventoryapp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

	List<User> users;
	@Autowired
	UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
	
	
	@PostMapping(path="/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public User add(@RequestBody User user) {
		LOGGER.info("addUser API Call initiated *****");
		if (user != null) {
			System.out.println("Users inserted Successful" + user);
			return userService.save(user);
		} else
			return user;

	}

}
