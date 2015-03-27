package pl.krzysiek;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;

import pl.krzysiek.dao.UserDAO;
import pl.krzysiek.model.Message;
import pl.krzysiek.model.User;
import pl.krzysiek.util.Replacer;

public class MainTester {

	@Test
	public void testReplacer(){
		Message m = new Message();
		m.setUsername("ADAM");
		m.setMessage("XZCZXCXZS:DADSADAS:)xzxzcxzczx:Dxzcxzczxcxzczxvv:)");
		Replacer.replace(m);
	}
	
	@Test
	public void topPosters(){
//
	}
}
