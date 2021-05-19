package inventoryapp.inventoryapp.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import inventoryapp.inventoryapp.exceptions.UserException;
import inventoryapp.inventoryapp.model.User;
import inventoryapp.inventoryapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	UserRepository usersRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	public User save(User user) {
		LOGGER.info("save() API Call initiated *****");
		return this.usersRepository.save(user);

	}

	public List<User> findByEmail(String email) {
		LOGGER.info("***findByEmail() call Initiated ***");
		System.out.println("FindByEmail:" + email);
		Query query = new Query(Criteria.where("email").is(email));
		List<User> users = mongoTemplate.find(query, User.class, "users");
		if(users==null) {
			throw new UserException("Email Not Found");
		}
		System.out.println(users);
		LOGGER.info("***findByEmail() call Terminated ***");
		return users;

	}

	public User getByEmailAndPass(String email, String password) {
		LOGGER.info("***getByEmailAndPass() call Initiated ***");
		System.out.println("getByEmailAndPass:" + email);
		Query query = new Query(Criteria.where("email").is(email).and("password").is(password));
		User users= mongoTemplate.findOne(query, User.class);
        if(users==null) {
        	throw new UserException("User Not Found");
        }
		System.out.println("Users Found"+users);
		LOGGER.info("***getByEmailAndPass() call Terminated ***");
		return users;

	}

}
