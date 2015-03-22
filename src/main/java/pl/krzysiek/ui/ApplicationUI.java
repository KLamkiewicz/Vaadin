package pl.krzysiek.ui;

import javax.servlet.annotation.WebServlet;

import pl.krzysiek.model.User;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ApplicationUI extends UI{

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout v = new VerticalLayout();
		v.addComponent(new Label("TEST MAIN"));
		setContent(v);
		//t();
	}

	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ApplicationUI.class)
	public static class Servlet extends VaadinServlet {
		
	}
	
//	public void t(){
//		User u = new User();
//		u.setUsername("Adam");
//		VaadinSession.getCurrent().getLockInstance().lock();
//		VaadinSession.getCurrent().getSession().setAttribute("user", u);
//		VaadinSession.getCurrent().getLockInstance().unlock();
//	}
}
