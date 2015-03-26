package pl.krzysiek.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.atmosphere.config.service.Message;

import pl.krzysiek.util.UserExists;

public class User {

	@NotNull
	@Size(min=3, max=15, message="Login must be 3-15 characters long")
	//@UserExists(groups=Register.class)
	private String username;
	@NotNull
	@Size(min=3, max=15, message="Password must be 3-15 characters long")
	private String password;
	private int messages = 0;
	
	public User(){
		
	}
	
	public User(String username) {
		this.username = username;
	}
	
	
	public int getMessages() {
		return messages;
	}

	public void setMessages(int messages) {
		this.messages = messages;
	}

	public void addCount(){
		this.messages++;
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
