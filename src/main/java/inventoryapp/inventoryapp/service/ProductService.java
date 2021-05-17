package inventoryapp.inventoryapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import inventoryapp.inventoryapp.model.Product;
import inventoryapp.inventoryapp.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	ProductRepository productRepository;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductService.class);
	
	public Product save(Product product) {
		LOGGER.info("save() API Call initiated *****");
		return this.productRepository.save(product);

	}
	
	public List<Product> getAllProducts(){
		LOGGER.info("***findAll() call Initiated ***");
		System.out.println("FindAll Product List:"+this.productRepository.findAll());
		LOGGER.info("***findAll() call Terminated ***");
		return this.productRepository.findAll();
	}
	
	public List<Product> findByCategory(String category) {
		LOGGER.info("***findByCategory() call Initiated ***");
		System.out.println("findByCategory:" + category);
		Query query = new Query(Criteria.where("category").is(category));
		List<Product> products=mongoTemplate.find(query, Product.class, "product");
		System.out.println(products);
		LOGGER.info("***findByCategory() call Terminated ***");
		return products;

	}
	
	public Product updateProduct(String id,Product updatedProduct) {
		LOGGER.info(" updateProduct() Call initiated *****");
		Optional<Product> product= productRepository.findById(id);
		boolean isPresent=product.isPresent();
		if(isPresent) {
			product.get().setProductName(updatedProduct.getProductName());
			product.get().setDescription(updatedProduct.getDescription());
			product.get().setCategory(updatedProduct.getCategory());
			product.get().setUnit(updatedProduct.getUnit());
			Product products=product.get();
			productRepository.save(products);
		}else {
			System.out.println("Product Details Not Uodated");
		}
		System.out.println("Updated:"+updatedProduct);
		LOGGER.info(" updateProduct() Call Terminated *****");

		return updatedProduct;
		
	}
	
	public void deleteById(String id) {
		LOGGER.info("***deleteById() call Initiated ***");
	    LOGGER.info("Deleted Product:"+id);
	    LOGGER.info("***deleteById() call Terminated ***");
		this.productRepository.deleteById(id);
		;

	}
}
