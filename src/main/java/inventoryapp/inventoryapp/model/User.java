package inventoryapp.inventoryapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;


@Data
@Document(collection= "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
@Id
private String id;	
@NonNull
private String fname;
@NonNull
private String lname;
@Indexed(unique=true)
private String email;
@NonNull
private String password;


public User() {
	
}


public User(String id, @NonNull String fname, @NonNull String lname, String email, @NonNull String password) {
	super();
	this.id = id;
	this.fname = fname;
	this.lname = lname;
	this.email = email;
	this.password = password;
}


public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getemail() {
	return email;
}
public void setemail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password="
			+ password + "]";
}






}
