package pl.krzysiek.dao;

import java.util.List;

import pl.krzysiek.data.DataContainer;
import pl.krzysiek.model.User;

public class UserDAO {
	
	private DataContainer dataContainer = DataContainer.getInstance();
	
	
	public User getUser(String name){
		List<User> list = dataContainer.getUsers();
		synchronized (list) {
			for(User u : dataContainer.getUsers()){
				System.out.println(u);
				if(u.getUsername().equals(name)){
					return u;
				}
			}
		}
		return null;
	}
	
	public void addUser(User u){
		dataContainer.addUser(u);
	}
	
	public int getNumberOfMessages(String username){
		List<User> list = dataContainer.getUsers();
		synchronized (list) {
			for(User u : dataContainer.getUsers()){
				System.out.println(u);
				if(u.getUsername().equals(username)){
					return u.getMessages();
				}
			}
		}
		return 0;
	}
	
	public void addMessageCount(String username){
		List<User> list = dataContainer.getUsers();
		synchronized (list) {
			for(User u : dataContainer.getUsers()){
				System.out.println(u);
				if(u.getUsername().equals(username)){
					u.addCount();
				}
			}
		}
	}
}
