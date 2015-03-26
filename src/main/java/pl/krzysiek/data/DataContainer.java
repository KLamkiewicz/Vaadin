package pl.krzysiek.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

import pl.krzysiek.model.User;

public class DataContainer {
	
	private static List<User> registeredUsers = Collections.synchronizedList(new ArrayList<User>());
	private final static Object dataLock = new Object();
	
	/*
	 * Thread safe singleton class
	 * http://stackoverflow.com/a/16106598/2170846
	 */
	private static class DataContainerHolder{
		static final DataContainer dataContainerInstance = new DataContainer();
	};
	
	public static DataContainer getInstance(){
		return DataContainerHolder.dataContainerInstance;
	}
	
	private DataContainer(){
		loadTestUsers();
	}
	
	public List<User> getUsers(){
		synchronized (dataLock) {
			return registeredUsers;
		}
	}
	
	public void addUser(User u){
		synchronized (dataLock) {
			registeredUsers.add(u);		
		}
	}
	
	//Created predefined users for testing
	public void loadTestUsers(){
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
		
		
		u1.setUsername("Adam");
		u1.setPassword("Haslo");
		u2.setUsername("Krzys");
		u2.setPassword("haslo");
		u3.setUsername("Test");
		u3.setPassword("ppr");

		
		registeredUsers.add(u1);
		registeredUsers.add(u2);
		registeredUsers.add(u3);
	}
}
