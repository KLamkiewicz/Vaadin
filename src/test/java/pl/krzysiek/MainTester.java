package pl.krzysiek;

import org.junit.Test;

import pl.krzysiek.model.Message;
import pl.krzysiek.util.Replacer;

public class MainTester {

	@Test
	public void testReplacer(){
		Message m = new Message();
		m.setUsername("ADAM");
		m.setMessage("XZCZXCXZS:DADSADAS:)xzxzcxzczx:Dxzcxzczxcxzczxvv:)");
		Replacer.replace(m);
	}
}
