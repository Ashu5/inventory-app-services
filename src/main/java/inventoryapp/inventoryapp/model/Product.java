package inventoryapp.inventoryapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NonNull;

@Data
@Document(collection = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
@Id
@Indexed(unique = true)
private String id;
@NonNull
private String productName;
@NonNull
private String category;
private String description;
@NonNull
private int unit;

public Product() {
	
}
public Product(String id, String productName, String category, String description, int unit) {
	super();
	this.id = id;
	this.productName = productName;
	this.category = category;
	this.description = description;
	this.unit = unit;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getUnit() {
	return unit;
}
public void setUnit(int unit) {
	this.unit = unit;
}
@Override
public String toString() {
	return "Product [id=" + id + ", productName=" + productName + ", category=" + category + ", description="
			+ description + ", unit=" + unit + "]";
}


}
