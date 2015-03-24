package pl.krzysiek.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.atmosphere.config.service.Message;

public class User {

	@NotNull
	@Size(min=3, max=15, message="Login is 3-15 characters long")
	private String username;
	@NotNull
	@Size(min=3, max=15, message="Password is 3-15 characters long")
	private String password;
	
	public User(){
		
	}
	
	public User(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
}
