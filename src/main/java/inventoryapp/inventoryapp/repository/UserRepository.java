package inventoryapp.inventoryapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import inventoryapp.inventoryapp.model.User;

@Repository 
public interface UserRepository extends MongoRepository<User,String>{

	
	}

