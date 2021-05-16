package inventoryapp.inventoryapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoClient;

import inventoryapp.inventoryapp.model.Product;
import inventoryapp.inventoryapp.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);
	@Autowired
	MongoClient mongoClient;
	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	@ResponseStatus(code= HttpStatus.CREATED)
	public Product add(@RequestBody Product product) {
		LOGGER.info("addProduct API Call initiated *****");
		System.out.println("City inserted Successful"+product);	
		return this.productService.save(product);
	}
	
	@GetMapping("/getProducts")
	public List<Product> getProducts(){
		LOGGER.info("getProducts API call Intiated****");
		return this.productService.getAllProducts();
	}
	

	@GetMapping("/getProductByCategory/{category}")
	public List<Product> getProductByCategory(@PathVariable String category) {
		LOGGER.info("getProductByCategory API Call initiated *****");
		System.out.println(this.productService.findByCategory(category));
		return this.productService.findByCategory(category);	
			
	}
	
	@PutMapping(value="/updateProduct/{id}")
	public Product updateProduct(@PathVariable String id, @RequestBody Product product){
		LOGGER.info("updateProduct/id API Call initiated *****");
		return this.productService.updateProduct(id, product);
		
	}
	
}
