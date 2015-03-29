package pl.krzysiek.dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import pl.krzysiek.data.DataContainer;
import pl.krzysiek.model.User;

public class UserDAO {
	
	private DataContainer dataContainer = DataContainer.getInstance();
	
	
	public User getUser(String name){
		List<User> list = dataContainer.getUsers();
			for(User u : dataContainer.getUsers()){
				if(u.getUsername().equals(name)){
					return u;
				}
			}
		return null;
	}
	
	public void addUser(User u){
		dataContainer.addUser(u);
	}
	
	public int getNumberOfMessages(String username){
		List<User> list = dataContainer.getUsers();
			for(User u : dataContainer.getUsers()){
				if(u.getUsername().equals(username)){
					return u.getMessages();
				}
			}
		return 0;
	}
	
	public void addMessageCount(String username){
		List<User> list = dataContainer.getUsers();
			for(User u : dataContainer.getUsers()){
				if(u.getUsername().equals(username)){
					u.addCount();
				}
			}
	}
	
	public List<User> getTopPosters(){
		List<User> list = dataContainer.getUsers();
		
		list.sort(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				if(o1.getMessages()>=o2.getMessages()){
					return -1;
				}else{
					return 1;
				}
			}
		});

		return list;
	}
}
