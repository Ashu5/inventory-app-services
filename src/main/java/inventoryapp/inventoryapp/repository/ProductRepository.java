package inventoryapp.inventoryapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import inventoryapp.inventoryapp.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,String>{
	
}
